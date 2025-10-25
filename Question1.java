//
// Title: Critical Bridge Detection Program
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Finds all critical bridges in an island network
//

import java.util.*;

public class Question1 {
    
    /**
     * Main method to solve Question 1
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of islands and bridges
        int n = scanner.nextInt(); // Number of islands
        int m = scanner.nextInt(); // Number of bridges
        
        // Create graph
        Graph graph = new Graph(n);
        
        // Read bridges and add to graph
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }
        
        // Find all bridges
        List<int[]> bridges = graph.findBridges();
        
        // Output results
        System.out.println("Bridge list:");
        
        // Print each bridge
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        
        // Print total count
        System.out.println("Total bridges: " + bridges.size());
        
        scanner.close();
    }
}


