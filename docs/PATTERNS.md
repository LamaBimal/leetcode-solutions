# Problem-solving pattern guide

Choose a pattern from the constraints and the invariant it can maintain. State the
input contract, then derive complexity from how often each element is visited.
Patterns are a learning aid; a problem may admit several valid approaches.

| Pattern | Useful for | Invariant or decision | Common pitfall |
| --- | --- | --- | --- |
| Prefix sum | Range sums and target-sum subarrays | Sum(l..r) = prefix(r+1) - prefix(l) | Integer overflow; overwriting the earliest index |
| Two pointers | Sorted pair search and opposing boundaries | Each move eliminates an impossible candidate | Applying sorted pair search to unsorted input |
| Sliding window | Contiguous strings or arrays | Shrink until the window satisfies its constraint | Sum-based shrinking is not generally valid with negative values |
| Fast and slow pointers | Linked-list cycles and midpoint | Pointers advance at different speeds | Dereferencing a missing fast.next |
| In-place linked-list reversal | Reverse a list or segment | Save next before changing the current link | Losing the unprocessed suffix; unexpected input mutation |
| Monotonic stack | Next greater/smaller values and 132 patterns | Stack order makes popped candidates final | Confusing strict and non-strict comparisons |
| Top K / heap | Largest or smallest K values | A bounded heap retains the best K candidates | Calling peek on an empty heap; invalid K |
| Overlapping intervals | Merge or schedule ranges | Sort starts; compare against the latest merged end | Empty input; overflowing subtraction in comparators |
| Modified binary search | Sorted partitions and monotonic predicates | Maintain a feasible interval and shrink it | Incorrect bounds or a predicate that is not monotonic |
| Binary-tree traversal | Ordered tree processing | Preorder, inorder, or postorder determines visit timing | Recursion depth on skewed trees |
| Depth-first search | Reachability, components, and path exploration | Mark a node before exploring neighbors | Missing adjacency lists or revisiting cycles |
| Breadth-first search | Shortest paths in unweighted graphs | Queue order processes one distance layer at a time | Marking visited too late; using BFS for weighted shortest paths |
| Matrix traversal | Islands, regions, and grid reachability | Check bounds and mark visited before expanding | Mixing row/column dimensions or mutating input unexpectedly |
| Backtracking | Subsets, permutations, and constrained search | Choose, recurse, undo | Sharing mutable result lists or failing to undo a choice |
| Dynamic programming | Repeated subproblems with reusable results | State and transition fully describe the remaining work | Incorrect base cases, evaluation order, or numeric bounds |

## Prefix-sum template

Use a long accumulator when int sums can overflow. For longest target-sum
subarrays, retain the earliest index of each prefix.

```java
Map<Long, Integer> firstIndex = new HashMap<>();
firstIndex.put(0L, -1);
long sum = 0;
int longest = 0;
for (int index = 0; index < numbers.length; index++) {
    sum += numbers[index];
    Integer start = firstIndex.get(sum - target);
    if (start != null) {
        longest = Math.max(longest, index - start);
    }
    firstIndex.putIfAbsent(sum, index);
}
```

## Sliding-window template

The validity condition must support incremental updates and shrinking.

```java
int left = 0;
for (int right = 0; right < input.length; right++) {
    // Add input[right] to the window state.
    while (/* window is invalid */) {
        // Remove input[left] from the state.
        left++;
    }
    // Use the valid window [left, right].
}
```

## Traversal template

For an unweighted graph, mark visited when enqueueing. Treat missing adjacency
entries as empty. This fragment assumes the graph, start, and neighbor lists have
already satisfied the caller's contract.

```java
Queue<Integer> queue = new ArrayDeque<>();
Set<Integer> visited = new HashSet<>();
queue.offer(start);
visited.add(start);
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
        if (visited.add(neighbor)) {
            queue.offer(neighbor);
        }
    }
}
```

## Classification notes

Reverse-order digit addition is carry simulation over linked lists; it does not
require reversal. Maximum subarray uses Kadane's dynamic programming recurrence.
Longest palindromic substring can use center expansion or dynamic programming;
ordinary duplicate-free sliding-window logic does not solve it.

The README catalog contains implementations spanning all 15 patterns. Other
problems mentioned as examples, including longest palindromic substring, remain
study directions unless explicitly listed in that catalog.
