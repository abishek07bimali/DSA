import java.util.*;

//
//To solve this problem, we can use Dijkstra's algorithm to find the cheapest route from the source node to the destination node within the given time constraint.
//
//        First, we create an Edge class to represent a route, which contains the source node, destination node,
//        and cost of the route.
//
//        We then construct a graph using an adjacency list to represent the connections between nodes,
//        with each node represented by an integer. We iterate over the input 2D array of edges and add
//        each edge to the adjacency list for its source node.
//
//        We initialize a priority queue and a visited set. The priority queue is used to keep track of the
//        nodes to visit, ordered by the time it takes to reach the node, with the node with the smallest time
//        being the first to be visited. The visited set keeps track of the nodes that have already been visited.
//
//        We add the source node to the priority queue, with its time and cost initialized to 0 and the cost of
//        the source node, respectively.
//
//        We continue to iterate over the priority queue until it is empty. For each node we visit, we check
//        if it is the destination node. If it is, we return the cost to reach it.
//
//        If the node is not the destination node, we mark it as visited and explore its neighboring nodes. For each
//        neighboring node, we calculate the time it takes to reach the node and the cost to reach the node, which is
//        the sum of the cost to reach the current node and the cost of the neighboring node.
//
//        We check if the neighboring node can be reached within the time constraint and has not been visited before.
//        If so, we add it to the priority queue.
//
//        If we have exhausted all nodes in the priority queue and have not found the destination node,
//        we return -1 to indicate that the destination node is not reachable within the given time constraint.
//
//        Finally, we call the findCheapestRoute method with the given input and print out the result.


public class Question1A {

    // Edge class to represent a route
    static class Edge {
        int source, destination, cost;
        Edge(int source, int destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }
    }

    // Method to find the cheapest route
    public static int findCheapestRoute(int[][] edges, int[] charges, int source, int destination, int timeConstraint) {
        // Construct graph using adjacency list
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int sources = edge[0];
            int destinations = edge[1];
            int cost = edge[2];
            graph.computeIfAbsent(sources, k -> new ArrayList<>()).add(new Edge(source, destinations, cost));
        }

        // Initialize priority queue and visited set
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [node, time]
        Set<Integer> visited = new HashSet<>();

        // Add source node to priority queue
        pq.offer(new int[]{source, 0, charges[source]}); // [node, time, cost]

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int time = curr[1];
            int cost = curr[2];

            // If destination reached, return cost
            if (node == destination) {
                return cost;
            }

            // Mark node as visited
            visited.add(node);

            // Explore neighboring nodes
            if (graph.containsKey(node)) {
                for (Edge edge : graph.get(node)) {
                    int nextNode = edge.destination;
                    int nextTime = time + edge.cost;
                    int nextCost = cost + charges[nextNode];

                    // Check if next node can be visited within time constraint and has not been visited before
                    if (nextTime <= timeConstraint && !visited.contains(nextNode)) {
                        pq.offer(new int[]{nextNode, nextTime, nextCost});
                    }
                }
            }
        }

        // If destination not reachable, return -1
        return -1;
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Example input values
        int[][] edges = {{0,1,5}, {0,3,2}, {1,2,5}, {3,4,5}, {4,5,6}, {2,5,5}};
        int[] charges = {10,2,3,25,25,4};
        int source = 0;
        int destination = 5;
        int timeConstraint = 14;

        // Call the findCheapestRoute method and print the result
        int cheapestCost = findCheapestRoute(edges, charges, source, destination, timeConstraint);
        System.out.println("Cheapest route cost: " + cheapestCost);
    }
}
