package srbn.DataStructures.Tree;

import java.util.ArrayList;
import java.util.List;

public class NodeTreeB<T> {


    List<T> keys;
    List<NodeTreeB<T>> children;
    NodeTreeB<T> parent;

    public NodeTreeB() {
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.parent = null;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }


//    int n; //numero de claves almacenadas en el nodo
//    boolean leaf; //Si el nodo es hoja (nodo hoja=true; nodo interno=false)
//    int key[];  //almacena las claves en el nodo
//    NodeTreeB child[]; //arreglo con referencias a los hijos
//
//    //Constructores
//    public NodeTreeB(int t) {
//        n = 0;
//        leaf = true;
//        key = new int[((2 * t) - 1)];
//        child = new NodeTreeB[(2 * t)];
//    }
//
//    public void imprimir() {
//        System.out.print("[");
//        for (int i = 0; i < n; i++) {
//            if (i < n - 1) {
//                System.out.print(key[i] + " | ");
//            } else {
//                System.out.print(key[i]);
//            }
//        }
//        System.out.print("]");
//    }
//
//    public int find(int k) {
//        for (int i = 0; i < n; i++) {
//            if (key[i] == k) {
//                return i;
//            }
//        }
//        return -1;
//    }

}
