import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        int opcion = 0;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Construir arbol");
            System.out.println("2. Mostrar arbol");
            System.out.println("3. Recorrido Preorden");
            System.out.println("4. Recorrido Inorden");
            System.out.println("5. Recorrido Posorden");
            System.out.println("6. Mostrar factores de equilibrio");
            System.out.println("7. Balancear arbol (AVL)");
            System.out.println("8. Salir");
            System.out.println("==========================");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido entre 1 y 8.");
                continue;
            }

            System.out.println();
            switch (opcion) {
                case 1:
                    tree.buildTree(scanner);
                    break;

                case 2:
                    System.out.println("Estructura del árbol:");
                    tree.printTree();
                    break;

                case 3:
                    System.out.println("Recorrido Preorden:");
                    System.out.println(tree.preorder());
                    break;

                case 4:
                    System.out.println("Recorrido Inorden:");
                    System.out.println(tree.inorder());
                    break;

                case 5:
                    System.out.println("Recorrido Posorden:");
                    System.out.println(tree.postorder());
                    break;

                case 6:
                    System.out.println("Factores de equilibrio:");
                    tree.printBalanceFactors();
                    break;

                case 7:
                    tree.balance();
                    break;

                case 8:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción fuera de rango (1-8). Intente de nuevo.");
            }

        } while (opcion != 8);

        scanner.close();
    }
}
