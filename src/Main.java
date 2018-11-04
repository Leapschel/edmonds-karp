import java.util.*;

public class Main {
    public static void main(String[] args){


        Graph network = FileReader.createNetwork(args[0]);

        List<Edge> minCut = Flow.getMinimumCut(network);


        System.out.println("Minimum Cut: ");

        for(Edge e : minCut) {
            System.out.println(e.toString());
        }
       }
}
