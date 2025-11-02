import DivideVenceras.*;
import Voraces.*;
import Dinamica.*;

import java.util.*;
//Prueba
public class MainApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL - ESTRATEGIAS DE PROGRAMACIÓN =====");
            System.out.println("1. Divide y vencerás - Majority Element");
            System.out.println("2. Divide y vencerás - Smaller Elements Count");
            System.out.println("3. Voraces - Mochila Fraccionaria");
            System.out.println("4. Voraces - Árbol de Recubrimiento Mínimo (Kruskal)");
            System.out.println("5. Voraces - Árbol de Recubrimiento Mínimo (Prim)");
            System.out.println("6. Dinámica - Camino Mínimo");
            System.out.println("7. Dinámica - Mochila 0/1 (Memoización)");
            System.out.println("8. Dinámica - Mochila 0/1 (Tabulación)");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();

            switch (option) {
                case 1 -> ejecutarMajorityElement();
                case 2 -> ejecutarSmallerElementsCount();
                case 3 -> ejecutarMochilaFraccionaria();
                case 4 -> ejecutarKruskal();
                case 5 -> ejecutarPrim();
                case 6 -> ejecutarCaminoMinimo();
                case 7 -> ejecutarMochilaMemoizacion();
                case 8 -> ejecutarMochilaTabulacion();
                case 9 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (option != 9);
    }

    // ----- 1 -----
    private static void ejecutarMajorityElement() {
        System.out.println("\n--- Majority Element ---");
        System.out.print("Tamaño del arreglo: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int result = MajorityElement.findMajorityElement(arr);
        System.out.println(result != -1 ? "Elemento mayoritario: " + result : "No existe elemento mayoritario.");
    }

    // ----- 2 -----
    private static void ejecutarSmallerElementsCount() {
        System.out.println("\n--- Smaller Elements Count ---");
        System.out.print("Tamaño del arreglo: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] res = SmallerElementsCount.contarMenores(arr);
        System.out.println("Conteos: " + Arrays.toString(res));
    }

    // ----- 3 -----
    private static void ejecutarMochilaFraccionaria() {
        System.out.println("\n--- Mochila Fraccionaria ---");
        System.out.print("Número de ítems: ");
        int n = sc.nextInt();
        MochilaFraccionaria.Item[] items = new MochilaFraccionaria.Item[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Peso y valor del ítem " + (i + 1) + ": ");
            int p = sc.nextInt();
            double v = sc.nextDouble();
            items[i] = new MochilaFraccionaria.Item(p, v);
        }
        System.out.print("Capacidad de la mochila: ");
        int W = sc.nextInt();
        MochilaFraccionaria.Result max = MochilaFraccionaria.resolverMochila(items, W);
        System.out.println("Valor máximo obtenido: " + max);
    }

    // ----- 4 -----
    private static void ejecutarKruskal() {
        System.out.println("\n--- Árbol de Recubrimiento Mínimo (Kruskal) ---");
        System.out.print("Número de vértices: ");
        int n = sc.nextInt();
        System.out.print("Número de aristas: ");
        int m = sc.nextInt();
        List<Kruskal.Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            System.out.print("u v w (índices desde 0): ");
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            edges.add(new Kruskal.Edge(u, v, w));
        }
        int total = Kruskal.kruskalMST(n, edges);
        System.out.println("Peso total del MST: " + total);
    }

    // ----- 5 -----
    private static void ejecutarPrim() {
        System.out.println("\n--- Árbol de Recubrimiento Mínimo (Prim) ---");
        System.out.print("Número de vértices: ");
        int n = sc.nextInt();
        System.out.print("Número de aristas: ");
        int m = sc.nextInt();
        List<List<Prim.Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < m; i++) {
            System.out.print("u v w (índices desde 0): ");
            int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt();
            Prim.addEdge(adj, u, v, w);
        }
        int total = Prim.primMST(n, adj);
        System.out.println("Peso total del MST: " + total);
    }

    // ----- 6 -----
    private static void ejecutarCaminoMinimo() {
        System.out.println("\n--- Camino Mínimo (Programación Dinámica) ---");
        System.out.print("Filas y columnas: ");
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] grid = new int[n][m];
        System.out.println("Ingrese los valores de la matriz:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        int result = MinPathGrid.calcularMinimoCamino(grid);
        System.out.println("Costo mínimo del camino: " + result);
    }

    // ----- 7 -----
    private static void ejecutarMochilaMemoizacion() {
        System.out.println("\n--- Mochila 0/1 (Memoización) ---");
        System.out.print("Número de ítems: ");
        int n = sc.nextInt();
        int[] peso = new int[n], valor = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Peso y valor del ítem " + (i + 1) + ": ");
            peso[i] = sc.nextInt();
            valor[i] = sc.nextInt();
        }
        System.out.print("Capacidad máxima: ");
        int W = sc.nextInt();
        MochilaMemoizacion.Resultado res = MochilaMemoizacion.resolverMochila(peso, valor, W);
        System.out.println(res);

    }

    // ----- 8 -----
    private static void ejecutarMochilaTabulacion() {
        System.out.println("\n--- Mochila 0/1 (Tabulación) ---");
        System.out.print("Número de ítems: ");
        int n = sc.nextInt();
        int[] peso = new int[n], valor = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Peso y valor del ítem " + (i + 1) + ": ");
            peso[i] = sc.nextInt();
            valor[i] = sc.nextInt();
        }
        System.out.print("Capacidad máxima: ");
        int W = sc.nextInt();
        MochilaTabulacion.Resultado res = MochilaTabulacion.resolverMochila(peso, valor, W);
        System.out.println(res);

    }
}
