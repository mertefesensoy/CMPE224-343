//
// Title: Graph Data Structure for Bridge Detection
// Author: [Your Name]
// ID: [Your ID]
// Section: [Your Section]
// Assignment: 1
// Description: Custom graph implementation using adjacency lists for bridge detection
//

import java.util.*;

public class Graph {
    private int vertices;
    private List<List<Integer>> adjacencyList;
    private int time; // For DFS timing
    
    /**
     * Constructor to initialize the graph
     * @param vertices Number of vertices in the graph
     */
    public Graph(int vertices) {
        this.vertices = vertices;
        this.time = 0;
        this.adjacencyList = new ArrayList<>();
        
        // Initialize adjacency list for each vertex
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }
    
    /**
     * Add an undirected edge between two vertices
     * @param u First vertex
     * @param v Second vertex
     */
    public void addEdge(int u, int v) {
        adjacencyList.get(u).add(v);
        adjacencyList.get(v).add(u);
    }
    
    /**
     * Get neighbors of a vertex
     * @param vertex The vertex to get neighbors for
     * @return List of neighbor vertices
     */
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }
    
    /**
     * Get total number of vertices
     * @return Number of vertices
     */
    public int getVertices() {
        return vertices;
    }
    
    /**
     * Find all bridges in the graph using Tarjan's algorithm
     * @return List of bridges as pairs [u, v] where u < v
     */
    public List<int[]> findBridges() {
        List<int[]> bridges = new ArrayList<>();
        boolean[] visited = new boolean[vertices];
        int[] discovery = new int[vertices];
        int[] low = new int[vertices];
        int[] parent = new int[vertices];
        
        // Initialize arrays
        Arrays.fill(discovery, -1);
        Arrays.fill(low, -1);
        Arrays.fill(parent, -1);
        
        // Run DFS from each unvisited vertex
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsBridge(i, visited, discovery, low, parent, bridges);
            }
        }
        
        // Sort bridges lexicographically
        bridges.sort((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });
        
        return bridges;
    }
    
    /**
     * DFS helper method for bridge detection
     * @param u Current vertex
     * @param visited Visited array
     * @param discovery Discovery time array
     * @param low Low value array
     * @param parent Parent array
     * @param bridges List to store found bridges
     */
    private void dfsBridge(int u, boolean[] visited, int[] discovery, 
                          int[] low, int[] parent, List<int[]> bridges) {
        visited[u] = true;
        discovery[u] = low[u] = ++time;
        
        // Visit all neighbors
        for (int v : adjacencyList.get(u)) {
            if (!visited[v]) {
                parent[v] = u;
                dfsBridge(v, visited, discovery, low, parent, bridges);
                
                // Update low value of u
                low[u] = Math.min(low[u], low[v]);
                
                // Check if edge (u,v) is a bridge
                if (low[v] > discovery[u]) {
                    // Add bridge in lexicographic order (smaller vertex first)
                    if (u < v) {
                        bridges.add(new int[]{u, v});
                    } else {
                        bridges.add(new int[]{v, u});
                    }
                }
            } else if (v != parent[u]) {
                // Back edge - update low value
                low[u] = Math.min(low[u], discovery[v]);
            }
        }
    }
}


