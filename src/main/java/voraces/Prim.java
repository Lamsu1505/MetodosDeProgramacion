package voraces;

import java.util.*;

public class Prim {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    public static int prim(int n, List<List<Edge>> adj) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // pq elementos: [peso, nodo]
        pq.offer(new int[]{0, 0}); // empezar en 0
        int total = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int w = cur[0], u = cur[1];
            if (visited[u]) continue;
            visited[u] = true;
            total += w;
            for (Edge e : adj.get(u)) {
                if (!visited[e.to]) pq.offer(new int[]{e.w, e.to});
            }
        }
        return total;
    }

    // ejemplo
    public static void main(String[] args) {
        int n = 4;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        // construir grafo no dirigido
        addEdge(adj,0,1,10); addEdge(adj,0,2,6); addEdge(adj,0,3,5); addEdge(adj,1,3,15); addEdge(adj,2,3,4);
        System.out.println("Prim MST peso = " + prim(n, adj)); // espera 19
    }
    static void addEdge(List<List<Edge>> adj, int u, int v, int w) {
        adj.get(u).add(new Edge(v,w));
        adj.get(v).add(new Edge(u,w));
    }
}

