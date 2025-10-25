//
// Title: Question 1 Test Class
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Test cases for critical bridge detection
//

import java.util.*;

public class Question1Tester {
    
    /**
     * Test the bridge detection with sample input
     */
    public static void testSampleInput() {
        System.out.println("=== Testing Sample Input ===");
        
        // Create graph with 4 islands and 4 bridges
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        
        List<int[]> bridges = graph.findBridges();
        
        System.out.println("Bridge list:");
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges.size());
        System.out.println();
    }
    
    /**
     * Test with a graph that has bridges
     */
    public static void testWithBridges() {
        System.out.println("=== Testing Graph with Bridges ===");
        
        // Create a graph: 0-1-2-3-4 where 1-2 is a bridge
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2); // This should be a bridge
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        List<int[]> bridges = graph.findBridges();
        
        System.out.println("Bridge list:");
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges.size());
        System.out.println();
    }
    
    /**
     * Test with a graph that has no bridges (cycle)
     */
    public static void testNoBridges() {
        System.out.println("=== Testing Graph with No Bridges ===");
        
        // Create a cycle: 0-1-2-3-0
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        
        List<int[]> bridges = graph.findBridges();
        
        System.out.println("Bridge list:");
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges.size());
        System.out.println();
    }
    
    /**
     * Test with disconnected components
     */
    public static void testDisconnected() {
        System.out.println("=== Testing Disconnected Graph ===");
        
        // Two separate components: 0-1 and 2-3
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        
        List<int[]> bridges = graph.findBridges();
        
        System.out.println("Bridge list:");
        for (int[] bridge : bridges) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges.size());
        System.out.println();
    }
    
    /**
     * Run all test cases
     */
    public static void main(String[] args) {
        testSampleInput();
        testWithBridges();
        testNoBridges();
        testDisconnected();
    }
}


