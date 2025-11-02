package voraces;

import java.util.*;

public class Prim {

    // Clase interna para representar una arista
    public static class Edge {
        int to, w;
        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    // Método para agregar aristas (grafo no dirigido)
    public static void addEdge(List<List<Edge>> adj, int u, int v, int w) {
        adj.get(u).add(new Edge(v, w));
        adj.get(v).add(new Edge(u, w));
    }

    // ===== MÉTODO PRINCIPAL SOLICITADO =====
    // Calcula el peso total del Árbol de Recubrimiento Mínimo usando Prim
    public static int primMST(int n, List<List<Edge>> adj) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0])); // [peso, nodo]
        pq.offer(new int[]{0, 0}); // empezar desde el nodo 0
        int total = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int peso = curr[0];
            int nodo = curr[1];

            if (visited[nodo]) continue;
            visited[nodo] = true;
            total += peso;

            for (Edge e : adj.get(nodo)) {
                if (!visited[e.to]) {
                    pq.offer(new int[]{e.w, e.to});
                }
            }
        }

        return total;
    }

    // ===== EJEMPLO DE PRUEBA =====
    public static void main(String[] args) {
        int n = 4;
        List<List<Edge>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // construir grafo no dirigido
        addEdge(adj, 0, 1, 10);
        addEdge(adj, 0, 2, 6);
        addEdge(adj, 0, 3, 5);
        addEdge(adj, 1, 3, 15);
        addEdge(adj, 2, 3, 4);

        int resultado = primMST(n, adj);
        System.out.println("Peso total del MST (Prim): " + resultado); // espera 19
    }
}
