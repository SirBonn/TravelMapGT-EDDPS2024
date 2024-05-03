package srbn.DataStructures.Lists.CellList;

import srbn.DataStructures.Graph.MatrixCell;
import srbn.DataStructures.Lists.NodeGList.NodeNG;
import srbn.DataStructures.Lists.NodeGList.OrderedNodeGList;

public class OrderedCellList {

    private NodeCell head;
    private int size;

    public OrderedCellList() {
        this.head = null;
        this.size = 0;
    }

    public OrderedCellList(OrderedCellList list) {
        this();
        if (list.head != null) {
            NodeCell ptr = list.head;
            while (ptr != null) {
                add(ptr.getElement());
                ptr = ptr.getNext();
            }
        }
    }



    public void add(MatrixCell element) {
        NodeCell newNode = new NodeCell(element);
        if (head == null) {
            head = newNode;
        } else {
            NodeCell ptr = head;
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

    //contains method
    public boolean contains(MatrixCell element) {
        NodeCell ptr = head;
        while (ptr != null) {
            if (ptr.getElement().getDestination().getAlias().equals(element.getDestination().getAlias())) {
                return true;
            }
            ptr = ptr.getNext();
        }
        return false;
    }

    public MatrixCell get(int index) {
        if (index < 0 || index - 1 >= size) {
            throw new IndexOutOfBoundsException("indice fuera de rango");
        }
        NodeCell ptr = head;
        for (int i = 0; i < index - 1; i++) {
            ptr = ptr.getNext();
        }
        return ptr.getElement();
    }

    public int getTotallyDistance() {
        int distance = 0;
        NodeCell ptr = head;
        while (ptr != null) {
            if(ptr.getElement().getRoute() != null) {
                distance += ptr.getElement().getRoute().getDistance();
            }
            ptr = ptr.getNext();
        }
        return distance;
    }

    public int getTotallyTimeWalk() {
        int time = 0;
        NodeCell ptr = head;
        while (ptr != null) {
            if(ptr.getElement().getRoute() != null) {
                time += ptr.getElement().getRoute().getTimeWalk();
            }
            ptr = ptr.getNext();
        }
        return time;
    }

    public int getTotallyTimeCar() {
        int time = 0;
        NodeCell ptr = head;
        while (ptr != null) {
            if(ptr.getElement().getRoute() != null) {
                time += ptr.getElement().getRoute().getTimeCar();
            }
            ptr = ptr.getNext();
        }
        return time;
    }

    public int getTotallyGasUsage() {
        int gasUsage = 0;
        NodeCell ptr = head;
        while (ptr != null) {
            if(ptr.getElement().getRoute() != null) {
                gasUsage += ptr.getElement().getRoute().getGasUsage();
            }
            ptr = ptr.getNext();
        }
        return gasUsage;
    }

    public int getTotallyPersonalWearing() {
        int personalWearing = 0;
        NodeCell ptr = head;
        while (ptr != null) {
            if(ptr.getElement().getRoute() != null) {
                personalWearing += ptr.getElement().getRoute().getPersonalWearing();
            }
            ptr = ptr.getNext();
        }
        return personalWearing;
    }

    //order by distance


//    public boolean contains(MatrixCell element) {
//        NodeNG ptr = head;
//        while (ptr != null) {
//            if (ptr.getElement().getAlias().equals(element.getAlias())) {
//                return true;
//            }
//            ptr = ptr.getNext();
//        }
//        return false;
//    }

}
