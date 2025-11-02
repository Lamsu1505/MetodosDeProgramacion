package Dinamica;

import java.util.Arrays;

public class MochilaMemoizacion {
    static int[][] memo;

    public static int resolverMochila(int[] peso, int[] valor, int capacidad) {
        int n = peso.length;
        memo = new int[n + 1][capacidad + 1];
        for (int i = 0; i <= n; i++)
            Arrays.fill(memo[i], -1);
        return knap(n - 1, capacidad, peso, valor);
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
}
