package voraces;

import java.util.*;

public class Kruskal {
    public static class Edge implements Comparable<Edge> {
        int u, v; int w;
        public Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge other) { return Integer.compare(this.w, other.w); }
    }
    static class DSU {
        int[] parent, rank;
        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            return parent[x] == x ? x : (parent[x] = find(parent[x]));
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;
            if (rank[a] < rank[b]) parent[a] = b;
            else if (rank[b] < rank[a]) parent[b] = a;
            else { parent[b] = a; rank[a]++; }
            return true;
        }
    }

    public static int kruskal(int n, List<Edge> edges) {
        Collections.sort(edges);
        DSU dsu = new DSU(n);
        int total = 0; int cnt = 0;
        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                total += e.w; cnt++;
                if (cnt == n-1) break;
            }
        }
        return total;
    }

    // Método solicitado en el main
    public static int kruskalMST(int n, List<Edge> edges) {
        Collections.sort(edges);
        DSU dsu = new DSU(n);
        int total = 0;
        int count = 0;

        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                total += e.w;
                count++;
                if (count == n - 1) break;
            }
        }
        return total;
    }

    // ejemplo
    public static void main(String[] args) {
        int n = 4;
        List<Edge> edges = Arrays.asList(
                new Edge(0,1,10), new Edge(0,2,6), new Edge(0,3,5),
                new Edge(1,3,15), new Edge(2,3,4)
        );
        System.out.println("Kruskal MST peso = " + kruskal(n, edges)); // espera 19
    }
}

