package srbn.DataStructures.Lists.GenericList;

public class NodeGeneric<T>{
    private T data;
    private NodeGeneric<T> next;

    public NodeGeneric(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public NodeGeneric<T> getNext() {
        return next;
    }

    public void setNext(NodeGeneric<T> next) {
        this.next = next;
    }
}
