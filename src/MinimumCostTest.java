import java.util.Arrays;

public class MinimumCostTest {

    public static void main(String[] args) {
        testMinimumTotalCostCalculation();
    }

    public static void testMinimumTotalCostCalculation() {
        // Test case 1
        int numberOfNodes = 5;
        int numberOfEdges = 4;
        int parkingFee = 20;
        int[] capacities = {1, 2, 1, 1, 2};
        int[][] edges = {{0, 1, 2}, {3, 4, 1}, {2, 3, 2}, {0, 2, 1}};
        int numberOfVehicles = 5;

        int[] expectedCosts = {20, 21, 22, 22, 23};

        Graph graph = new Graph(numberOfNodes);

        // Set capacities for each node
        for (int i = 0; i < numberOfNodes; i++) {
            graph.setCapacity(i, capacities[i]);
        }

        // Create edges with their costs
        for (int[] edge : edges) {
            int sourceNode = edge[0];
            int destNode = edge[1];
            int cost = edge[2];
            graph.addEdge(sourceNode, destNode, cost);
        }

        int[] totalCosts = Main.calculateMinimumTotalCost(graph, parkingFee, numberOfVehicles);

        System.out.println("Test Case 1:");
        String expectedResult = Arrays.toString(expectedCosts);
        String actualResult = Arrays.toString(totalCosts);
        System.out.println("Expected: " + expectedResult);
        System.out.println("Actual: " + actualResult);
        if(expectedResult.equals(actualResult)){
            System.out.println("TEST PASSED");
        }else{
            System.out.println("TEST FAILED");
        }
    }
}
