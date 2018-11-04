import java.util.*;

//modified from Sedgewick and Wayne
public class BFS {
    public static int[] run(Graph network, int source, int delta) {

        int V = network.numberOfVertices();
        boolean[] visited = new boolean[V];
        int[] edgeTo = new int[V];

        for (int v = 0; v < V; v++) {
            edgeTo[v] = -1;
        }

        bfs(network, source, visited, edgeTo, delta);
        return edgeTo;
    }

    private static void bfs(Graph network, int source, boolean[] visited, int[] edgeTo, int delta) {
        Queue<Integer> q = new LinkedList<>();
        visited[source] = true;
        q.add(source);

        while (!q.isEmpty()) {
            int v = q.poll();

            // we ignore the neighbor which has not the right treshold
            for (int w : network.getAdjacentVertices(v, delta)) {

                if (!visited[w]) {
                    edgeTo[w] = v;
                    visited[w] = true;
                    q.add(w);
                }
            }
        }
    }

    public static List<Edge> getPath(int[] edgeTo, Graph network) {

        LinkedList<Edge> path = new LinkedList<>();

        int v = edgeTo.length - 1;
        while(v != 0) {
            int w = edgeTo[v];
            Edge edge = network.getEdge(w, v);
            path.addFirst(edge);
            v = w;
        }
        return path;
    }
}