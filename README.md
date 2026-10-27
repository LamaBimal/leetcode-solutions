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

`verify` also enforces basic Java style with Checkstyle and checks selected
error-prone coding patterns with PMD. Both include production and test sources.
The rules live in `config/`; violations fail the build. These focused checks
complement code review rather than asserting complete style or defect coverage.

JaCoCo generates line and branch coverage at `target/site/jacoco/index.html`
and machine-readable coverage at `target/site/jacoco/jacoco.xml`. The report
includes teaching examples and standalone demos; there is no percentage gate.
Use uncovered branches to choose useful tests rather than treating a test count
or coverage percentage as proof of correctness.

## Verification snapshot

Local verification on **27 October 2026**, covering the implementation in
commit `869a1a9`:

| Check | Result |
| --- | --- |
| Maven verification on Java 8 and Java 17 | Passed on both runtimes |
| JUnit tests | 196 passed; zero failures, errors, or skipped tests |
| Checkstyle | Zero violations across 88 production and test source files |
| PMD | Zero violations against the configured rules |
| JaCoCo line coverage | 78.1% |
| JaCoCo branch coverage | 90.1% |

Coverage includes all production classes, teaching examples, and standalone
demos. These figures are a dated snapshot; regenerate the reports with `verify`
after changing code. Java 21 is included in the CI matrix but was not part of
this local verification. After pushing changes, inspect the repository's
[Actions page](https://github.com/LamaBimal/leetcode-solutions/actions) for remote
CI results.

## Engineering improvements

- Standard Maven layout and lowercase pattern packages make solutions easier to
  locate and import. All 39 upstream source files and 39 upstream test files
  were preserved during integration, with additional models and regression tests.
- Public tree and list nodes support callers outside the algorithm packages;
  consumer-package tests exercise construction, traversal, cycle detection,
  midpoint search, and in-place reversal.
- Numeric validation rejects NaN, infinities, and deposits that overflow the
  finite balance range before changing state. Boundary tests cover absolute zero
  and rejected updates that preserve the previous value.
- The Law of Demeter example delegates city lookup through `Customer`, preserving
  specialized customer behavior without navigating its address from `Order`.
- Correctness fixes cover zero-step stair counting, the returned digit-list head,
  products containing zeros, and arithmetic overflow in sums and interval sorting.
  Independent randomized checks exercise median partitioning and 132 detection.
- The verification lifecycle enforces the configured style and analysis rules and
  generates coverage reports through the pinned Maven wrapper.

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
config/                  Checkstyle and PMD rules enforced by verify
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
  addition (null represents zero), tree traversals, midpoint search, and cycle
  detection (null is empty), list reversal (null is empty), and island counting
  (null returns zero).
- Empty arrays and strings return zero, false, or an empty result as appropriate.
  Maximum subarray rejects empty input; median rejects two empty arrays.
- Most algorithms leave caller inputs unchanged. Island counting, flood fill,
  and linked-list reversal explicitly modify their inputs in place. Addition
  allocates new nodes; array products and merged intervals return new arrays.
- Sorted-array methods require ascending inputs. List operations other than
  cycle detection require acyclic inputs.
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
production persistence or banking implementations. The bank-account example
requires finite balances and positive finite transaction amounts; deposits that
would overflow the finite range fail without changing the balance. Thermometer
values must be finite and at least -273.15 Celsius.

Tree and general-list algorithms expose public static nested node classes with
public constructors, immutable values, and mutable links. For example, external
callers can construct `BinaryTreeLevelOrder.TreeNode` and connect its `left` and
`right` fields. Separate consumer-package tests verify these public APIs. The
digit-addition node remains `com.learning.linkedlist.ListNode`, enforcing 0..9.

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

Previously package-private tree and general-list node types are now public;
their class names remain unchanged, and their `val` fields are now final.
Code that assigned directly to a node value must construct a replacement node.
Nonfinite inputs to the validation examples now throw `IllegalArgumentException`.

See [architecture](docs/ARCHITECTURE.md), [pattern guide](docs/PATTERNS.md),
and [contribution standards](CONTRIBUTING.md).
