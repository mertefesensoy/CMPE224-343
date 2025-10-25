//
// Title: Question 2 Test Class
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Test cases for bipartite graph checking
//

import java.util.*;

public class Question2Tester {
    
    /**
     * Test with a bipartite graph (even cycle)
     */
    public static void testBipartiteGraph() {
        System.out.println("=== Testing Bipartite Graph (Even Cycle) ===");
        
        // Create a 4-cycle: 0-1-2-3-0 (bipartite)
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with a non-bipartite graph (odd cycle)
     */
    public static void testNonBipartiteGraph() {
        System.out.println("=== Testing Non-Bipartite Graph (Odd Cycle) ===");
        
        // Create a 3-cycle: 0-1-2-0 (not bipartite)
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with disconnected components (one bipartite, one not)
     */
    public static void testMixedComponents() {
        System.out.println("=== Testing Mixed Components ===");
        
        // Component 1: 0-1-2-3-0 (bipartite 4-cycle)
        // Component 2: 4-5-6-4 (non-bipartite 3-cycle)
        Graph graph = new Graph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 4);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with a simple bipartite graph (tree)
     */
    public static void testBipartiteTree() {
        System.out.println("=== Testing Bipartite Tree ===");
        
        // Create a tree: 0-1-2 and 0-3-4 (bipartite)
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with a single vertex
     */
    public static void testSingleVertex() {
        System.out.println("=== Testing Single Vertex ===");
        
        // Single vertex (trivially bipartite)
        Graph graph = new Graph(1);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with two disconnected vertices
     */
    public static void testTwoVertices() {
        System.out.println("=== Testing Two Disconnected Vertices ===");
        
        // Two isolated vertices (both bipartite)
        Graph graph = new Graph(2);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Test with a complex non-bipartite graph
     */
    public static void testComplexNonBipartite() {
        System.out.println("=== Testing Complex Non-Bipartite Graph ===");
        
        // Create a graph with a 5-cycle (odd cycle)
        Graph graph = new Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        
        Question2.checkBipartiteComponents(graph);
        System.out.println();
    }
    
    /**
     * Run all test cases
     */
    public static void main(String[] args) {
        testBipartiteGraph();
        testNonBipartiteGraph();
        testMixedComponents();
        testBipartiteTree();
        testSingleVertex();
        testTwoVertices();
        testComplexNonBipartite();
    }
}
