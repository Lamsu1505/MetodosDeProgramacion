package programacionDinamica;

public class MinPathGrid {
    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];
        dp[0] = grid[0][0];
        // primera fila
        for (int j = 1; j < m; j++) dp[j] = dp[j-1] + grid[0][j];
        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0]; // primera columna actualización
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j], dp[j-1]) + grid[i][j];
            }
        }
        return dp[m-1];
    }

    // DP con espacio O(m)
    public static int calcularMinimoCamino(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[] dp = new int[m];

        dp[0] = grid[0][0];
        for (int j = 1; j < m; j++) dp[j] = dp[j - 1] + grid[0][j];

        for (int i = 1; i < n; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < m; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1];
    }

    // prueba
    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        // ruta mínima 1->1->1->1->1 = 7
        System.out.println("Min path sum: " + minPathSum(grid)); // espera 7
    }
}

