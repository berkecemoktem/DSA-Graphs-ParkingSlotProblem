import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfNodes = scanner.nextInt();
        int numberOfEdges = scanner.nextInt();
        int parkingFee = scanner.nextInt();

        Graph graph = new Graph(numberOfNodes);

        // Setting the capacities for each node
        for (int i = 0; i < numberOfNodes; i++) {
            int currentCapacity = scanner.nextInt();
            graph.setCapacity(i, currentCapacity);
        }

        // Creating the edges with their costs
        for (int j = 0; j < numberOfEdges; j++) {
            int sourceNode = scanner.nextInt() - 1;
            int destNode = scanner.nextInt() - 1;
            int cost = scanner.nextInt();
            graph.addEdge(sourceNode, destNode, cost);
        }

        int numberOfVehicles = scanner.nextInt();

        int[] totalCosts = calculateMinimumTotalCost(graph, parkingFee, numberOfVehicles);
        for (int cost : totalCosts) {
            System.out.print(cost + " ");
        }
    }

    /*Using Dijkstra's Algorithm in order to find the shortest path to an available node with capacity > zero*/
    public static int[] calculateMinimumTotalCost(Graph graph, int parkingFee, int numberOfVehicles) {
        int[] distances = new int[graph.getV()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        int[] totalCosts = new int[numberOfVehicles];

        distances[0] = 0;

        for (int vehicle = 0; vehicle < numberOfVehicles; vehicle++) {
            int minDistance = Integer.MAX_VALUE;
            int minNode = -1;

            // Find the node with the minimum distance
            for (int node = 0; node < graph.getV(); node++) {
                if (distances[node] < minDistance && graph.getCapacity(node) > 0) {
                    minDistance = distances[node];
                    minNode = node;
                }
            }

            if (minNode == -1) {
                totalCosts[vehicle] = -1; // If no suitable parking slot is found for the current vehicle
                continue; // Move on to the next vehicle
            }

            totalCosts[vehicle] = minDistance + parkingFee;
            graph.decrementCapacity(minNode); // Decrement the capacity of the assigned node

            // Update distances to adjacent nodes of minNode
            List<Edge> neighbors = graph.getAdjacentNodes(minNode);
            for (Edge neighbor : neighbors) {
                int nextNode = neighbor.getDestination();
                int weight = neighbor.getCost();

                if (minDistance + weight < distances[nextNode]) {
                    distances[nextNode] = minDistance + weight;
                }
            }
        }

        return totalCosts;
    }

}