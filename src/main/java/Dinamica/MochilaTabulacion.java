package Dinamica;
import java.util.*;

public class MochilaTabulacion {

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

    public static Resultado resolverMochila(int[] peso, int[] valor, int capacidad) {
        int n = peso.length;
        int[][] dp = new int[n + 1][capacidad + 1];

        // Llenado de la tabla
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacidad; w++) {
                dp[i][w] = dp[i - 1][w];
                if (peso[i - 1] <= w)
                    dp[i][w] = Math.max(dp[i][w], valor[i - 1] + dp[i - 1][w - peso[i - 1]]);
            }
        }

        // Reconstrucción de los ítems elegidos
        List<Integer> seleccion = new ArrayList<>();
        int w = capacidad;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                seleccion.add(i - 1);
                w -= peso[i - 1];
            }
        }
        Collections.reverse(seleccion);

        return new Resultado(dp[n][capacidad], seleccion);
    }
}
