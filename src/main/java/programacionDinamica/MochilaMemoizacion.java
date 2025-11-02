package programacionDinamica;

import java.util.Arrays;

public class MochilaMemoizacion {
    static int[][] memo;

    // Método público llamado desde Main
    public static int resolverMochila(int[] peso, int[] valor, int capacidad) {
        int n = peso.length;
        memo = new int[n + 1][capacidad + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(memo[i], -1);
        return knap(n - 1, capacidad, peso, valor);
    }

    // Método recursivo con memoización (Top-Down)
    private static int knap(int i, int cap, int[] peso, int[] valor) {
        // Caso base: sin ítems o sin capacidad
        if (i < 0 || cap == 0) return 0;

        // Revisar si ya está calculado
        if (memo[i][cap] != -1) return memo[i][cap];

        // Opción 1: no tomar el ítem actual
        int sin = knap(i - 1, cap, peso, valor);

        // Opción 2: tomar el ítem actual (si cabe)
        int con = 0;
        if (peso[i] <= cap)
            con = valor[i] + knap(i - 1, cap - peso[i], peso, valor);

        // Guardar el resultado en la tabla
        memo[i][cap] = Math.max(con, sin);
        return memo[i][cap];
    }

    // Prueba de escritorio
    public static void main(String[] args) {
        int[] peso = {10, 20, 30};
        int[] valor = {60, 100, 120};
        int capacidad = 50;

        int resultado = resolverMochila(peso, valor, capacidad);
        System.out.println("Valor máximo (Memoización): " + resultado); // Debería imprimir 220
    }
}
