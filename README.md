# LeetCode Solutions

A Java algorithm library for learning problem-solving patterns. Solutions are
organized by pattern, expose reusable methods, and include regression tests.
The production library uses only the JDK. The catalog contains 24 algorithm
classes spanning the 15 study patterns, plus 15 design-principle teaching examples
under `principles` and `solid`.

Project identifier: `leetcode-solutions`. Maven coordinates:
`com.learning:leetcode-solutions:1.0.0-SNAPSHOT`.

## Requirements and build

- JDK 8 or later.
- The included Maven wrapper downloads Maven 3.9.9; an existing Maven 3.6.3+
  installation can also run the build.
- Network access on the first build to resolve pinned dependencies.

```powershell
.\mvnw.cmd verify
java -cp target/classes com.learning.examples.AlgorithmExamples
```

On Linux or macOS, use `sh ./mvnw verify`. With Maven installed, `mvn verify`
is equivalent.

Open `pom.xml` as a Maven project in IntelliJ IDEA. Maven defines the source roots;
regenerate any existing IDE module configuration after migrating from the old layout.
CI runs the same verification lifecycle on Java 8, 17, and 21.

## Project layout

```text
src/main/java/com/learning/
    backtracking/        Subset generation
    bfs/                 Binary-tree level-order traversal
    binarysearch/        Rotated-array search and median partitioning
    binarytree/          Inorder, preorder, and postorder traversal
    dfs/                 Island counting
    dynamicprogramming/  Stair counting and maximum subarray
    examples/            Runnable console examples
    fastslowpointers/    Cycle detection and middle-node search
    linkedlist/          Digit nodes, addition, conversion helpers
    linkedlistreversal/  Full and partial in-place reversal
    matrixtraversal/     Flood fill
    monotonicstack/      132 patterns and next-greater elements
    overlappingintervals/ Interval merging
    prefixsum/           Prefix accumulation and array products
    principles/          General design-principle teaching examples
    slidingwindow/       Substring and binary-window problems
    solid/               SOLID teaching examples
    topkelements/        Kth-largest search
    twopointers/         Sorted pair search and trapped rainwater
src/test/java/           JUnit Jupiter tests, grouped by pattern
docs/                    Design decisions and pattern reference
.github/workflows/       Build verification
```

## Solution catalog

All packages below are relative to `com.learning`. Space excludes returned output.

| Package and class | Method | Time | Auxiliary space |
| --- | --- | --- | --- |
| `monotonicstack.Pattern132` | `contains132Pattern` | O(n) | O(n) |
| `slidingwindow.LargestSubString` | `lengthOfLongestSubstring` | O(n) | O(u) |
| `slidingwindow.UniqueCharacterSubString` | `countGoodSubstrings` | O(n) | O(1) |
| `slidingwindow.MaxConsecutiveOnes` | `findLongestOnes` | O(n) | O(1) |
| `linkedlist.ListNodeIntegerSum` | `sumTwoNumbers` | O(max(m,n)) | O(1) |
| `linkedlist.IntegerSum` | `generateNumber`, `convertToList` | O(d) | O(1) |
| `binarysearch.TwoArraysMedian` | `findMedianSortedArrays` | O(log(min(m,n) + 1)) | O(1) |
| `prefixsum.LongestSubArray` | `findLongestSubArrayWithTarget` | O(n) | O(n) |
| `dynamicprogramming.MaxSumSubArray` | `calculateMaxSumArray` | O(n) | O(1) |
| `prefixsum.ProductArray` | `productExceptSelf` | O(n) | O(1) |
| `twopointers.SumTwoNumber` | `findTarget` | O(n) | O(1) |
| `twopointers.TrappingWater` | `calculateWaterAmount` | O(n) | O(1) |
| `dynamicprogramming.ClimbingStairs` | `countWays` | O(n) | O(1) |
| `dynamicprogramming.ClimbingStairs` | `countWaysWithTabulation` | O(n) | O(n) |
| `backtracking.Subsets` | `subsets` | O(n * 2^n) | O(n) |
| `bfs.BinaryTreeLevelOrder` | `levelOrder` | O(n) | O(w) |
| `binarysearch.SearchRotatedArray` | `search` | O(log n) | O(1) |
| `binarytree.BinaryTreeTraversal` | `inorder`, `preorder`, `postorder` | O(n) | O(h) |
| `dfs.NumberOfIslands` | `numIslands` | O(rows * columns) | O(rows * columns) |
| `fastslowpointers.LinkedListCycle` | `hasCycle`, `findMiddle` | O(n) | O(1) |
| `linkedlistreversal.ReverseLinkedList` | `reverse`, `reverseBetween` | O(n) | O(1) |
| `matrixtraversal.FloodFill` | `floodFill` | O(rows * columns) | O(rows * columns) |
| `monotonicstack.NextGreaterElement` | `nextGreaterElement` | O(n) | O(n) |
| `overlappingintervals.MergeIntervals` | `merge` | O(n log n) | O(n) |
| `topkelements.KthLargestElement` | `findKthLargest` | O(n log(k + 1)) | O(k) |

Here, u is the number of distinct UTF-16 code units, d is the number of digits,
w is maximum tree width, and h is tree height. Recursive grid traversals can
consume one stack frame per cell and are limited by the JVM stack size.
Hash-based algorithms use expected constant-time map/set operations.
Maximum subarray uses Kadane's dynamic programming recurrence.

## Example

```java
import com.learning.linkedlist.ListNode;
import com.learning.linkedlist.ListNodeIntegerSum;

ListNode first = new ListNode(2);
first.next = new ListNode(4);
first.next.next = new ListNode(3);
ListNode second = new ListNode(5);
second.next = new ListNode(6);
second.next.next = new ListNode(4);

ListNode result = new ListNodeIntegerSum().sumTwoNumbers(first, second);
// Result digits: 7 -> 0 -> 8, representing 807.
```

## Input and result contracts

- Reference inputs reject null with `NullPointerException`, except digit-list
  addition (null represents zero), tree traversals and cycle detection (null is
  empty), list reversal (null is empty), and island counting (null returns zero).
- Empty arrays and strings return zero, false, or an empty result as appropriate.
  Maximum subarray rejects empty input; median rejects two empty arrays.
- Most algorithms leave caller inputs unchanged. Island counting, flood fill,
  and linked-list reversal explicitly modify their inputs in place. Addition
  allocates new nodes; array products and merged intervals return new arrays.
- Sorted-array methods require ascending inputs. Linked lists must be acyclic.
  These preconditions are documented rather than scanned on every call.
- Rotated-array search requires distinct values in a rotated ascending array.
  Grid methods require rectangular grids. Flood-fill coordinates must identify
  an existing cell; partial reversal requires valid 1-based bounds.
- Invalid digits, negative elevations, negative flip budgets, nonbinary windows,
  and stair counts outside 0..45 throw `IllegalArgumentException`.
- Sums use wide intermediates where needed. Integer results and required product
  intermediates throw `ArithmeticException` on overflow instead of wrapping.
- String methods count UTF-16 code units, matching Java `char` semantics.
- A product over no other elements is 1, so a singleton product array returns
  `[1]`. Multiple zeros produce all zeros.
- Kth-largest search requires `1 <= k <= array.length`.

Design-principle examples intentionally demonstrate mutable domain objects and
contrasting approaches. Their contracts are local to each example; they are not
production persistence or banking implementations. Tree and non-digit list
examples currently use package-local node models; their tests live in the same
package. Digit addition exposes a public node model.

## Migration from the original layout

Sources moved from `src/com/learning` to `src/main/java/com/learning`.
Upstream tests moved from `test/com/learning` to `src/test/java/com/learning`.
Update imports as follows:

| Previous package | Current package |
| --- | --- |
| `com.learning.question1` | `com.learning.monotonicstack` |
| `com.learning.question2` | `com.learning.slidingwindow` |
| `com.learning.question3` | `com.learning.linkedlist` |
| `com.learning.question4` | `com.learning.binarysearch` |
| `com.learning.prefixSum` | `com.learning.prefixsum` |

Exception: `MaxSumSubArray` moves from `com.learning.prefixSum` to
`com.learning.dynamicprogramming`, matching Kadane's recurrence.

Central examples live in `AlgorithmExamples`; newer upstream solutions also
retain their standalone `main` demos. Computation methods return results without
printing debug output. `ProductArray.method1` and `method2` are deprecated aliases
that now return an `int[]`; use `productExceptSelf` for new code. Recompile
callers after this change. `ListNode` is now public with a validated, final digit
and a mutable next link. `Pattern132.find132pattern` remains as a compatibility
entry point for the optimized detector. Interval merging now copies caller input.

See [architecture](docs/ARCHITECTURE.md), [pattern guide](docs/PATTERNS.md),
and [contribution standards](CONTRIBUTING.md).
