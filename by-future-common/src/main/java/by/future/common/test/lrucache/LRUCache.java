package by.future.common.test.lrucache;

import java.util.HashMap;

/**
 * @author by@Deng
 * @create 2019-08-10 21:50
 */
public class LRUCache {

    private int capacity; //容量
    private int len;

    private HashMap<Integer, DoubleListNode> map = new HashMap<>();
    private DoubleListNode firstNode;
    private DoubleListNode lastNode;

    public LRUCache(int capacity) {
        len = 0;
        this.capacity = capacity;
    }

    //获取元素
    public int get(int key){
        if(map.containsKey(key)){
            DoubleListNode listNode = map.get(key);

            //删除替换当前节点
            remove(listNode);
            setCurrentNode(listNode);

            return listNode.getValue();
        }else{
            return -1;
        }
    }

    //设置当前链表
    public void setCurrentNode(DoubleListNode listNode){

        listNode.setPreNode(null);
        listNode.setNextNode(firstNode);

        if(firstNode != null){
            firstNode.setPreNode(listNode);
        }
        firstNode = listNode;

        if(lastNode == null){
            lastNode = listNode;
        }
    }


    public void put(int key,int value){

        if(map.containsKey(key)){

            //替换已存在
            DoubleListNode existListNode = map.get(key);
            existListNode.setValue(value);

            //删除替换当前节点
            remove(existListNode);
            setCurrentNode(existListNode);

        }else{

            DoubleListNode listNode = new DoubleListNode(key,value);
            if(len < capacity){
                setCurrentNode(listNode);
                map.put(key,listNode);
                len++;
            }else{

                //删除最近访问最少
                map.remove(lastNode.getKey());
                lastNode = lastNode.getPreNode();

                if(lastNode != null){
                    lastNode.setNextNode(null);
                }

                setCurrentNode(listNode);
                map.put(key,listNode);
            }
        }

    }

    //删除元素
    public void remove(DoubleListNode listNode){

        DoubleListNode currentList = listNode;
        DoubleListNode preNode = currentList.getPreNode();
        DoubleListNode nextNode = currentList.getNextNode();

        if(preNode != null){
            preNode.setNextNode(nextNode);
        }else{
            firstNode = nextNode;
        }

        if(nextNode != null){
            nextNode.setPreNode(preNode);
        }else{
            lastNode = preNode;
        }
    }

    public static void main(String[] args) {

        LRUCache cache = new LRUCache( 2 );

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
//        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));
//        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));
//        cache.get(1);       // 返回 -1 (未找到)
        System.out.println(cache.get(3));
//        cache.get(3);       // 返回  3
        System.out.println(cache.get(4));
//        cache.get(4);       // 返回  4
    }

}
