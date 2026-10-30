# Big O & complexity: dedicated learning module

Select **05 · Big O & complexity** in Pattern Lab. It has its own lesson navigation and does not appear beneath algorithm, SOLID, design-pattern, or quiz lessons. Existing per-algorithm complexity summaries describe that algorithm; the teaching curriculum lives here.

Start: https://lamabimal.github.io/leetcode-solutions/#lesson=complexity%2Fbasics

## Learning sequence

1. Basics: input size, representative operations, upper/tight/lower bounds, and time versus memory.
2. Consecutive versus nested loops: add phases; multiply independent repetitions.
3. Brute-force pairs: derive n(n−1)/2 from the triangular sum.
4. Two pointers and fast/slow pointers: bound total pointer advances.
5. Sliding windows and monotonic stacks: charge each addition/removal to an element.
6. Binary search: solve T(n)=T(n/2)+Θ(1); compare iterative and recursive memory.
7. Prefix sums and hashing: expected lookup costs and n+1 retained prefixes.
8. Sorting then scanning: interval copies, O(n log n) sorting, and a linear merge.
9. Bounded heaps: n input items versus k retained items.
10. Trees: total work n, DFS stack h, BFS frontier w, and output storage.
11. Grids and graphs: R·C or V+E; visited marking bounds total traversal.
12. Backtracking: count subsets and copied output elements separately.
13. Stair DP: naive recursion, memoization, tabulation, and rolling state.
14. Object designs: Observer callback costs, Strategy delegation, and Decorator depth.

Each chapter includes an excerpt, a step-by-step derivation, an adjustable count/work model, a memory explanation, and a knowledge check. Related lessons link to actual Java repository implementations and the corresponding algorithm simulator.

Counts illustrate specified work units, not elapsed-time benchmarks. Heap and sorting counters explicitly model height/levels rather than exact library comparisons. Subset and naive-recursion counts are calculated by formulas/recurrences, so the UI never performs uncontrolled exponential enumeration. Input validation and saved visualization frames are separate from the displayed algorithm bound.
