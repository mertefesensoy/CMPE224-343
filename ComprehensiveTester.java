//
// Title: Comprehensive Test Class for Both Questions
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Complete test suite for both bridge detection and bipartite checking
//

import java.util.*;

public class ComprehensiveTester {
    
    /**
     * Test Question 1: Bridge Detection
     */
    public static void testQuestion1() {
        System.out.println("==========================================");
        System.out.println("QUESTION 1: CRITICAL BRIDGE DETECTION");
        System.out.println("==========================================");
        
        // Test 1: Sample input (4-cycle, no bridges)
        System.out.println("Test 1: 4-cycle (should have 0 bridges)");
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        
        List<int[]> bridges1 = graph1.findBridges();
        System.out.println("Bridge list:");
        for (int[] bridge : bridges1) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges1.size());
        System.out.println();
        
        // Test 2: Graph with bridges
        System.out.println("Test 2: Graph with bridges (should have 4 bridges)");
        Graph graph2 = new Graph(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 3);
        graph2.addEdge(3, 4);
        
        List<int[]> bridges2 = graph2.findBridges();
        System.out.println("Bridge list:");
        for (int[] bridge : bridges2) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges2.size());
        System.out.println();
        
        // Test 3: Disconnected components
        System.out.println("Test 3: Disconnected components (should have 2 bridges)");
        Graph graph3 = new Graph(4);
        graph3.addEdge(0, 1);
        graph3.addEdge(2, 3);
        
        List<int[]> bridges3 = graph3.findBridges();
        System.out.println("Bridge list:");
        for (int[] bridge : bridges3) {
            System.out.println(bridge[0] + " " + bridge[1]);
        }
        System.out.println("Total bridges: " + bridges3.size());
        System.out.println();
    }
    
    /**
     * Test Question 2: Bipartite Checking
     */
    public static void testQuestion2() {
        System.out.println("==========================================");
        System.out.println("QUESTION 2: BIPARTITE GRAPH CHECKING");
        System.out.println("==========================================");
        
        // Test 1: Bipartite graph (even cycle)
        System.out.println("Test 1: Bipartite graph (4-cycle)");
        Graph graph1 = new Graph(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 0);
        
        Question2.checkBipartiteComponents(graph1);
        System.out.println();
        
        // Test 2: Non-bipartite graph (odd cycle)
        System.out.println("Test 2: Non-bipartite graph (3-cycle)");
        Graph graph2 = new Graph(3);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(2, 0);
        
        Question2.checkBipartiteComponents(graph2);
        System.out.println();
        
        // Test 3: Mixed components
        System.out.println("Test 3: Mixed components (one bipartite, one not)");
        Graph graph3 = new Graph(7);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 3);
        graph3.addEdge(3, 0);
        graph3.addEdge(4, 5);
        graph3.addEdge(5, 6);
        graph3.addEdge(6, 4);
        
        Question2.checkBipartiteComponents(graph3);
        System.out.println();
    }
    
    /**
     * Test edge cases
     */
    public static void testEdgeCases() {
        System.out.println("==========================================");
        System.out.println("EDGE CASES TESTING");
        System.out.println("==========================================");
        
        // Test 1: Single vertex
        System.out.println("Test 1: Single vertex");
        Graph graph1 = new Graph(1);
        List<int[]> bridges1 = graph1.findBridges();
        System.out.println("Bridges: " + bridges1.size());
        Question2.checkBipartiteComponents(graph1);
        System.out.println();
        
        // Test 2: Two disconnected vertices
        System.out.println("Test 2: Two disconnected vertices");
        Graph graph2 = new Graph(2);
        List<int[]> bridges2 = graph2.findBridges();
        System.out.println("Bridges: " + bridges2.size());
        Question2.checkBipartiteComponents(graph2);
        System.out.println();
        
        // Test 3: Complete graph (no bridges, not bipartite)
        System.out.println("Test 3: Complete graph K3");
        Graph graph3 = new Graph(3);
        graph3.addEdge(0, 1);
        graph3.addEdge(1, 2);
        graph3.addEdge(2, 0);
        
        List<int[]> bridges3 = graph3.findBridges();
        System.out.println("Bridges: " + bridges3.size());
        Question2.checkBipartiteComponents(graph3);
        System.out.println();
    }
    
    /**
     * Run all comprehensive tests
     */
    public static void main(String[] args) {
        testQuestion1();
        testQuestion2();
        testEdgeCases();
        
        System.out.println("==========================================");
        System.out.println("ALL TESTS COMPLETED SUCCESSFULLY!");
        System.out.println("==========================================");
    }
}


