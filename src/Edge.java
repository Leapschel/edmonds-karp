public class Edge {
    private int from;
    private int to;
    private int capacity;
    private Edge partnerEdge;
    private boolean residualEdge;
    private int flow;

    public Edge(int v, int w, int c, boolean res) {
        this.from = v;
        this.to = w;
        this.capacity = c;
        this.residualEdge = res;
        this.flow = 0;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity( int newCapacity) {
        this.capacity = newCapacity;
    }

    public void addPartnerEdge(Edge partner) {
        this.partnerEdge = partner;
    }

    public int getResidualCapacity() {
        return capacity - flow;
    }

    public Edge getPartnerEdge() {
        return partnerEdge;
    }

    public int getFlow() {
        return flow;
    }

    public void incrementFlow(int flow) {
        this.flow += flow;
        if(!residualEdge) this.getPartnerEdge().setCapacity(flow);
    }

    public String toString() {
        return "" + from + " " + to ;
    }
}