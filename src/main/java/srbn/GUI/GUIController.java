package srbn.GUI;

import srbn.DataStructures.Graph.MatrixCell;
import srbn.DataStructures.Graph.NodeG;
import srbn.DataStructures.Lists.GenericList.GenericOrderedList;
import srbn.DataStructures.Lists.NodeGList.OrderedNodeGList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import srbn.DataStructures.Lists.CellList.OrderedCellList;

public class GUIController {

    private JLabel label;
    private OrderedCellList pathSelected;
    private MatrixCell previousCell;
    private NodeG principalTarget;


    public GUIController() {
    }

    public OrderedCellList getPathSelected() {
        return pathSelected;
    }

    public MatrixCell getPreviousCell() {
        return previousCell;
    }

    public void setPreviousCell(MatrixCell previousCell) {
        this.previousCell = previousCell;
    }

    public NodeG getPrincipalTarget() {
        return principalTarget;
    }

    public void setPrincipalTarget(NodeG principalTarget) {
        this.principalTarget = principalTarget;
    }

    public void setClock(JFrame mainFrame, JLabel hourLabel) {

        this.label = hourLabel;
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setFont(new Font("Arial", Font.BOLD, 15));

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateHour(label);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    private void updateHour(JLabel hourLabel) {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        Date ahora = new Date();
        hourLabel.setText(formatoHora.format(ahora));
    }

    public void setItems(JComboBox comboBox, OrderedNodeGList nodes) {
        for (int i = 0; i < nodes.getSize(); i++) {
            comboBox.addItem(nodes.get(i).getAlias());
        }
    }

    public boolean setImageAsPanel(JScrollPane panel, JLabel toGraphLabel, String pathImage) {
        if (toGraphLabel.getIcon() != null) {
            toGraphLabel.setIcon(null);
            return true;
        } else {
            toGraphLabel.setIcon(null);
            ImageIcon imageIcon = new ImageIcon(pathImage);
            toGraphLabel.setSize(panel.getSize());
            toGraphLabel.setHorizontalAlignment(JLabel.CENTER);
            toGraphLabel.setIcon(imageIcon);
            setScroll(panel);
            return false;
        }
    }

    public void setPathsButtons(JPanel pathsPanel, GenericOrderedList<OrderedCellList> paths,
                                String src, String target, JScrollPane routePanel, JLabel routeMapLabel, JTextArea infoRouteLabel, int orderBy) {

        pathsPanel.removeAll();
        routeMapLabel.setIcon(null);
        infoRouteLabel.setText("");

        pathsPanel.setLayout(new GridLayout(paths.getSize(), 1));
        for (int i = 0; i < paths.getSize(); i++) {
            JButton button = new JButton(src + "To" + target + " " + (i + 1));
            String pathFile = "Graphs/" + src + "To" + target + "/" + src + "To" + target + i + ".png";
            OrderedCellList path = paths.get(i);
            int finalI = i;
            button.addActionListener(e -> {
                if (setImageAsPanel(routePanel, routeMapLabel, pathFile)) {
                    setImageAsPanel(routePanel, routeMapLabel, pathFile);
                }
                setLabelInfo(infoRouteLabel, path, finalI, orderBy);
                this.pathSelected = path;
            });
            pathsPanel.add(button);
        }
        pathsPanel.revalidate();
        pathsPanel.repaint();

    }

    public void setLabelInfo(JTextArea infoRouteLabel, OrderedCellList path, int i, int orderBy) {
        String text = "Ruta " + (i + 1) + " total: " + setNodeWeight(null, orderBy, path)
                + "\n";
        for (int j = 2; j <= path.getSize(); j++) {
            text += "\t" + path.get(j).getOrigin().getAlias() + " -> " + path.get(j).getDestination().getAlias() + ": ";
            text += setNodeWeight(path.get(j), orderBy, null);

            if (path.get(j).getRoute().getTraffic() != null) {
                text += "Probable trafico de: " + path.get(j).getRoute().getTraffic().getInitHour() + " a " + path.get(j).getRoute().getTraffic().getEndHour();
            }

            text += "\n";

        }
        infoRouteLabel.setText(text);
        infoRouteLabel.revalidate();
        infoRouteLabel.repaint();
    }

    private String setNodeWeight(MatrixCell matrixCell, int type, OrderedCellList path) {
        if (path != null) {
            switch (type) {
                case 0:
                    return path.getTotallyDistance() + " km\n";
                case 1:
                    return path.getTotallyPersonalWearing() + " Pw\n";
                case 2:
                    return path.getTotallyGasUsage() + " GasL\n";
                case 3:
                    return path.getTotallyTimeWalk() + " Wmin\n";
                case 4:
                    return path.getTotallyTimeCar() + " Cmin\n";
                default:
                    return "";
            }
        } else {
            switch (type) {
                case 0:
                    return matrixCell.getRoute().getDistance() + " km\n";
                case 1:
                    return matrixCell.getRoute().getPersonalWearing() + " Pw\n";
                case 2:
                    return matrixCell.getRoute().getGasUsage() + " GasL\n";
                case 3:
                    return matrixCell.getRoute().getTimeWalk() + " Wmin\n";
                case 4:
                    return matrixCell.getRoute().getTimeCar() + " Cmin\n";
                default:
                    return "";
            }
        }
    }

    private void setScroll(JScrollPane scrollPane) {

        scrollPane.addMouseListener(new MouseAdapter() {
            private Point origin;

            @Override
            public void mousePressed(MouseEvent e) {
                origin = new Point(e.getPoint());
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (origin != null) {
                    JViewport viewPort = scrollPane.getViewport();
                    Point pos = viewPort.getViewPosition();
                    pos.translate(origin.x - e.getX(), origin.y - e.getY());
                    viewPort.setViewPosition(pos);
                }
            }
        });

        scrollPane.revalidate();
        scrollPane.repaint();
    }

    public GenericOrderedList<OrderedCellList> sortPaths(GenericOrderedList<OrderedCellList> travel, int option) {

        GenericOrderedList<OrderedCellList> sortedList = travel;
        OrderedCellList[] array = new OrderedCellList[travel.getSize()];
        for (int i = 0; i < travel.getSize(); i++) {
            array[i] = travel.get(i);
        }

        switch (option) {
            case 0:
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length - 1; j++) {
                        if (array[j].getTotallyDistance() > array[j + 1].getTotallyDistance()) {
                            OrderedCellList temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }

                break;
            case 1:
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length - 1; j++) {
                        if (array[j].getTotallyPersonalWearing() < array[j + 1].getTotallyPersonalWearing()) {
                            OrderedCellList temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }

                break;
            case 2:
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length - 1; j++) {
                        if (array[j].getTotallyGasUsage() > array[j + 1].getTotallyGasUsage()) {
                            OrderedCellList temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length - 1; j++) {
                        if (array[j].getTotallyTimeWalk() > array[j + 1].getTotallyTimeWalk()) {
                            OrderedCellList temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                break;
            case 4:
                for (int i = 0; i < array.length; i++) {
                    for (int j = 0; j < array.length - 1; j++) {
                        if (array[j].getTotallyTimeCar() > array[j + 1].getTotallyTimeCar()) {
                            OrderedCellList temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

        sortedList = new GenericOrderedList<>(array);
        return sortedList;
    }
}