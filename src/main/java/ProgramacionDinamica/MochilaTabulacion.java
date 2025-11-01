package ProgramacionDinamica;

import java.util.*;

public class MochilaTabulacion {
    public static int knap(int[] wt, int[] val, int W) {
        int n = wt.length;
        int[][] dp = new int[n+1][W+1];
        for (int i = 1; i <= n; i++) {
            for (int cap = 0; cap <= W; cap++) {
                dp[i][cap] = dp[i-1][cap];
                if (wt[i-1] <= cap) {
                    dp[i][cap] = Math.max(dp[i][cap], val[i-1] + dp[i-1][cap - wt[i-1]]);
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] wt = {10, 20, 30};
        int[] val = {60, 100, 120};
        int W = 50;
        System.out.println("BottomUp knap = " + knap(wt, val, W)); // 220
    }
}

