package srbn.DataStructures.Tree;

import srbn.DataStructures.Lists.CellList.OrderedCellList;

public class TreeB<T> {
    private NodeTreeB<T> root;
    private int degree;

    public TreeB(int degree) {
        if (degree < 2) {
            throw new IllegalArgumentException("Degree must be at least 2.");
        }
        this.degree = degree;
        this.root = new NodeTreeB<T>();
    }

    public void insert(T value) {
        NodeTreeB<T> r = this.root;
        if (r.keys.size() == (2 * degree - 1)) {
            NodeTreeB<T> s = new NodeTreeB<>();
            this.root = s;
            s.children.add(r);
            splitChild(s, 0);
            insertNonFull(s, value);
        } else {
            insertNonFull(r, value);
        }
    }

    private void insertNonFull(NodeTreeB<T> node, T value) {
        int i = node.keys.size() - 1;
        if (node.isLeaf()) {
            node.keys.add(null);
            while (i >= 0 && value.equals(node.keys.get(i))) {
                node.keys.set(i + 1, node.keys.get(i));
                i--;
            }
            node.keys.set(i + 1, value);
        } else {
            while (i >= 0 && value.equals(node.keys.get(i))) {
                i--;
            }
            i++;
            if (node.children.get(i).keys.size() == (2 * degree - 1)) {
                splitChild(node, i);
                if (value.equals(node.keys.get(i))) {
                    i++;
                }
            }
            insertNonFull(node.children.get(i), value);
        }
    }

    private void splitChild(NodeTreeB<T> parent, int index) {
        NodeTreeB<T> node = parent.children.get(index);
        NodeTreeB<T> newNode = new NodeTreeB<>();
        newNode.parent = parent;

        for (int i = 0; i < degree - 1; i++) {
            newNode.keys.add(node.keys.remove(degree));
        }

        if (!node.isLeaf()) {
            for (int i = 0; i < degree; i++) {
                newNode.children.add(node.children.remove(degree));
                newNode.children.get(i).parent = newNode;
            }
        }

        parent.keys.add(index, node.keys.remove(degree - 1));
        parent.children.add(index + 1, newNode);
    }

//    NodeTreeB root;
//    int t;
//
//    //Constructor
//    public TreeB(int t) {
//        this.t = t;
//        root = new NodeTreeB(t);
//    }
//
//    public int buscarClaveMayor() {
//        int claveMaxima = getClaveMayor(this.root);
//
//        return claveMaxima;
//    }
//
//    private int getClaveMayor(NodeTreeB current) {
//        if (current == null) {
//            return 0;//Si es cero no existe
//        }
//
//        //Mientras no sea una hoja
//        while (!current.leaf) {
//            //Se accede al hijo mas a la derecha
//            current = current.child[current.n];
//        }
//
//        return claveMayorPorNodo(current);
//    }
//
//    private int claveMayorPorNodo(NodeTreeB current) {
//        //Devuelve el valor mayor, el que esta mas a la derecha
//        return current.key[current.n - 1];
//    }
//
//    public void mostrarClavesNodoMinimo() {
//        NodeTreeB temp = buscarNodoMinimo(root);
//
//        if (temp == null) {
//            System.out.println("Sin minimo");
//        } else {
//            temp.imprimir();
//        }
//    }
//
//    public NodeTreeB buscarNodoMinimo(NodeTreeB nodoActual) {
//        if (root == null) {
//            return null;
//        }
//
//        NodeTreeB aux = root;
//
//        //Mientras no sea una hoja
//        while (!aux.leaf) {
//            //Se accede al primer hijo
//            aux = aux.child[0];
//        }
//
//        //Devuelve el nodo menor, el que esta mas a la izquierda
//        return aux;
//    }
//
//    //Busca el valor ingresado y muestra el contenido del nodo que contiene el valor
//    public void buscarNodoPorClave(int num) {
//        NodeTreeB temp = search(root, num);
//
//        if (temp == null) {
//            System.out.println("No se ha encontrado un nodo con el valor ingresado");
//        } else {
//            print(temp);
//        }
//    }
//
//    //Search
//    private NodeTreeB search(NodeTreeB actual, int key) {
//        int i = 0;//se empieza a buscar siempre en la primera posicion
//
//        //Incrementa el indice mientras el valor de la clave del nodo sea menor
//        while (i < actual.n && key > actual.key[i]) {
//            i++;
//        }
//
//        //Si la clave es igual, entonces retornamos el nodo
//        if (i < actual.n && key == actual.key[i]) {
//            return actual;
//        }
//
//        //Si llegamos hasta aqui, entonces hay que buscar los hijos
//        //Se revisa primero si tiene hijos
//        if (actual.leaf) {
//            return null;
//        } else {
//            //Si tiene hijos, hace una llamada recursiva
//            return search(actual.child[i], key);
//        }
//    }
//
//    public void insertar(int key) {
//        NodeTreeB r = root;
//
//        //Si el nodo esta lleno lo debe separar antes de insertar
//        if (r.n == ((2 * t) - 1)) {
//            NodeTreeB s = new NodeTreeB(t);
//            root = s;
//            s.leaf = false;
//            s.n = 0;
//            s.child[0] = r;
//            split(s, 0, r);
//            nonFullInsert(s, key);
//        } else {
//            nonFullInsert(r, key);
//        }
//    }
//
//    // Caso cuando la raiz se divide
//    // x =          | | | | | |
//    //             /
//    //      |10|20|30|40|50|
//    // i = 0
//    // y = |10|20|30|40|50|
//    private void split(NodeTreeB x, int i, NodeTreeB y) {
//        //Nodo temporal que sera el hijo i + 1 de x
//        NodeTreeB z = new NodeTreeB(t);
//        z.leaf = y.leaf;
//        z.n = (t - 1);
//
//        //Copia las ultimas (t - 1) claves del nodo y al inicio del nodo z      // z = |40|50| | | |
//        for (int j = 0; j < (t - 1); j++) {
//            z.key[j] = y.key[(j + t)];
//        }
//
//        //Si no es hoja hay que reasignar los nodos hijos
//        if (!y.leaf) {
//            for (int k = 0; k < t; k++) {
//                z.child[k] = y.child[(k + t)];
//            }
//        }
//
//        //nuevo tamanio de y                                                    // x =            | | | | | |
//        y.n = (t - 1);                                                          //               /   \
//        //  |10|20| | | |
//        //Mueve los hijos de x para darle espacio a z
//        for (int j = x.n; j > i; j--) {
//            x.child[(j + 1)] = x.child[j];
//        }
//        //Reasigna el hijo (i+1) de x                                           // x =            | | | | | |
//        x.child[(i + 1)] = z;                                                   //               /   \
//        //  |10|20| | | |     |40|50| | | |
//        //Mueve las claves de x
//        for (int j = x.n; j > i; j--) {
//            x.key[(j + 1)] = x.key[j];
//        }
//
//        //Agrega la clave situada en la mediana                                 // x =            |30| | | | |
//        x.key[i] = y.key[(t - 1)];                                              //               /    \
//        x.n++;                                                                  //  |10|20| | | |      |40|50| | | |
//    }
//
//    private void nonFullInsert(NodeTreeB x, int key) {
//        //Si es una hoja
//        if (x.leaf) {
//            int i = x.n; //cantidad de valores del nodo
//            //busca la posicion i donde asignar el valor
//            while (i >= 1 && key < x.key[i - 1]) {
//                x.key[i] = x.key[i - 1];//Desplaza los valores mayores a key
//                i--;
//            }
//
//            x.key[i] = key;//asigna el valor al nodo
//            x.n++; //aumenta la cantidad de elementos del nodo
//        } else {
//            int j = 0;
//            //Busca la posicion del hijo
//            while (j < x.n && key > x.key[j]) {
//                j++;
//            }
//
//            //Si el nodo hijo esta lleno lo separa
//            if (x.child[j].n == (2 * t - 1)) {
//                split(x, j, x.child[j]);
//
//                if (key > x.key[j]) {
//                    j++;
//                }
//            }
//
//            nonFullInsert(x.child[j], key);
//        }
//    }
//
//    public void showBTree() {
//        print(root);
//    }
//
//    //Print en preorder
//    private void print(NodeTreeB n) {
//        n.imprimir();
//
//        //Si no es hoja
//        if (!n.leaf) {
//            //recorre los nodos para saber si tiene hijos
//            for (int j = 0; j <= n.n; j++) {
//                if (n.child[j] != null) {
//                    System.out.println();
//                    print(n.child[j]);
//                }
//            }
//        }
//    }

}
