package srbn.DataStructures.Lists.QueueGn;

import java.util.NoSuchElementException;

public class QueueGn<T> {

    private NodeQ<T> head;
    private NodeQ<T> tail;
    private int size;

    public QueueGn() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(T elemento) {
        NodeQ<T> nuevoNodo = new NodeQ<>(elemento);
        if (isEmpty()) {
            head = nuevoNodo;
            tail = nuevoNodo;
        } else {
            tail.setNext(nuevoNodo);
            tail = nuevoNodo;
        }
        size++;
    }

    public T poll() {
        if (isEmpty()) {
//            throw new NoSuchElementException("La cola está vacía");
            return null;
        }
        T elemento = head.getElement();
        head = head.getNext();
        size--;
        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
//            throw new NoSuchElementException("La cola está vacía");
            return null;
        }
        return head.getElement();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
