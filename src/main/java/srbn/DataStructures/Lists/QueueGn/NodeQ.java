package srbn.DataStructures.Lists.QueueGn;

public class NodeQ<T> {

    private T element;
    private NodeQ<T> next;

    public NodeQ(T element) {
        this.element = element;
        this.next = null;
    }

    public T getElement() {
        return element;
    }

    public NodeQ<T> getNext() {
        return next;
    }

    public void setNext(NodeQ<T> next) {
        this.next = next;
    }

}
