package ProgramacionDinamica;

import java.util.*;

public class MochilaMemoizacion {
    static int[][] memo;
    public static int knap(int[] wt, int[] val, int W) {
        int n = wt.length;
        memo = new int[n+1][W+1];
        for (int i=0;i<=n;i++) Arrays.fill(memo[i], -1);
        return solve(n-1, W, wt, val);
    }
    private static int solve(int i, int cap, int[] wt, int[] val) {
        if (i < 0 || cap == 0) return 0;
        if (memo[i][cap] != -1) return memo[i][cap];
        int without = solve(i-1, cap, wt, val);
        int with = 0;
        if (wt[i] <= cap) with = val[i] + solve(i-1, cap - wt[i], wt, val);
        memo[i][cap] = Math.max(with, without);
        return memo[i][cap];
    }

    public static void main(String[] args) {
        int[] wt = {10, 20, 30};
        int[] val = {60, 100, 120};
        int W = 50;
        System.out.println("TopDown knap = " + knap(wt, val, W)); // espera 220? No, para 0/1: 160 (items 10+20 => 60+100=160) o 20+30? 20+30=50 -> 100+120=220 but weight 20+30=50 fits -> 220.
    }
}

