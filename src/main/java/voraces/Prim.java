package Voraces;
import java.util.*;

public class Prim {

    public static class Edge {
        int to, w;
        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    public static void addEdge(List<List<Edge>> adj, int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w));
    }

    public static int primMST(int n, List<List<Edge>> adj) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0});
        int total = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int peso = curr[0], nodo = curr[1];
            if (visited[nodo]) continue;
            visited[nodo] = true;
            total += peso;

            for (Edge e : adj.get(nodo))
                if (!visited[e.to])
                    pq.offer(new int[]{e.w, e.to});
        }
        return total;
    }
}
