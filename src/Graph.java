import java.util.ArrayList;
import java.util.List;

class Graph {
    private int V;
    private List<List<Edge>> adjacencyList;
    private int[] capacities;

    public Graph(int V) {
        this.V = V;
        adjacencyList = new ArrayList<>();
        capacities = new int[V];
        for (int i = 0; i < V; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public int getV() {
        return V;
    }

    public void setCapacity(int node, int capacity) {
        capacities[node] = capacity;
    }

    public int getCapacity(int node) {
        return capacities[node];
    }

    public void addEdge(int source, int destination, int cost) {
        Edge edge = new Edge(destination, cost);
        adjacencyList.get(source).add(edge);
        // Adding the reverse edge as well since the graph is undirected
        Edge reverseEdge = new Edge(source, cost);
        adjacencyList.get(destination).add(reverseEdge);
    }

    public List<Edge> getAdjacentNodes(int node) {
        return adjacencyList.get(node);
    }

    public void decrementCapacity(int node) {
        capacities[node]--;
    }
}