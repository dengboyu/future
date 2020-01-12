package by.future.common.utils;


import java.util.*;

/**
 * hash一致性
 *
 * @Author：by@Deng
 * @Date：2020/1/12 19:22
 */
public class ConsistentHashingUtils {

    //物理节点
    private Set<String> physicalNodeList = new TreeSet<String>() {
        {
            add("192.168.1.101");
            add("192.168.1.102");
            add("192.168.1.103");
            add("192.168.1.104");
        }
    };

    //虚拟节点
    private static final int VIRTUAL_NODES = 1 << 20; //每个物理节点有多少个虚拟节点(1M,尽可能保证均匀分布)

    //哈希值-->物理节点
    private TreeMap<Long, String> virtualNodes = new TreeMap<>();

    //根据物理节点-构建虚拟节点映射表
    public ConsistentHashingUtils() {
        for (String nodeIp : physicalNodeList) {
            addPhysicalNode(nodeIp);
        }
    }


    //添加物理节点
    public void addPhysicalNode(String nodeIp) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            long hash = FNVHash(nodeIp + "#" + i);
            virtualNodes.put(hash, nodeIp);
        }
    }


    //移除物理节点
    public void removePhysicalNode(String nodeIp) {
        for (int i = 0; i < VIRTUAL_NODES; i++) {
            long hash = FNVHash(nodeIp + "#" + i);
            virtualNodes.remove(hash);
        }
    }


    //查找对象映射的节点
    public String getObjectNode(String object) {
        long hash = FNVHash(object);
        SortedMap<Long, String> tailMap = virtualNodes.tailMap(hash);    //所有大于hash的节点
        long key = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
        return virtualNodes.get(key);
    }


    // 统计对象与节点的映射关系
    public void dumpObjectNodeMap(String label, int objectMin, int objectMax) {
        // 统计
        Map<String, Integer> objectNodeMap = new TreeMap<>(); // IP => COUNT
        for (int object = objectMin; object <= objectMax; ++object) {
            String nodeIp = getObjectNode(Integer.toString(object));
            Integer count = objectNodeMap.get(nodeIp);
            objectNodeMap.put(nodeIp, (count == null ? 0 : count + 1));
        }

        // 打印
        double totalCount = objectMax - objectMin + 1;
        System.out.println("======== " + label + " ========");
        for (Map.Entry<String, Integer> entry : objectNodeMap.entrySet()) {
            long percent = (int) (100 * entry.getValue() / totalCount);
            System.out.println("IP=" + entry.getKey() + ": RATE=" + percent + "%");
        }
    }


    public static void main(String[] args) {
        ConsistentHashingUtils ch = new ConsistentHashingUtils();

        // 初始情况
        ch.dumpObjectNodeMap("初始情况", 0, 65536);

        // 删除物理节点
        ch.removePhysicalNode("192.168.1.103");
        ch.dumpObjectNodeMap("删除物理节点", 0, 65536);

        // 添加物理节点
        ch.addPhysicalNode("192.168.1.108");
        ch.addPhysicalNode("192.168.1.109");
        ch.addPhysicalNode("192.168.1.110");
        ch.addPhysicalNode("192.168.1.111");
        ch.dumpObjectNodeMap("添加物理节点", 0, 65536);
    }


    //32位的 Fowler-Noll-Vo 哈希算法
    //https://en.wikipedia.org/wiki/Fowler–Noll–Vo_hash_function
    private static long FNVHash(String key) {
        final int p = 16777619;
        long hash = 2166136261L;

        for (int idx = 0, num = key.length(); idx < num; ++idx) {
            hash = (hash ^ key.charAt(idx)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


}
