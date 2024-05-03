package srbn.DataStructures.Lists.NodeGList;

import srbn.DataStructures.Graph.NodeG;

public class OrderedNodeGList {

    private NodeNG head;
    private int size;

    public OrderedNodeGList() {
        this.head = null;
        this.size = 0;
    }

    public OrderedNodeGList(OrderedNodeGList list) {
        this();
        if(list.head != null){
            NodeNG ptr = list.head;
            while(ptr != null){
                add(ptr.getElement());
                ptr = ptr.getNext();
            }
        }
    }

    public void add(NodeG element) {
        NodeNG newNode = new NodeNG(element);
        if (head == null) {
            head = newNode;
        } else {
            NodeNG ptr = head;
            while (ptr.getNext() != null) {
                ptr = ptr.getNext();
            }
            ptr.setNext(newNode);
        }
        size++;
    }

    public int getSize() {
        return size;
    }


    public NodeG get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }
        NodeNG ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.getNext();
        }
        return ptr.getElement();
    }

    public NodeG get(String alias) {

        NodeNG ptr = head;
        while (!alias.equals(ptr.element.getAlias())) {
            ptr = ptr.getNext();
        }
        return ptr.getElement();
    }

    public boolean contains(NodeG element) {
        NodeNG ptr = head;
        while (ptr != null) {
            if (ptr.getElement().getAlias().equals(element.getAlias())) {
                return true;
            }
            ptr = ptr.getNext();
        }
        return false;
    }



}
