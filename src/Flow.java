import java.util.*;

public class Flow {
    public static List<Edge> getMinimumCut(Graph network) {
        int flow = 0;

        int delta = network.getDelta();

        int[] edgeTo = BFS.run(network, 0, delta);

          while(delta >=1) {

            while (edgeTo[edgeTo.length - 1] != -1) {
                List<Edge> path = BFS.getPath(edgeTo, network);
                flow = augment(flow, path);
                edgeTo = BFS.run(network, 0, delta);
            }

           delta = delta/2;
           edgeTo = BFS.run(network, 0, delta);
       }

        // debugging
        System.out.println("MaxFlow: " + flow);
        return getMinCut(network, edgeTo);
    }

    public static int augment(int flow, List<Edge> path){
        int bottleneck = Integer.MAX_VALUE;

        //find the bottleneck
        for(Edge edge : path) {
            if(edge.getResidualCapacity() < bottleneck) {
                bottleneck = edge.getResidualCapacity();
            }
        }

        // modify flow on all edges
        for(Edge edge : path) {
            edge.incrementFlow(bottleneck);
        }

        return (flow + bottleneck);
    }

    private static List<Edge> getMinCut(Graph network, int[] edgeTo) {
        List<Integer> sourceCut = new ArrayList<>();
        List<Integer> sinkCut = new ArrayList<>();

        sourceCut.add(0);
        for(int i = 1; i < edgeTo.length; i++) {
            if(edgeTo[i] != -1) sourceCut.add(i);
            else sinkCut.add(i);
        }

        List<Edge> crossingEdges = new ArrayList<>();
        for(int v : sourceCut) {
            for(int w : sinkCut) {

                Edge cuttingEdge = network.getEdge(v, w);
                if(cuttingEdge != null && cuttingEdge.getFlow() != 0) {
                    crossingEdges.add(cuttingEdge);
                }
            }
        }

        return crossingEdges;
    }

    // for debugging
    private static void printPath(List<Edge> path) {
        String delim = "";
        System.out.print("Path: {");
        for(Edge edge : path) {
            System.out.print(delim + edge);
            delim = " --> ";
        }
        System.out.print("}\n");
    }

    // for debugging
    private static void printEdgesAndPartnersOnPath(List<Edge> path) {
        for(Edge edge : path) {
            System.out.println("Edge: " + edge + " has residual capacity = " + edge.getResidualCapacity() +
                    " and flow = " + edge.getFlow());
            System.out.println("-----Partner Edge: " + edge.getPartnerEdge() + " has residual capacity = " +
                    edge.getPartnerEdge().getResidualCapacity() + " and flow  = " + edge.getPartnerEdge().getFlow());
        }
    }
}