package srbn.DataStructures.Graph;

import srbn.DataStructures.Lists.NodeGList.OrderedNodeGList;

public class GraphManager {

    private OrderedNodeGList nodes;
    private int idCounter;

    public GraphManager() {
        this.nodes = new OrderedNodeGList();
        this.idCounter = 1;
    }

    private NodeG setNode(String alias) {

        NodeG newNode = new NodeG(alias, idCounter);

        if (nodes.contains(newNode)) {
            return nodes.get(alias);
        } else {
            nodes.add(newNode);
            idCounter++;
            return newNode;
        }
    }

    public MatrixCell newCell(String[] fields) {

        NodeG src = setNode(fields[0]);
        NodeG dest = setNode(fields[1]);
        int timeCar = Integer.parseInt(fields[2]);
        int timeWalk = Integer.parseInt(fields[3]);
        int gasUsage = Integer.parseInt(fields[4]);
        int pesonalWearing = Integer.parseInt(fields[5]);
        int distance = Integer.parseInt(fields[6]);

        return new MatrixCell(src, dest, timeCar, timeWalk, gasUsage, pesonalWearing, distance);

    }

    public OrderedNodeGList getNodes() {
        return nodes;
    }

    public int getTotalNodes() {
        return nodes.getSize()+1;
    }

    public void setTrafic(AdyMatrix matrix, String[] fields) {
        NodeG src = setNode(fields[0]);
        NodeG dest = setNode(fields[1]);
        int initialT = Integer.parseInt(fields[2]);
        int finalT = Integer.parseInt(fields[3]);
        int trafficP = Integer.parseInt(fields[4]);

        matrix.setTrafic(src.getId(), dest.getId(), new Traffic(initialT, finalT, trafficP));
        
    }
}
