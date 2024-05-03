package srbn.FilesManagement;

import srbn.DataStructures.Graph.AdyMatrix;
import srbn.DataStructures.Graph.GraphManager;
import srbn.DataStructures.Lists.CellList.OrderedCellList;
import srbn.GraphViz.GraphVizManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private AdyMatrix adyMatrix;
    private GraphManager graphManager;

    public FileManager(AdyMatrix adyMatrix) {
        this.adyMatrix = adyMatrix;
        this.graphManager = new GraphManager();
    }

    public AdyMatrix readLocationsFile(File file) {

        OrderedCellList cellList = new OrderedCellList();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");

                if (fields.length == 7) {

                    cellList.add(graphManager.newCell(fields));

                } else {
                    System.err.println("Error: La línea no tiene el formato esperado.");
                }
            }

            adyMatrix = new AdyMatrix(graphManager.getTotalNodes());
            adyMatrix.setCells(graphManager.getNodes());
            adyMatrix.setCells(cellList);
            adyMatrix.setNodesList(graphManager.getNodes());
            new GraphVizManager(adyMatrix).drawGraph("Graphs/", "grafo", "full_map", null);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return adyMatrix;

    }

    public AdyMatrix readTrafficFile(File file, AdyMatrix matrix) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\\|");

                if (fields.length == 5) {

//                    cellList.add(graphManager.newCell(fields));
                    graphManager.setTrafic(matrix, fields);

                } else {
                    System.err.println("Error: La línea no tiene el formato esperado.");
                }
            }


        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return matrix;
    }

    public void saveFile(File file) {

    }

    private void saveLocationsJson() {

    }

    public void getLocations() {

    }

    public void getRoutes() {

    }

}
