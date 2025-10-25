//
// Title: Bipartite Graph Checking Program
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Checks if museum networks are bipartite and finds odd cycles
//

import java.util.*;

public class Question2 {
    
    /**
     * Main method to solve Question 2
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of museums and corridors
        int n = scanner.nextInt(); // Number of museums
        int m = scanner.nextInt(); // Number of corridors
        
        // Create graph
        Graph graph = new Graph(n);
        
        // Read corridors and add to graph
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph.addEdge(u, v);
        }
        
        // Check bipartiteness for each connected component
        checkBipartiteComponents(graph);
        
        scanner.close();
    }
    
    /**
     * Check all connected components for bipartiteness
     * @param graph The graph to analyze
     */
    public static void checkBipartiteComponents(Graph graph) {
        int n = graph.getVertices();
        boolean[] visited = new boolean[n];
        int componentCount = 0;
        
        // Process each connected component
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                componentCount++;
                BipartiteResult result = checkBipartiteBFS(graph, i, visited);
                
                if (result.isBipartite) {
                    System.out.println("Component " + componentCount + ": Bipartite");
                } else {
                    System.out.println("Component " + componentCount + ": Not Bipartite");
                    System.out.println("Odd cycle: " + result.oddCycle);
                }
            }
        }
    }
    
    /**
     * Check if a connected component starting from vertex start is bipartite using BFS
     * @param graph The graph to analyze
     * @param start Starting vertex for BFS
     * @param visited Global visited array
     * @return BipartiteResult containing bipartiteness status and odd cycle if any
     */
    public static BipartiteResult checkBipartiteBFS(Graph graph, int start, boolean[] visited) {
        int n = graph.getVertices();
        int[] color = new int[n]; // 0 = Blue, 1 = Gold, -1 = uncolored
        int[] parent = new int[n];
        Arrays.fill(color, -1);
        Arrays.fill(parent, -1);
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        color[start] = 0; // Start with Blue
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            // Check all neighbors
            for (int v : graph.getNeighbors(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    color[v] = 1 - color[u]; // Alternate color
                    queue.offer(v);
                } else if (color[v] == color[u]) {
                    // Found edge between same-colored vertices - not bipartite
                    // Find the odd cycle
                    String oddCycle = findOddCycle(u, v, parent, start);
                    return new BipartiteResult(false, oddCycle);
                }
            }
        }
        
        return new BipartiteResult(true, "");
    }
    
    /**
     * Find an odd cycle when bipartite check fails
     * @param u First vertex of the conflicting edge
     * @param v Second vertex of the conflicting edge
     * @param parent Parent array for path reconstruction
     * @param start Starting vertex of the component
     * @return String representation of the odd cycle
     */
    public static String findOddCycle(int u, int v, int[] parent, int start) {
        // Find path from u to start
        List<Integer> pathU = new ArrayList<>();
        int current = u;
        while (current != -1) {
            pathU.add(current);
            current = parent[current];
        }
        
        // Find path from v to start
        List<Integer> pathV = new ArrayList<>();
        current = v;
        while (current != -1) {
            pathV.add(current);
            current = parent[current];
        }
        
        // Find the lowest common ancestor
        int lca = findLCA(pathU, pathV);
        
        // Build the odd cycle: u -> LCA -> v -> u
        List<Integer> cycle = new ArrayList<>();
        
        // Add path from u to LCA
        int lcaIndexU = pathU.indexOf(lca);
        for (int i = 0; i <= lcaIndexU; i++) {
            cycle.add(pathU.get(i));
        }
        
        // Add path from LCA to v (excluding LCA to avoid duplication)
        int lcaIndexV = pathV.indexOf(lca);
        for (int i = lcaIndexV - 1; i >= 0; i--) {
            cycle.add(pathV.get(i));
        }
        
        // Add u to complete the cycle
        cycle.add(u);
        
        // Convert to string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cycle.size(); i++) {
            if (i > 0) sb.append(" ");
            sb.append(cycle.get(i));
        }
        
        return sb.toString();
    }
    
    /**
     * Find the lowest common ancestor of two paths
     * @param pathU First path
     * @param pathV Second path
     * @return The lowest common ancestor vertex
     */
    public static int findLCA(List<Integer> pathU, List<Integer> pathV) {
        // Find the first common vertex when traversing from start
        for (int i = 0; i < Math.min(pathU.size(), pathV.size()); i++) {
            if (pathU.get(i).equals(pathV.get(i))) {
                return pathU.get(i);
            }
        }
        return pathU.get(0); // Fallback to start vertex
    }
    
    /**
     * Inner class to hold bipartite check result
     */
    static class BipartiteResult {
        boolean isBipartite;
        String oddCycle;
        
        public BipartiteResult(boolean isBipartite, String oddCycle) {
            this.isBipartite = isBipartite;
            this.oddCycle = oddCycle;
        }
    }
}


