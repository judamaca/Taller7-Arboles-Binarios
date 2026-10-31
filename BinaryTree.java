import java.util.Scanner;
import java.util.ArrayList;

public class BinaryTree {
    Scanner input = new Scanner(System.in);

    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    /*
    IMPORTANTE: respete EXACTAMENTE los nombres y firmas de los metodos que se
    piden abajo; la revision automatica los busca por nombre. Puede agregar
    todos los metodos auxiliares (privados) que necesite.
    */


    // 1. Construccion del arbol: implemente aqui

    public void insertRoot(Object data) {
        if (root == null) {
            root = new TreeNode(data);
            System.out.println("El arbol fue creado con la raíz: "+ data);
        } else {
            System.out.println("El arbol ya tiene una raíz");
        }
    }


    public TreeNode search(Object data) {
        return searchRecursivo(this.root, data);
    }

    private TreeNode searchRecursivo(TreeNode actual, Object data) {
        if (actual == null) return null;
        if (actual.data.equals(data)) return actual;
        TreeNode resultadoIzquierda =  searchRecursivo(actual.left, data);
        if (resultadoIzquierda != null) return resultadoIzquierda;
        return searchRecursivo(actual.right, data);
    }

    public boolean insertLeft(Object parent, Object data) {
        TreeNode parentNode =  search(parent);
        if (parentNode == null) return false;
        if (parentNode.left != null)  return false;
        parentNode.left = new TreeNode(data);
        return true;
    }

    public boolean insertRight(Object parent, Object data) {
        TreeNode parentNode =  search(parent);
        if (parentNode == null) return false;
        if (parentNode.right != null)  return false;
        parentNode.right = new TreeNode(data);
        return true;
    }

    public void buildTree(Scanner scanner) {
        ArrayList<TreeNode> lista = new ArrayList<>();
        int i = 0;

        System.out.println("Ingresa el valor de la raíz (o '-' para árbol vacio):");
        String valorRaiz = scanner.nextLine().trim();

        if  (valorRaiz.equals("-") || valorRaiz.equals("")) root = null;
        else {
            root = new TreeNode(valorRaiz);
            lista.add(root);
        }

        while (i < lista.size()) {
            TreeNode actual = lista.get(i);

            System.out.println("Ingresa el valor del hijo izquierdo de: " + actual.data + ". Ingresa '-' o simplemente da enter para hijo vacio): ");
            String hijoIzquierdo = scanner.nextLine().trim();

            if (!hijoIzquierdo.isEmpty() && !hijoIzquierdo.equals("-")) {
                actual.left = new TreeNode(hijoIzquierdo);
                lista.add(actual.left);
            }

            System.out.println("Ingresa el valor del hijo derecho de: " + actual.data + ". Ingresa '-' o simplemente da enter para hijo vacio): ");
            String hijoDerecho = scanner.nextLine().trim();

            if  (!hijoDerecho.isEmpty() && !hijoDerecho.equals("-")) {
                actual.right = new TreeNode(hijoDerecho);
                lista.add(actual.right);
            }
            i++;
        }
    }


    // 2. Visualizacion: implemente aqui

    public void printTree() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        System.out.println(root.data);
        printTreeAux(root.left, "", true);
        printTreeAux(root.right, "", false);
    }

    private void printTreeAux(TreeNode actual, String prefijo, boolean esIzquierdo) {
        if (actual == null) return;

        // 1. Conector y etiqueta para el nodo actual
        String conector = esIzquierdo ? "├── " : "└── ";
        String etiqueta = esIzquierdo ? "[L] " : "[R] ";

        System.out.println(prefijo + conector + etiqueta + actual.data);

        // 2. Sangría acumulada que heredan los hijos de este nodo
        String prefijoHijos = prefijo + (esIzquierdo ? "│   " : "    ");

        printTreeAux(actual.left, prefijoHijos, true);
        printTreeAux(actual.right, prefijoHijos, false);
    }

    /*
    3. Recorridos: implemente aqui. Cada recorrido debe ser una funcion
    RECURSIVA independiente (por ejemplo, un metodo privado que reciba un
    TreeNode). Los metodos publicos devuelven la secuencia separada por
    espacios, p.ej. "A B D H I E C F G".

    public String preorder()
    public String inorder()
    public String postorder()
    */

    /*
    4. Factores de equilibrio: implemente aqui

    public int height(TreeNode node)
        Altura del subarbol que empieza en 'node' (null tiene altura 0 y una
        hoja altura 1).

    public int balanceFactor(TreeNode node)
        Factor de equilibrio de 'node': height(node.right) - height(node.left).

    public boolean isBalanced()
        true si TODOS los nodos tienen factor de equilibrio -1, 0 o 1.

    public List<Object> unbalancedNodes()
        Lista con los datos de los nodos cuyo factor de equilibrio es menor
        que -1 o mayor que 1 (los que requieren balanceo).

    public void printBalanceFactors()
        Muestra el arbol en consola indicando en cada nodo su factor de
        equilibrio.
    */

    /*
    5. Balanceo AVL: implemente aqui. Cada rotacion recibe la raiz del
    subarbol desbalanceado y devuelve la NUEVA raiz de ese subarbol.

    public TreeNode rotateRight(TreeNode node)       rotacion simple a la derecha
    public TreeNode rotateLeft(TreeNode node)        rotacion simple a la izquierda
    public TreeNode rotateLeftRight(TreeNode node)   rotacion doble izquierda-derecha
    public TreeNode rotateRightLeft(TreeNode node)   rotacion doble derecha-izquierda

    public void balance()
        Identifica los nodos desbalanceados y aplica las rotaciones necesarias
        hasta que el arbol sea AVL (actualiza 'root' si cambia). Debe mostrar
        el arbol con sus factores de equilibrio antes y despues de balancear.
    */
}
