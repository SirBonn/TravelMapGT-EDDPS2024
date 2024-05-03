package srbn.DataStructures.Lists.NodeGList;

import srbn.DataStructures.Graph.NodeG;

public class NodeNG {

    protected NodeG element;
    protected NodeNG next;

    public NodeNG() {
    }

    public NodeNG(NodeG element) {
        this.element = element;
        this.next = null;
    }

    public NodeNG(NodeG element, NodeNG next) {
        this.element = element;
        this.next = next;
    }

    public NodeG getElement() {
        return element;
    }

    public void setElement(NodeG element) {
        this.element = element;
    }

    public NodeNG getNext() {
        return next;
    }

    public void setNext(NodeNG newNode) {
        this.next = newNode;
    }
}
