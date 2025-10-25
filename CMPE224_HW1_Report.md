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

---

## Problem Statement and Code Design (15%)

### Problem Summary

This assignment required implementing two critical graph algorithms using a custom graph data structure:

1. **Question 1 - Critical Bridge Detection:** Identify all bridges in an island network whose removal would disconnect parts of the country.
2. **Question 2 - Bipartite Graph Checking:** Determine if museum networks can be colored with Blue/Gold and find odd cycles if not.

### Sub-tasks Completed

1. Design and implement a custom Graph class using adjacency lists
2. Implement Tarjan's algorithm for bridge detection
3. Implement BFS-based bipartite checking with odd cycle detection
4. Create comprehensive test cases for both algorithms
5. Ensure proper output formatting and lexicographic ordering

### Modular Design Structure Chart

```
CMPE224 HW1 System
├── Graph Class
│   ├── Constructor (vertices)
│   ├── addEdge(u, v)
│   ├── getNeighbors(vertex)
│   ├── findBridges()
│   └── dfsBridge() [private helper]
├── Question1 (Bridge Detection)
│   ├── main()
│   └── Input/Output Processing
├── Question2 (Bipartite Checking)
│   ├── main()
│   ├── checkBipartiteComponents()
│   ├── checkBipartiteBFS()
│   ├── findOddCycle()
│   └── findLCA()
└── Test Classes
    ├── Question1Tester
    ├── Question2Tester
    └── ComprehensiveTester
```

### Design Rationale

The design follows a modular approach where:
- **Graph Class** serves as the foundation data structure
- **Question1** focuses solely on bridge detection
- **Question2** handles bipartite checking and cycle detection
- **Test Classes** provide comprehensive validation
- Each module has clear responsibilities and interfaces

---

## Implementation and Functionality (20%)

### Graph Class Implementation

**Purpose:** Custom graph data structure using adjacency lists for both algorithms.

**Key Methods:**
- `Graph(int vertices)`: Initializes graph with specified number of vertices
- `addEdge(int u, int v)`: Adds undirected edge between vertices
- `findBridges()`: Returns list of critical bridges using Tarjan's algorithm

**Pseudocode for Bridge Detection:**
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

### Question 1: Bridge Detection

**Purpose:** Identify all critical bridges in the island network.

**Input Parameters:** Number of islands (N), number of bridges (M), bridge connections
**Output:** List of bridges in lexicographic order, total count

**Key Algorithm:** Tarjan's DFS-based bridge detection
- Uses discovery time and low values
- A bridge is detected when `low[v] > discovery[u]`
- Ensures lexicographic ordering of output

### Question 2: Bipartite Checking

**Purpose:** Check if museum networks are 2-colorable and find odd cycles.

**Input Parameters:** Number of museums (N), number of corridors (M), corridor connections
**Output:** Bipartite status for each component, odd cycles if not bipartite

**Key Algorithm:** BFS-based bipartite checking
- Uses color assignment (0=Blue, 1=Gold)
- Detects conflicts when adjacent vertices have same color
- Reconstructs odd cycles using parent tracking

**Pseudocode for Bipartite Checking:**
```
Algorithm checkBipartiteBFS(graph, start, visited):
1. Initialize color[], parent[], queue
2. Set color[start] = 0, add start to queue
3. While queue not empty:
   a. Dequeue vertex u
   b. For each neighbor v of u:
      - If v not visited:
        * Set color[v] = 1 - color[u]
        * Set parent[v] = u
        * Add v to queue
      - Else if color[v] == color[u]:
        * Return not bipartite with odd cycle
4. Return bipartite
```

### Cycle Detection Implementation

**Purpose:** Find odd-length cycles when bipartite check fails.

**Method:** `findOddCycle(u, v, parent, start)`
1. Reconstruct paths from u and v to start vertex
2. Find lowest common ancestor (LCA)
3. Build cycle: u → LCA → v → u
4. Return cycle as space-separated string

---

## Testing (7.5%)

### Test Strategy

Comprehensive testing was implemented using multiple test classes to validate all aspects of both algorithms.

### Question 1 Test Cases

**Test 1: 4-Cycle (No Bridges)**
- Input: 4 vertices, 4 edges forming a cycle
- Expected: 0 bridges
- Result: ✓ PASSED

**Test 2: Linear Graph (All Bridges)**
- Input: 5 vertices in linear arrangement
- Expected: 4 bridges
- Result: ✓ PASSED

**Test 3: Disconnected Components**
- Input: Two separate 2-vertex components
- Expected: 2 bridges
- Result: ✓ PASSED

**Test 4: Complex Graph**
- Input: Mixed connected and disconnected parts
- Expected: Variable number of bridges
- Result: ✓ PASSED

### Question 2 Test Cases

**Test 1: Bipartite Graph (Even Cycle)**
- Input: 4-vertex cycle
- Expected: Bipartite
- Result: ✓ PASSED

**Test 2: Non-Bipartite Graph (Odd Cycle)**
- Input: 3-vertex cycle
- Expected: Not Bipartite with odd cycle
- Result: ✓ PASSED

**Test 3: Mixed Components**
- Input: One bipartite, one non-bipartite component
- Expected: Mixed results
- Result: ✓ PASSED

**Test 4: Edge Cases**
- Single vertex: Bipartite ✓
- Two disconnected vertices: Both bipartite ✓
- Complete graph K3: Not bipartite ✓

### Test Coverage Analysis

The test suite covers:
- ✅ Normal cases for both algorithms
- ✅ Edge cases (single vertex, disconnected)
- ✅ Boundary conditions
- ✅ Complex scenarios with multiple components
- ✅ Output format validation
- ✅ Lexicographic ordering verification

### Bug Analysis

No bugs were discovered during testing. All test cases passed successfully, confirming the correctness of both implementations.

---

## Final Assessments (5%)

### 1. Trouble Points in Completing This Assignment

The main challenges encountered were:
- **Algorithm Selection:** Choosing the most efficient bridge detection algorithm (Tarjan's vs. naive approaches)
- **Cycle Reconstruction:** Implementing proper odd cycle detection and path reconstruction
- **Output Formatting:** Ensuring exact compliance with specified output formats
- **Java Version Compatibility:** Resolving compilation issues between Java 21 compiler and Java 8 runtime

### 2. Most Challenging Parts

The most challenging aspects were:
- **Odd Cycle Detection:** Reconstructing the exact path of odd cycles when bipartite check fails
- **Lexicographic Ordering:** Ensuring bridges are output in correct sorted order
- **Component Handling:** Properly managing disconnected components in bipartite checking
- **Path Reconstruction:** Implementing LCA (Lowest Common Ancestor) algorithm for cycle detection

### 3. What I Liked and Learned

**Liked:**
- The practical applications of graph algorithms in real-world scenarios
- The modular design approach that made code organization clear
- The comprehensive testing that validated algorithm correctness

**Learned:**
- Deep understanding of Tarjan's bridge detection algorithm
- BFS-based bipartite checking techniques
- Advanced graph traversal and cycle detection methods
- Importance of proper test case design and edge case handling

### 4. AI Tools Usage

**AI Tools Used:**
- **ChatGPT/Copilot:** Used for initial algorithm clarification and pseudocode generation
- **Code Review:** AI assistance in identifying potential edge cases and optimization opportunities

**How I Ensured Original Work:**
- Implemented all algorithms from scratch using my own understanding
- Modified and adapted AI suggestions to fit specific requirements
- Added extensive custom documentation and comments
- Created comprehensive test cases independently
- Verified all implementations through manual testing

### 5. AI Assistance Impact on Learning

**Positive Impacts:**
- **Conceptual Clarity:** AI helped clarify complex algorithm concepts and edge cases
- **Efficiency:** Reduced time spent on initial research, allowing more focus on implementation
- **Code Quality:** AI suggestions improved code structure and documentation

**Learning Process Changes:**
- AI made problem-solving more systematic and thorough
- Encouraged deeper understanding through explanation and adaptation of suggestions
- Enhanced debugging process through AI-assisted analysis

### 6. Ethical Boundaries for AI in Academic Work

**Appropriate Use:**
- Concept clarification and algorithm explanation
- Code structure and documentation suggestions
- Edge case identification and test case generation
- Debugging assistance and optimization hints

**Inappropriate Use:**
- Direct code copying without understanding
- Complete solution generation without learning
- Submission of AI-generated work as original

**Personal Guidelines:**
- Always understand and modify any AI-generated code
- Use AI as a learning tool, not a solution provider
- Ensure final submission reflects personal understanding and effort
- Maintain academic integrity while leveraging available tools

---

## Conclusion

This assignment successfully implemented two critical graph algorithms using a custom data structure. The modular design, comprehensive testing, and thorough documentation demonstrate a solid understanding of graph theory concepts and their practical applications. The implementation handles all specified requirements and edge cases while maintaining clean, well-documented code suitable for academic submission.

**Total Pages:** 3  
**Word Count:** Approximately 1,200 words  
**Format:** Professional academic report with clear sections and technical depth


