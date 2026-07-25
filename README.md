# 🎯 LeetCode Solutions & Coding Interview Patterns

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17+-ED8B00?logo=java&logoColor=white)](https://www.java.com/)
[![LeetCode](https://img.shields.io/badge/LeetCode-600+-FFA500?logo=leetcode)](https://leetcode.com/)
[![Problems Solved](https://img.shields.io/badge/Problems%20Solved-600+-success)](https://github.com/LamaBimal/leetcode-solutions)

> Master **15 essential coding patterns** that solve 90% of LeetCode problems. Pattern recognition → Template application → Efficient solutions.

---

## 📚 Overview

This repository contains:
- ✅ 15 core problem-solving patterns
- ✅ 600+ LeetCode solutions with explanations
- ✅ Time & space complexity analysis
- ✅ Pattern identification guide
- ✅ Interview preparation strategies
- ✅ Real-world algorithm applications

**Perfect for:**
- LeetCode preparation
- Coding interviews (Google, Amazon, Meta, etc.)
- Algorithm mastery
- Job preparation
- Competitive programming

---

## 🎯 Pattern Recognition Framework

The key to solving LeetCode efficiently is **pattern recognition**:

```
Problem → Identify Pattern → Apply Template → Optimize → Submit
```

---

## 🔑 15 Essential Patterns

### 1️⃣ Prefix Sum
**Problem Type:** Range sum queries, Subarray problems  
**Time:** O(n) preprocessing, O(1) per query  
**Space:** O(n)

```java
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + nums[i];
}
// Sum from index l to r: prefix[r + 1] - prefix[l]
```

**When to Use:**
- ✅ "Find sum of elements in range [l, r]"
- ✅ Contiguous subarray sum problems
- ✅ Difference array techniques

**Example Problems:**
- Subarray Sum Equals K
- Range Sum Query - Immutable
- Product of Array Except Self

---

### 2️⃣ Two Pointers
**Problem Type:** Sorted array problems, Pair matching  
**Time:** O(n)  
**Space:** O(1)

```java
int left = 0, right = arr.length - 1;
while (left < right) {
    int sum = arr[left] + arr[right];
    if (sum == target) return true;
    else if (sum < target) left++;
    else right--;
}
```

**When to Use:**
- ✅ Sorted arrays
- ✅ Finding pairs with specific sum
- ✅ Removing duplicates in-place
- ✅ Container with most water

**Example Problems:**
- Two Sum II - Input Array Is Sorted
- 3Sum
- Trapping Rain Water
- Container With Most Water

---

### 3️⃣ Sliding Window
**Problem Type:** Contiguous subarray/substring  
**Time:** O(n)  
**Space:** O(k) where k = window size

```java
int left = 0, windowSum = 0;
for (int right = 0; right < arr.length; right++) {
    windowSum += arr[right];
    while (windowSum > target) {
        windowSum -= arr[left++];
    }
    // Process current window
}
```

**When to Use:**
- ✅ "Longest/shortest substring/subarray"
- ✅ Fixed or variable window size
- ✅ Contains duplicates, character frequency

**Example Problems:**
- Longest Substring Without Repeating Characters
- Sliding Window Maximum
- Min Window Substring
- Permutation in String

---

### 4️⃣ Fast & Slow Pointers
**Problem Type:** Cycle detection, Middle element  
**Time:** O(n)  
**Space:** O(1)

```java
ListNode slow = head, fast = head;
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
    if (slow == fast) return true; // Cycle detected
}
```

**When to Use:**
- ✅ Detect cycle in linked list
- ✅ Find middle of linked list
- ✅ Remove nth node from end

**Example Problems:**
- Linked List Cycle
- Linked List Cycle II
- Happy Number
- Reorder List

---

### 5️⃣ Linked List In-Place Reversal
**Problem Type:** Reverse linked lists  
**Time:** O(n)  
**Space:** O(1)

```java
ListNode prev = null, curr = head;
while (curr != null) {
    ListNode next = curr.next;
    curr.next = prev;
    prev = curr;
    curr = next;
}
return prev;
```

**When to Use:**
- ✅ Reverse linked list
- ✅ Reverse sublist
- ✅ Reverse in groups

**Example Problems:**
- Reverse Linked List
- Reverse Linked List II
- Reverse Nodes in k-Group

---

### 6️⃣ Monotonic Stack
**Problem Type:** Next/Previous greater/smaller element  
**Time:** O(n)  
**Space:** O(n)

```java
Stack<Integer> stack = new Stack<>();
int[] result = new int[arr.length];
for (int i = 0; i < arr.length; i++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
        result[stack.pop()] = arr[i];
    }
    stack.push(i);
}
```

**When to Use:**
- ✅ Next greater element
- ✅ Daily temperatures
- ✅ Largest rectangle in histogram

**Example Problems:**
- Next Greater Element I
- Daily Temperatures
- Largest Rectangle in Histogram
- Trapping Rain Water

---

### 7️⃣ Top K Elements
**Problem Type:** Find K largest/smallest elements  
**Time:** O(n log k)  
**Space:** O(k)

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int num : arr) {
    minHeap.offer(num);
    if (minHeap.size() > k) minHeap.poll();
}
return minHeap.peek();
```

**When to Use:**
- ✅ Find K largest/smallest elements
- ✅ K most frequent elements
- ✅ Median of stream

**Example Problems:**
- K Largest Elements in an Array
- Top K Frequent Elements
- Find K Closest Elements

---

### 8️⃣ Overlapping Intervals
**Problem Type:** Merge intervals, meeting rooms  
**Time:** O(n log n)  
**Space:** O(1) excluding output

```java
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> merged = new ArrayList<>();
merged.add(intervals[0]);
for (int i = 1; i < intervals.length; i++) {
    int[] last = merged.get(merged.size() - 1);
    if (intervals[i][0] <= last[1]) {
        last[1] = Math.max(last[1], intervals[i][1]);
    } else {
        merged.add(intervals[i]);
    }
}
```

**When to Use:**
- ✅ Merge overlapping intervals
- ✅ Meeting rooms
- ✅ Insert interval

**Example Problems:**
- Merge Intervals
- Insert Interval
- Meeting Rooms
- Interval List Intersections

---

### 9️⃣ Modified Binary Search
**Problem Type:** Variations of binary search  
**Time:** O(log n)  
**Space:** O(1)

```java
int left = 0, right = arr.length - 1;
while (left <= right) {
    int mid = left + (right - left) / 2;
    if (condition(mid)) left = mid + 1;
    else right = mid - 1;
}
```

**When to Use:**
- ✅ Search in rotated sorted array
- ✅ Find first/last position of element
- ✅ Closest number in sorted array

**Example Problems:**
- Search in Rotated Sorted Array
- Find First and Last Position of Element
- Median of Two Sorted Arrays

---

### 🔟 Binary Tree Traversal
**Problem Type:** Tree exploration patterns  
**Time:** O(n)  
**Space:** O(h) where h = height

```java
void inorder(TreeNode root) {
    if (root == null) return;
    inorder(root.left);
    process(root);
    inorder(root.right);
}
```

**Traversal Types:**
- Inorder: Left → Root → Right
- Preorder: Root → Left → Right
- Postorder: Left → Right → Root
- Level-order: BFS traversal

**Example Problems:**
- Binary Tree Inorder Traversal
- Binary Tree Maximum Path Sum
- Lowest Common Ancestor
- Validate Binary Search Tree

---

### 1️⃣1️⃣ Depth-First Search (DFS)
**Problem Type:** Graph exploration  
**Time:** O(V + E)  
**Space:** O(V)

```java
void dfs(int node, boolean[] visited, List<Integer>[] graph) {
    visited[node] = true;
    for (int neighbor : graph[node]) {
        if (!visited[neighbor]) {
            dfs(neighbor, visited, graph);
        }
    }
}
```

**When to Use:**
- ✅ Connected components
- ✅ Topological sort
- ✅ Detect cycle in graph
- ✅ Path finding

**Example Problems:**
- Number of Islands
- Surrounded Regions
- Course Schedule
- Clone Graph

---

### 1️⃣2️⃣ Breadth-First Search (BFS)
**Problem Type:** Level-by-level exploration  
**Time:** O(V + E)  
**Space:** O(V)

```java
void bfs(int start, List<Integer>[] graph) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[graph.length];
    queue.add(start);
    visited[start] = true;
    while (!queue.isEmpty()) {
        int node = queue.poll();
        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                queue.add(neighbor);
            }
        }
    }
}
```

**When to Use:**
- ✅ Shortest path in unweighted graph
- ✅ Level-order traversal
- ✅ Bipartite graph check

**Example Problems:**
- Word Ladder
- Minimum Knight Moves
- Binary Tree Level Order Traversal
- Rotting Oranges

---

### 1️⃣3️⃣ Matrix Traversal
**Problem Type:** 2D array exploration  
**Time:** O(m × n)  
**Space:** O(m × n)

```java
int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
void dfs(int r, int c) {
    if (r < 0 || c < 0 || r >= rows || c >= cols) return;
    if (visited[r][c]) return;
    visited[r][c] = true;
    for (int[] d : directions) {
        dfs(r + d[0], c + d[1]);
    }
}
```

**When to Use:**
- ✅ Island counting
- ✅ Path finding in grid
- ✅ Spiral matrix traversal

**Example Problems:**
- Number of Islands
- Surrounded Regions
- Spiral Matrix
- Pacific Atlantic Water Flow

---

### 1️⃣4️⃣ Backtracking
**Problem Type:** Permutations, combinations, subsets  
**Time:** O(N!)  
**Space:** O(N)

```java
void backtrack(List<Integer> path, int start, int[] nums, List<List<Integer>> result) {
    result.add(new ArrayList<>(path));
    for (int i = start; i < nums.length; i++) {
        path.add(nums[i]);
        backtrack(path, i + 1, nums, result);
        path.remove(path.size() - 1);
    }
}
```

**When to Use:**
- ✅ Generate all permutations
- ✅ Generate all combinations
- ✅ Sudoku solver
- ✅ Word search

**Example Problems:**
- Permutations
- Combinations
- Subsets
- Word Search
- N-Queens

---

### 1️⃣5️⃣ Dynamic Programming
**Problem Type:** Optimal substructure problems  
**Time:** Varies (typically O(n) to O(n²))  
**Space:** O(n) with space optimization

```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;
for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
return dp[n];
```

**DP Strategies:**
- Top-down (Memoization)
- Bottom-up (Tabulation)
- Space optimization

**Example Problems:**
- Climbing Stairs
- Longest Increasing Subsequence
- Coin Change
- House Robber
- Edit Distance

---

## 🚀 Quick Start

### Installation

```bash
# Clone repository
git clone https://github.com/LamaBimal/leetcode-solutions.git
cd leetcode-solutions

# Build
mvn clean install

# Run tests
mvn test

# Run specific solution
mvn exec:java -Dexec.mainClass="com.leetcode.solutions.TwoSum"
```

### How to Use

1. **Identify the Pattern**: Read the problem, identify which pattern it matches
2. **Apply Template**: Use the pattern template as starting point
3. **Customize**: Adapt template to specific problem requirements
4. **Optimize**: Consider edge cases and optimize further
5. **Submit**: Test thoroughly before submitting

---

## 📖 Study Guide

### Beginner (Week 1-2)
- Arrays & Strings: Two Pointers, Prefix Sum
- Linked Lists: In-place Reversal, Fast & Slow Pointers
- Basic Trees: Tree Traversal

### Intermediate (Week 3-4)
- Graphs: DFS, BFS, Topological Sort
- Advanced Trees: Binary Search Trees, Balancing
- Heaps: Priority Queue, Top K

### Advanced (Week 5-6)
- Dynamic Programming: Multiple patterns
- Greedy Algorithms
- Bit Manipulation

### Interview Prep (Week 7-8)
- Mock interviews
- Pattern combination
- Optimize solutions
- System design concepts

---

## 💡 Problem-Solving Strategy

```
1. Understand the Problem
   - Read carefully
   - Identify constraints
   - Ask clarifying questions

2. Think of Examples
   - Simple case
   - Edge cases
   - Complex cases

3. Brute Force Solution
   - Get it working first
   - Don't worry about efficiency

4. Optimize
   - Identify pattern
   - Apply better algorithm
   - Reduce complexity

5. Code & Test
   - Write clean code
   - Test edge cases
   - Debug if needed

6. Analysis
   - Time complexity
   - Space complexity
   - Trade-offs
```

---

## 📊 Complexity Analysis

### Common Complexities
```
O(1)     - Constant (best)
O(log n) - Logarithmic (very good)
O(n)     - Linear (good)
O(n log n) - Log-linear (acceptable)
O(n²)    - Quadratic (needs optimization)
O(2ⁿ)    - Exponential (very slow)
O(n!)    - Factorial (extremely slow)
```

---

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TwoSumTest

# View coverage
mvn clean test jacoco:report
```

---

## 🎓 Resources

- **LeetCode:** [leetcode.com](https://leetcode.com)
- **NeetCode:** [neetcode.io](https://neetcode.io) - Pattern explanations
- **Cracking the Coding Interview:** Essential interview prep book
- **Algorithm Visualizer:** [visualgo.net](https://visualgo.net)
- **Big O Cheat Sheet:** [bigocheatsheet.com](https://www.bigocheatsheet.com)

---

## ✨ Features

- ✅ 15 essential patterns
- ✅ 600+ solutions
- ✅ Pattern templates
- ✅ Complexity analysis
- ✅ Real-world applications
- ✅ Interview tips
- ✅ Comprehensive explanations
- ✅ Edge case handling

---

## 🤝 Contributing

Contributions welcome! Please:
1. Add new solutions
2. Include pattern identification
3. Add comprehensive comments
4. Include test cases
5. Update documentation

---

## 📝 License

MIT License - See LICENSE file for details.

---

<div align="center">

**Master Coding Interviews! 💻**

[⬆ back to top](#-leetcode-solutions--coding-interview-patterns)

</div>
