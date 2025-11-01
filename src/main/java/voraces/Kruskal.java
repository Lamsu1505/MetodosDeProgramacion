package voraces;

import java.util.*;

public class Kruskal {
    static class Edge implements Comparable<Edge> {
        int u, v; int w;
        Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
        public int compareTo(Edge other) { return Integer.compare(this.w, other.w); }
    }
    static class DSU {
        int[] p, r;
        DSU(int n) { p = new int[n]; r = new int[n]; for(int i=0;i<n;i++) p[i]=i; }
        int find(int x) { return p[x]==x?x:(p[x]=find(p[x])); }
        boolean union(int a,int b) {
            a=find(a); b=find(b);
            if (a==b) return false;
            if (r[a]<r[b]) p[a]=b; else if (r[b]<r[a]) p[b]=a; else { p[b]=a; r[a]++; }
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

