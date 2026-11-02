import java.util.List;
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

    public String preorder() {
        StringBuilder sb = new StringBuilder();
        preorderAux(root, sb);
        return sb.toString().trim();
    }

    private void preorderAux(TreeNode actual, StringBuilder sb) {
        if  (actual == null) return;
        sb.append(actual.data).append(" ");
        preorderAux(actual.left, sb);
        preorderAux(actual.right, sb);
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorderAux(root, sb);
        return sb.toString().trim();
    }

    private void inorderAux(TreeNode actual, StringBuilder sb) {
        if  (actual == null) return;
        inorderAux(actual.left, sb);
        sb.append(actual.data).append(" ");
        inorderAux(actual.right, sb);
    }

    public String postorder() {
        StringBuilder sb = new StringBuilder();
        postorderAux(root, sb);
        return sb.toString().trim();
    }

    private void postorderAux(TreeNode actual, StringBuilder sb) {
        if  (actual == null) return;
        postorderAux(actual.left, sb);
        postorderAux(actual.right, sb);
        sb.append(actual.data).append(" ");
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        int alturaIzq = height(node.left);
        int alturaDer = height(node.right);
        return 1 + Math.max(alturaIzq, alturaDer);
    }

    public int balanceFactor(TreeNode node) {
        if (node == null) return 0;
        return height(node.right) - height(node.left);
    }

    public boolean isBalanced() {
        return isBalancedAux(root);
    }

    private boolean isBalancedAux(TreeNode node) {
        if (node == null) return true;
        if (Math.abs(balanceFactor(node)) > 1) return false;
        return isBalancedAux(node.left) && isBalancedAux(node.right);
    }

    public List<Object> unbalancedNodes() {
        List<Object> lista = new ArrayList<>();
        unbalancedNodesAux(root, lista);
        return lista;
    }

    private void unbalancedNodesAux(TreeNode node,  List<Object> lista) {
        if (node == null) return;
        if (Math.abs(balanceFactor(node)) > 1) lista.add(node.data);
        unbalancedNodesAux(node.left, lista);
        unbalancedNodesAux(node.right, lista);
    }

    public void printBalanceFactors() {
        if (root == null) {
            System.out.println("El árbol está vacío");
            return;
        }
        System.out.println(root.data + " (fe = " + balanceFactor(root) + ")");
        printBalanceFactorsAux(root.left, "", true);
        printBalanceFactorsAux(root.right, "", false);
    }

    private void printBalanceFactorsAux(TreeNode actual, String prefijo, boolean esIzquierdo) {
        if (actual == null) return;

        // 1. Conector y etiqueta para el nodo actual
        String conector = esIzquierdo ? "├── " : "└── ";
        String etiqueta = esIzquierdo ? "[L] " : "[R] ";
        String fe = " (fe = " + balanceFactor(actual) + ")";

        System.out.println(prefijo + conector + etiqueta + actual.data + fe);

        // 2. Sangría acumulada que heredan los hijos de este nodo
        String prefijoHijos = prefijo + (esIzquierdo ? "│   " : "    ");

        printBalanceFactorsAux(actual.left, prefijoHijos, true);
        printBalanceFactorsAux(actual.right, prefijoHijos, false);
    }

    public TreeNode rotateRight(TreeNode node) {
        if (node == null) return null;

        TreeNode auxNewRoot = node.left;
        TreeNode auxNode = auxNewRoot.right;

        auxNewRoot.right = node;
        node.left = auxNode;
        return auxNewRoot;
    }

    public TreeNode rotateLeft(TreeNode node) {
        if (node == null) return null;

        TreeNode auxNewRoot = node.right;
        TreeNode auxNode = auxNewRoot.left;

        auxNewRoot.left = node;
        node.right = auxNode;
        return auxNewRoot;
    }

    public TreeNode rotateLeftRight(TreeNode node) {
        if (node == null) return null;
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    public TreeNode rotateRightLeft(TreeNode node) {
        if (node == null) return null;
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    public void balance() {
        System.out.println("=== ÁRBOL ANTES DEL BALANCEO ===");
        printBalanceFactors();

        root = balanceAux(root);

        System.out.println("\n=== ÁRBOL DESPUÉS DEL BALANCEO ===");
        printBalanceFactors();
    }

    private TreeNode balanceAux(TreeNode actual) {
        if (actual == null) return null;

        actual.left = balanceAux(actual.left);
        actual.right = balanceAux(actual.right);

        int fe = balanceFactor(actual);

        if (fe < -1) {
            if (balanceFactor(actual.left) <= 0) {
                actual = rotateRight(actual);
            } else {
                actual = rotateLeftRight(actual);
            }
        }

        if (fe > 1) {
            if (balanceFactor(actual.right) >= 0) {
                actual = rotateLeft(actual);
            } else {
                actual = rotateRightLeft(actual);
            }
        }
        return actual;
    }

}
