# Big O foundations

Open the live guide's **Big O foundations** section for counted examples and a knowledge check.

1. Define input size and representation. Graphs use V and E; dense datasets use n and d.
2. State worst, best, expected, or amortized cost. Big O itself does not specify a case.
3. Add consecutive phases; multiply independent nested work; halving leads to logarithmic growth.
4. Distinguish auxiliary space, returned output, input storage, and recursive stack depth.
5. Account separately for validation, input copies, and saved visualization frames.

Examples: a scan is O(n); ordered pairs are O(n²); binary search is O(log n) on sorted random-access data; adjacency-list traversal is O(V + E). A nested shrinking window can still be O(n), because each boundary advances at most n times.

## Practice

- Two consecutive n-length loops: Θ(n), not Θ(n²).
- Sorting then scanning: O(n log n) overall.
- Balanced recursive DFS: O(log n) stack; chain-shaped tree: O(n).
- K-fold evaluation over H configurations: about H × K fits, plus final refitting.
- Saving O(n) full n-element snapshots: O(n²) memory beyond the algorithm.

Counts illustrate growth, not wall-clock speed. Bounded integer operations are treated as constant-time; arbitrary precision arithmetic needs additional analysis.
