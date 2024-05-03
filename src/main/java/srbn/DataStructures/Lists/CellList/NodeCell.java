package srbn.DataStructures.Lists.CellList;

import srbn.DataStructures.Graph.MatrixCell;

public class NodeCell {

    protected MatrixCell element;
    protected NodeCell next;

    public NodeCell() {
    }

    public NodeCell(MatrixCell element) {
        this.element = element;
        this.next = null;
    }

    public NodeCell(MatrixCell element, NodeCell next) {
        this.element = element;
        this.next = next;
    }

    public MatrixCell getElement() {
        return element;
    }

    public void setElement(MatrixCell element) {
        this.element = element;
    }

    public NodeCell getNext() {
        return next;
    }

    public void setNext(NodeCell newNode) {
        this.next = newNode;
    }

}
