# CMPE 224-343 Programming Assignment Report
## Homework 1: Graph Algorithms Implementation

---

## Information (2.5%)

**Student ID:** [Your Student ID]  
**Name:** [Your Full Name]  
**Section:** [Your Section Number]  
**Assignment:** Programming Homework 1  
**Course:** CMPE 224-343  
**Semester:** Fall 2025  
**Due Date:** October 26, 2025  
**TA:** Deniz Merve Uzun  

---

## Problem Statement and Code Design (15%)

### Problem Summary

This assignment required implementing two critical graph algorithms using a custom graph data structure without external libraries:

1. **Question 1 - Critical Bridge Detection:** Identify all bridges in an island network whose removal would disconnect parts of the country. A bridge is an edge whose removal increases the number of connected components.

2. **Question 2 - Bipartite Graph Checking:** Determine if museum networks can be colored with exactly two colors (Blue/Gold) such that no adjacent vertices have the same color. If not bipartite, find and output an odd-length cycle.

### Sub-tasks Completed

1. **Custom Graph Implementation:** Designed and implemented a Graph class using adjacency lists
2. **Bridge Detection Algorithm:** Implemented Tarjan's algorithm for efficient bridge detection
3. **Bipartite Checking Algorithm:** Implemented BFS-based bipartite checking with odd cycle detection
4. **Output Formatting:** Ensured exact compliance with specified output formats and lexicographic ordering
5. **Comprehensive Testing:** Created extensive test cases covering normal and edge cases

### Modular Design Structure Chart

```
CMPE224 HW1 System
├── Graph Class (Foundation)
│   ├── Constructor(int vertices)
│   ├── addEdge(int u, int v)
│   ├── getNeighbors(int vertex)
│   ├── findBridges() → List<int[]>
│   └── dfsBridge() [private helper]
│
├── Question1 (Bridge Detection)
│   ├── main(String[] args)
│   ├── Input Processing (Scanner)
│   └── Output Formatting
│
├── Question2 (Bipartite Checking)
│   ├── main(String[] args)
│   ├── checkBipartiteComponents(Graph)
│   ├── checkBipartiteBFS(Graph, int, boolean[])
│   ├── findOddCycle(int, int, int[], int)
│   └── findLCA(List<Integer>, List<Integer>)
│
└── Test Suite
    ├── Question1Tester
    ├── Question2Tester
    └── ComprehensiveTester
```

### Design Rationale

The modular design follows these principles:
- **Separation of Concerns:** Each class has a single, well-defined responsibility
- **Reusability:** Graph class serves both algorithms
- **Maintainability:** Clear interfaces and documentation
- **Testability:** Comprehensive test coverage for each module
- **Extensibility:** Easy to add new graph algorithms

---

## Implementation and Functionality (20%)

### Graph Class Implementation

**Purpose:** Custom graph data structure using adjacency lists for both algorithms.

**Key Data Members:**
- `int vertices`: Number of vertices in the graph
- `List<List<Integer>> adjacencyList`: Adjacency list representation
- `int time`: Global timer for DFS operations

**Key Methods:**

#### Constructor
```java
public Graph(int vertices) {
    this.vertices = vertices;
    this.time = 0;
    this.adjacencyList = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
        adjacencyList.add(new ArrayList<>());
    }
}
```

#### Add Edge
```java
public void addEdge(int u, int v) {
    adjacencyList.get(u).add(v);
    adjacencyList.get(v).add(u);  // Undirected graph
}
```

### Question 1: Bridge Detection Algorithm

**Algorithm:** Tarjan's DFS-based bridge detection

**Key Insight:** An edge (u,v) is a bridge if and only if `low[v] > discovery[u]`, where:
- `discovery[u]`: Time when vertex u is first discovered
- `low[u]`: Earliest discovery time of any vertex reachable from u

**Pseudocode:**
```
Algorithm findBridges():
1. Initialize arrays: visited[], discovery[], low[], parent[]
2. For each unvisited vertex i:
   a. Call dfsBridge(i, visited, discovery, low, parent, bridges)
3. Sort bridges lexicographically
4. Return bridges list

Algorithm dfsBridge(u, visited, discovery, low, parent, bridges):
1. Mark u as visited
2. Set discovery[u] = low[u] = ++time
3. For each neighbor v of u:
   a. If v not visited:
      - Set parent[v] = u
      - Recursively call dfsBridge(v, ...)
      - Update low[u] = min(low[u], low[v])
      - If low[v] > discovery[u], add (u,v) as bridge
   b. Else if v != parent[u]:
      - Update low[u] = min(low[u], discovery[v])
```

**Time Complexity:** O(V + E) where V is vertices, E is edges
**Space Complexity:** O(V) for recursion stack and arrays

### Question 2: Bipartite Checking Algorithm

**Algorithm:** BFS-based bipartite checking with odd cycle detection

**Key Insight:** A graph is bipartite if and only if it contains no odd-length cycles. Use BFS to assign colors alternately and detect conflicts.

**Pseudocode:**
```
Algorithm checkBipartiteBFS(graph, start, visited):
1. Initialize color[], parent[], queue
2. Set color[start] = 0, add start to queue
3. While queue not empty:
   a. Dequeue vertex u
   b. For each neighbor v of u:
      - If v not visited:
        * Set color[v] = 1 - color[u]  // Alternate color
        * Set parent[v] = u
        * Add v to queue
      - Else if color[v] == color[u]:
        * Return not bipartite with odd cycle
4. Return bipartite

Algorithm findOddCycle(u, v, parent, start):
1. Reconstruct path from u to start using parent array
2. Reconstruct path from v to start using parent array
3. Find lowest common ancestor (LCA)
4. Build cycle: u → LCA → v → u
5. Return cycle as space-separated string
```

**Time Complexity:** O(V + E) for BFS traversal
**Space Complexity:** O(V) for queue and color arrays

### Cycle Detection Implementation

**Purpose:** Find odd-length cycles when bipartite check fails.

**Method:** `findOddCycle(u, v, parent, start)`
1. **Path Reconstruction:** Use parent array to trace paths from conflicting vertices to start
2. **LCA Finding:** Identify lowest common ancestor of the two paths
3. **Cycle Building:** Construct cycle by combining path segments
4. **Formatting:** Return cycle as space-separated vertex sequence

---

## Testing (7.5%)

### Test Strategy

Comprehensive testing implemented using multiple test classes to validate all aspects of both algorithms.

### Question 1 Test Results

| Test Case | Input | Expected Bridges | Actual Result | Status |
|-----------|-------|------------------|---------------|--------|
| 4-Cycle | 4 vertices, 4 edges | 0 | 0 | ✅ PASS |
| Linear Graph | 5 vertices in line | 4 | 4 | ✅ PASS |
| Disconnected | Two 2-vertex components | 2 | 2 | ✅ PASS |
| Single Vertex | 1 vertex, 0 edges | 0 | 0 | ✅ PASS |
| Complete K3 | 3 vertices, 3 edges | 0 | 0 | ✅ PASS |

### Question 2 Test Results

| Test Case | Input | Expected Result | Actual Result | Status |
|-----------|-------|-----------------|---------------|--------|
| Even Cycle | 4-vertex cycle | Bipartite | Bipartite | ✅ PASS |
| Odd Cycle | 3-vertex cycle | Not Bipartite | Not Bipartite | ✅ PASS |
| Mixed Components | 1 bipartite + 1 non-bipartite | Mixed | Mixed | ✅ PASS |
| Single Vertex | 1 vertex | Bipartite | Bipartite | ✅ PASS |
| Tree | 5-vertex tree | Bipartite | Bipartite | ✅ PASS |

### Edge Case Coverage

✅ **Empty Graphs:** Handled correctly  
✅ **Single Vertex:** Trivially bipartite, no bridges  
✅ **Disconnected Components:** Processed separately  
✅ **Complete Graphs:** No bridges, not bipartite (odd cycles)  
✅ **Linear Graphs:** All edges are bridges, bipartite  
✅ **Cyclic Graphs:** Even cycles bipartite, odd cycles not  

### Output Format Validation

✅ **Bridge Output:** Lexicographic ordering verified  
✅ **Bipartite Output:** Component numbering correct  
✅ **Cycle Output:** Valid odd-length cycles found  
✅ **Count Format:** "Total bridges: X" format correct  

### Bug Analysis

**No bugs discovered during testing.** All test cases passed successfully, confirming:
- Algorithm correctness
- Output format compliance
- Edge case handling
- Performance within expected bounds

---

## Final Assessments (5%)

### 1. Trouble Points in Completing This Assignment

**Primary Challenges:**
- **Algorithm Selection:** Choosing between multiple bridge detection approaches (Tarjan's vs. naive DFS)
- **Cycle Reconstruction:** Implementing proper odd cycle detection and path reconstruction
- **Output Formatting:** Ensuring exact compliance with specified output formats and lexicographic ordering
- **Java Version Compatibility:** Resolving compilation issues between Java 21 compiler and Java 8 runtime
- **Component Handling:** Managing disconnected components correctly in bipartite checking

### 2. Most Challenging Parts

**Most Difficult Aspects:**
- **Odd Cycle Detection:** Reconstructing the exact path of odd cycles when bipartite check fails
- **LCA Implementation:** Finding lowest common ancestor for cycle reconstruction
- **Lexicographic Ordering:** Ensuring bridges are output in correct sorted order
- **Path Reconstruction:** Building valid cycles from parent arrays
- **Edge Case Handling:** Ensuring robust behavior for all possible inputs

### 3. What I Liked and Learned

**Liked About the Assignment:**
- **Practical Applications:** Real-world scenarios (island bridges, museum networks) made algorithms meaningful
- **Modular Design:** Clean separation of concerns made code organization clear
- **Comprehensive Testing:** Thorough validation process ensured algorithm correctness
- **Algorithm Depth:** Deep dive into advanced graph algorithms

**Key Learning Outcomes:**
- **Tarjan's Algorithm:** Deep understanding of bridge detection using discovery time and low values
- **BFS Applications:** Advanced uses of BFS for bipartite checking and cycle detection
- **Graph Theory:** Practical applications of fundamental graph concepts
- **Code Design:** Importance of modular, well-documented code structure
- **Testing Methodology:** Systematic approach to algorithm validation

### 4. AI Tools Usage

**AI Tools Used:**
- **ChatGPT:** Initial algorithm clarification and pseudocode generation
- **GitHub Copilot:** Code structure suggestions and documentation assistance
- **AI Code Review:** Identifying potential edge cases and optimization opportunities

**How I Ensured Original Work:**
- **Implementation from Scratch:** All algorithms implemented using personal understanding
- **Custom Adaptation:** Modified and adapted AI suggestions to fit specific requirements
- **Extensive Documentation:** Added comprehensive custom comments and explanations
- **Independent Testing:** Created test cases and validation independently
- **Manual Verification:** Thoroughly tested all implementations through manual analysis

### 5. AI Assistance Impact on Learning

**Positive Learning Impacts:**
- **Conceptual Clarity:** AI helped clarify complex algorithm concepts and edge cases
- **Efficiency Gains:** Reduced initial research time, allowing more focus on implementation details
- **Code Quality:** AI suggestions improved code structure and documentation standards
- **Debugging Support:** AI assistance enhanced the debugging and optimization process

**Learning Process Changes:**
- **Systematic Approach:** AI encouraged more systematic and thorough problem-solving
- **Deeper Understanding:** Required explanation and adaptation of AI suggestions enhanced comprehension
- **Enhanced Analysis:** AI-assisted analysis improved debugging and optimization skills

### 6. Ethical Boundaries for AI in Academic Work

**Appropriate AI Use:**
- **Concept Clarification:** Understanding algorithm principles and edge cases
- **Code Structure:** Suggestions for organization and documentation
- **Edge Case Identification:** Help identifying potential test scenarios
- **Debugging Assistance:** Analysis of code issues and optimization hints
- **Learning Support:** Explanation of complex concepts and algorithms

**Inappropriate AI Use:**
- **Direct Code Copying:** Using AI-generated code without understanding
- **Complete Solution Generation:** Having AI solve entire problems
- **Academic Dishonesty:** Submitting AI work as original

**Personal Guidelines Established:**
- **Understanding First:** Always comprehend and modify any AI-generated code
- **Learning Tool:** Use AI as a learning aid, not a solution provider
- **Original Work:** Ensure final submission reflects personal understanding and effort
- **Academic Integrity:** Maintain honesty while leveraging available tools
- **Transparency:** Acknowledge AI assistance appropriately in academic work

---

## Conclusion

This assignment successfully implemented two critical graph algorithms using a custom data structure. The modular design, comprehensive testing, and thorough documentation demonstrate a solid understanding of graph theory concepts and their practical applications. The implementation handles all specified requirements and edge cases while maintaining clean, well-documented code suitable for academic submission.

The project reinforced the importance of systematic algorithm design, thorough testing, and clear documentation in software development. The experience with AI tools provided valuable insights into their appropriate use in academic contexts while maintaining academic integrity.

**Total Pages:** 3  
**Word Count:** Approximately 1,500 words  
**Format:** Professional academic report with technical depth and clear structure
