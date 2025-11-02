package Dinamica;
import java.util.*;

public class MochilaMemoizacion {
    static int[][] memo;

    public static class Resultado {
        int valorMaximo;
        List<Integer> itemsSeleccionados;

        public Resultado(int valorMaximo, List<Integer> itemsSeleccionados) {
            this.valorMaximo = valorMaximo;
            this.itemsSeleccionados = itemsSeleccionados;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Valor máximo alcanzado: ").append(valorMaximo).append("\n");
            sb.append("Ítems seleccionados (índice, peso, valor):\n");
            for (int idx : itemsSeleccionados) {
                sb.append("  Ítem ").append(idx + 1).append("\n");
            }
            return sb.toString();
        }
    }

    // método principal
    public static Resultado resolverMochila(int[] peso, int[] valor, int capacidad) {
        int n = peso.length;
        memo = new int[n + 1][capacidad + 1];
        for (int[] fila : memo) Arrays.fill(fila, -1);

        int valorMaximo = knap(n - 1, capacidad, peso, valor);
        List<Integer> seleccion = reconstruirSeleccion(peso, valor, capacidad);

        return new Resultado(valorMaximo, seleccion);
    }

    private static int knap(int i, int cap, int[] peso, int[] valor) {
        if (i < 0 || cap == 0) return 0;
        if (memo[i][cap] != -1) return memo[i][cap];

        int sin = knap(i - 1, cap, peso, valor);
        int con = 0;
        if (peso[i] <= cap)
            con = valor[i] + knap(i - 1, cap - peso[i], peso, valor);

        return memo[i][cap] = Math.max(con, sin);
    }

    // reconstrucción de la selección de ítems
    private static List<Integer> reconstruirSeleccion(int[] peso, int[] valor, int capacidad) {
        List<Integer> seleccion = new ArrayList<>();
        int n = peso.length;
        int cap = capacidad;

        for (int i = n - 1; i >= 0; i--) {
            if (i == 0) {
                if (memo[i][cap] > 0) seleccion.add(i);
            } else if (memo[i][cap] != memo[i - 1][cap]) {
                seleccion.add(i);
                cap -= peso[i];
            }
        }
        Collections.reverse(seleccion);
        return seleccion;
    }
}
