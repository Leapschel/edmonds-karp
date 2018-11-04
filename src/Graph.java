import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> node_to_edges;
    private int delta;

    public Graph(int V) {
        this.node_to_edges = new HashMap<>();
        // adding sink to the network
        node_to_edges.put(V-1, new ArrayList<>());
    }

    public void addEdge(Edge edge){
        node_to_edges.putIfAbsent(edge.getFrom(), new ArrayList<>());
        node_to_edges.get(edge.getFrom()).add(edge);
    }

    public int numberOfVertices() {
        return node_to_edges.keySet().size();
    }

    // return only those vertices where the edge leading to it has residual capacity
    public List<Integer> getAdjacentVertices(int v, int delta) {
        List<Integer> adjacent = new ArrayList<>();
        for (Edge edge : node_to_edges.get(v)) {

            if(edge.getResidualCapacity() > 0 && edge.getResidualCapacity() >= delta) adjacent.add(edge.getTo());
        }
        return adjacent;
    }

    public Edge getEdge(int v, int w) {
        for(Edge edge : node_to_edges.get(v)) {
            if(edge.getTo() == w) return edge;
        }
        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for(Integer node : node_to_edges.keySet()) {
            s.append("Node: ").append(node);
            s.append("\n");
            for(Edge e : node_to_edges.get(node)) {
                s.append(e.toString()).append(", ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void setDelta(int delta){
        this.delta = delta;
    }

    public int getDelta(){
        return delta;
    }
}