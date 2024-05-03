package srbn.GraphViz;

import srbn.DataStructures.Graph.AdyMatrix;
import srbn.DataStructures.Graph.MatrixCell;
import srbn.DataStructures.Lists.CellList.OrderedCellList;
import srbn.DataStructures.Lists.GenericList.GenericOrderedList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GraphVizManager {

    public static final String GRAPH_PATH = "Graphs/";
    AdyMatrix adyMatrix;

    public GraphVizManager(String folderName, GenericOrderedList<OrderedCellList> list) {

        File dir = new File(GRAPH_PATH +folderName + "/");
        if (dir.exists()) {
            System.out.println("folder already exists");
        } else {
            dir.mkdirs();
        }

        drawPaths(folderName, list);

    }

    private void drawPaths(String path, GenericOrderedList<OrderedCellList> list) {
        String dotFile = "";

        for (int i = 0; i < list.getSize(); i++) {

            dotFile = "digraph Grafo {resolution = 110;\n" +
                    "rankdir=LR;\n" +
                    "    fontname=\"Helvetica,Arial,sans-serif\"\n" +
                    "    node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                    "    edge [fontname=\"Helvetica,Arial,sans-serif\"]\n";

            OrderedCellList cellList = list.get(i);

            for (int j = 0; j <= cellList.getSize(); j++) {
                MatrixCell cell = cellList.get(j);
                if (!cell.getDestination().getAlias().equals(cell.getOrigin().getAlias())) {
                    dotFile += cell.getOrigin().getAlias() + "->" + cell.getDestination().getAlias() + ";\n";
                }
            }


            dotFile += "\n}";
            drawGraph(GRAPH_PATH+path, path + i, path + i, dotFile);
        }

    }

    public GraphVizManager(AdyMatrix adyMatrix) {
        this.adyMatrix = adyMatrix;

        File dir = new File(GRAPH_PATH);
        if (dir.exists()) {
            System.out.println("folder already exists");
        } else {
            dir.mkdirs();
        }

    }

    private String generateDot() {
        String dotFile = "digraph Grafo {resolution = 100;\n" +
                "    fontname=\"Helvetica,Arial,sans-serif\"\n" +
                "    node [fontname=\"Helvetica,Arial,sans-serif\"]\n" +
                "    edge [fontname=\"Helvetica,Arial,sans-serif\"]\n";
        int n = adyMatrix.getSize();
        MatrixCell[][] matrixCells = adyMatrix.getMtrx();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {

                if (matrixCells[i][j] != null) {

                    dotFile += matrixCells[i][j].getOrigin().getAlias() + "->" + matrixCells[i][j].getDestination().getAlias() + ";\n";
                }
            }
        }
        return dotFile += "\n}";
    }

    private void dotFileMaker(String path, String contenido) {
        //guardamos el archivo .dot en la carpeta del sistema.
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;


        try {
            fileWriter = new FileWriter(path + ".dot");
            printWriter = new PrintWriter(fileWriter);
            printWriter.write(contenido);
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

    }

    public void drawGraph(String path, String dotName, String pngName, String content) {

        try {
            if (content != null) {
                dotFileMaker(path + "/" + dotName, content);
            } else {
                dotFileMaker(path + dotName, generateDot());
            }

            String PATH = path + "/" + dotName + ".dot";
            String FINAL = path + "/" + pngName + ".png";

            ProcessBuilder processBuilder = new ProcessBuilder("dot", "-Tpng", "-o", FINAL, PATH);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Gráfico generado");
            } else {
                System.out.println("Error al generar el gráfico.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(System.out);
        }
    }

}
