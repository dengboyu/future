package by.future;

/**
 * @author by@Deng
 * @create 2019-08-10 21:49
 */
public class ListNode {

    private int key;
    private int value;

    private ListNode preNode;
    private ListNode nextNode;

    public ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getPreNode() {
        return preNode;
    }

    public void setPreNode(ListNode preNode) {
        this.preNode = preNode;
    }

    public ListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode nextNode) {
        this.nextNode = nextNode;
    }
}
