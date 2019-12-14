package by.future.common.test.lrucache;

/**
 * 单向链表
 *
 * @author by@Deng
 * @create 2019-10-04 01:13
 */
public class SingleListNode {

    private SingleListNode nextNode;

    private String key;
    private String value;

    public SingleListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(SingleListNode nextNode) {
        this.nextNode = nextNode;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
