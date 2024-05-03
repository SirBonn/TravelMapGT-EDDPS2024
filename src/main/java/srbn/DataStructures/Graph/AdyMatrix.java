package srbn.DataStructures.Graph;

import srbn.DataStructures.Lists.CellList.OrderedCellList;
import srbn.DataStructures.Lists.GenericList.GenericOrderedList;
import srbn.DataStructures.Lists.NodeGList.OrderedNodeGList;
import srbn.DataStructures.Lists.QueueGn.QueueGn;
import srbn.DataStructures.Tree.TreeB;
import srbn.GraphViz.GraphVizManager;

import javax.swing.*;
import java.util.*;

public class AdyMatrix {

    //matrix[i][j] = i -> destination, j -> origin
    private MatrixCell[][] mtrx;
    private OrderedNodeGList nodesList;

    public AdyMatrix() {
    }

    public AdyMatrix(int size) {
        mtrx = new MatrixCell[size][size];
    }

    public GenericOrderedList<OrderedCellList> travel(NodeG start, NodeG target) {
        GenericOrderedList<OrderedCellList> path = findPathBFS(start, target);

        if (path.getSize()!=0) {
            System.out.println("Camino encontrado: " + path);
            new GraphVizManager(start.getAlias() +"To"+target.getAlias(), path);

            System.out.println("Arbol B generado: ");
            
        } else {
            String msg = "No hay caminos desde " + start.getAlias() + " hacia " + target.getAlias();
            JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("No se encontró un camino");
        }

        return path;
    }

    public void addCell(MatrixCell cell) {
        mtrx[cell.getOrigin().getId()][cell.getDestination().getId()].setRoute(cell.getRoute());
    }

    public void setCells(OrderedNodeGList nodes) {
        for (int i = 0; i < nodes.getSize(); i++) {
            mtrx[0][nodes.get(i).getId()] = new MatrixCell(nodes.get(i), nodes.get(i)); //destination
            mtrx[nodes.get(i).getId()][0] = new MatrixCell(nodes.get(i), nodes.get(i));//origins
        }
    }

    public void setCells(OrderedCellList cells) {
        for (int i = 0; i <= cells.getSize(); i++) {
            MatrixCell cell = cells.get(i);
            mtrx[cell.getOrigin().getId()][cell.getDestination().getId()] = new MatrixCell(cell);
        }
        printMatrix();
    }

    public void setTrafic(int origin, int destination, Traffic traffic) {
        if(mtrx[origin][destination] != null){
            mtrx[origin][destination].getRoute().setTraffic(traffic);
        }
    }

    public MatrixCell[][] getMtrx() {
        return mtrx;
    }

    public void setMtrx(MatrixCell[][] mtrx) {
        this.mtrx = mtrx;
    }

    private void printMatrix() {
        for (int i = 0; i < mtrx.length; i++) {
            for (int j = 0; j < mtrx.length; j++) {
                if (mtrx[i][j] == null) {
                    System.out.print("----- ");
                } else {
                    System.out.print(mtrx[i][j].getDestination().getAlias() + " ");
                }

            }
            System.out.println("");
        }
    }

    public int getSize(){
        return mtrx[0].length;
    }

    public OrderedNodeGList getNodesList() {
        return nodesList;
    }

    public void setNodesList(OrderedNodeGList nodesList) {
        this.nodesList = nodesList;
    }

    private GenericOrderedList<OrderedCellList> findPathBFS(NodeG start, NodeG target) {
        GenericOrderedList<OrderedCellList> cellsPath = new GenericOrderedList<>();
        GenericOrderedList<OrderedNodeGList> orderedAllPaths = new GenericOrderedList<>();
        QueueGn<OrderedNodeGList> queueGn = new QueueGn<>();
        QueueGn<OrderedCellList> queueCells = new QueueGn<>();


        OrderedNodeGList initialOrderedPath = new OrderedNodeGList();
        initialOrderedPath.add(start);
        queueGn.add(initialOrderedPath);
        OrderedCellList initialCellPath = new OrderedCellList();
        initialCellPath.add(mtrx[start.getId()][0]);
        queueCells.add(initialCellPath);

        while (!queueCells.isEmpty()) {
            OrderedNodeGList currentOrderedPath = (OrderedNodeGList) queueGn.poll();
            OrderedCellList currentCellPath = (OrderedCellList) queueCells.poll();
            NodeG currentOrdered = currentOrderedPath.get(currentOrderedPath.getSize() - 1);
            MatrixCell currentCell = currentCellPath.get(currentCellPath.getSize() - 1);

            if (currentOrdered.equals(target)) {
                orderedAllPaths.add(currentOrderedPath);
                cellsPath.add(currentCellPath);
                markBackwardPath(currentOrderedPath);
                continue;
            }

            int currentIndex = currentOrdered.getId();

            for (int i = 0; i < mtrx[currentIndex].length; i++) {
                MatrixCell cell = mtrx[currentIndex][i];
                if (cell != null && !cell.isVisited()) {
                    OrderedNodeGList newOrderedPath = new OrderedNodeGList(currentOrderedPath);
                    OrderedCellList newCellPath = new OrderedCellList(currentCellPath);
                    newOrderedPath.add(cell.getDestination());
                    newCellPath.add(cell);
                    queueGn.add(newOrderedPath);
                    queueCells.add(newCellPath);
                    cell.setVisited(true);
                }
            }
        }
        resetVisited(mtrx);

        return cellsPath;
    }

    public MatrixCell getCell(int origin, int destination) {
        return mtrx[origin][destination];
    }

    private void resetVisited(MatrixCell[][] adjacencyMatrix) {
        for (MatrixCell[] row : adjacencyMatrix) {
            for (MatrixCell cell : row) {
                if (cell != null) {
                    cell.setVisited(false);
                }
            }
        }
    }
    private void markBackwardPath(OrderedNodeGList path) {
        if (path.getSize() <= 1) {
            return;
        }

        for (int i = path.getSize() - 1; i > 0; i--) {
            NodeG currentNode = path.get(i);
            NodeG prevNode = path.get(i - 1);

            int currentIndex = currentNode.getId();
            int prevIndex = prevNode.getId();

            if(mtrx[currentIndex][prevIndex] != null){
                mtrx[currentIndex][prevIndex].setVisited(true);
            }

            for (int j = 0; j < mtrx[currentIndex].length; j++) {
                if (j != prevIndex && mtrx[currentIndex][j] != null) {
                    mtrx[currentIndex][j].setVisited(false);
                }
            }
        }
    }
}
