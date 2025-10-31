import DivideVenceras.MajorityElement;
import DivideVenceras.SmallerElementsCount;
import java.util.*;
import java.util.Arrays;

public class MainApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL - ESTRATEGIAS DE PROGRAMACIÓN =====");
            System.out.println("1. Divide y vencerás - Majority Element");
            System.out.println("2. Divide y vencerás - Smaller Elements Count");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();

            switch (option) {
                case 1 -> ejecutarMajorityElement();
                case 2 -> ejecutarSmallerElementsCount();
                case 3 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción inválida, intente nuevamente.");
            }
        } while (option != 3);
    }

    private static void ejecutarMajorityElement() {
        System.out.println("\n--- Majority Element ---");
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result = MajorityElement.findMajorityElement(arr);
        if (result != -1)
            System.out.println("Elemento mayoritario encontrado: " + result);
        else
            System.out.println("No existe un elemento mayoritario.");
    }

    private static void ejecutarSmallerElementsCount() {
        System.out.println("\n--- Smaller Elements Count ---");
        System.out.print("Ingrese el tamaño del arreglo: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Ingrese los elementos del arreglo:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] result = SmallerElementsCount.contarMenores(arr);
        System.out.println("Conteo de elementos menores a la derecha: " + Arrays.toString(result));
    }
}
