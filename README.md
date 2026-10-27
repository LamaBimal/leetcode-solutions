# LeetCode Solutions

A Java algorithm library for learning problem-solving patterns. Solutions are
organized by pattern, expose reusable methods, and include regression tests.
The production library uses only the JDK. The catalog contains 24 algorithm
classes spanning the 15 study patterns, plus 15 design-principle teaching examples
under `principles` and `solid`.

Project identifier: `leetcode-solutions`. Maven coordinates:
`com.learning:leetcode-solutions:1.0.0-SNAPSHOT`.

Start with the build below, choose a solution from the catalog, and read its tests
alongside the implementation. For design rationale and tradeoffs, see the
[architecture guide](docs/ARCHITECTURE.md).

## Contents

- [Requirements and build](#requirements-and-build)
- [Learning path](#learning-path)
- [Solution catalog](#solution-catalog)
- [Example](#example)
- [Input and result contracts](#input-and-result-contracts)
- [Project layout](#project-layout)
- [Verification snapshot](#verification-snapshot)
- [Engineering Standards](#engineering-standards)
- [Future Extensions](#future-extensions)
- [Contributing and further reading](#contributing-and-further-reading)
- [License](#license)

## Requirements and build

Clone the repository and enter its directory:

```sh
git clone https://github.com/LamaBimal/leetcode-solutions.git
cd leetcode-solutions
```

- JDK 8 or later.
- The included Maven wrapper downloads Maven 3.9.9; an existing Maven 3.6.3+
  installation can also run the build.
- Network access on the first build to resolve pinned dependencies.

On Windows, build, run tests and quality checks, then run the console examples:

```powershell
.\mvnw.cmd verify
java -cp target/classes com.learning.examples.AlgorithmExamples
```

On Linux or macOS, use `sh ./mvnw verify`. With Maven installed, `mvn verify`
is equivalent.

To run one test class while studying a solution:

```powershell
.\mvnw.cmd -Dtest=ClimbingStairsTest test
```

Use `sh ./mvnw -Dtest=ClimbingStairsTest test` on Linux or macOS. Run the full
`verify` lifecycle before submitting changes.

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

## Learning path

Use this sequence at your own pace. Each stage points to implemented examples in
this repository; the [pattern guide](docs/PATTERNS.md) explains when to choose
all 15 patterns and which assumptions to check.

| Stage | Patterns to practice | Start with these catalog entries |
| --- | --- | --- |
| 1. Arrays and strings | Two pointers, prefix sum, sliding window | `SumTwoNumber`, `LongestSubArray`, `LargestSubString` |
| 2. Linked lists | Fast/slow pointers, in-place reversal | `LinkedListCycle`, `ReverseLinkedList`; then digit addition with `ListNodeIntegerSum` |
| 3. Trees and grids | Tree traversal, BFS, DFS, matrix traversal | `BinaryTreeTraversal`, `BinaryTreeLevelOrder`, `NumberOfIslands`, `FloodFill` |
| 4. Ordered candidates | Binary search, monotonic stack, top K, overlapping intervals | `SearchRotatedArray`, `NextGreaterElement`, `KthLargestElement`, `MergeIntervals` |
| 5. Search and reusable subproblems | Backtracking, dynamic programming | `Subsets`, `ClimbingStairs`, `MaxSumSubArray` |

For each solution:

1. State the input constraints, expected output, and whether mutation is allowed.
2. Work through a small example and an edge case before reading the implementation.
3. Describe a straightforward approach, then identify the invariant or recurrence
   that makes the chosen pattern work.
4. Read the implementation and its tests. Derive time and space costs, including
   recursion and any copied data.
5. Implement it independently, test the boundaries, and explain the tradeoffs.

After the core patterns, compare the examples in `principles` and `solid` with the
[architecture decisions](docs/ARCHITECTURE.md). They demonstrate design concepts;
this repository's scope remains an algorithm learning library.

## Solution catalog

All packages below are relative to `com.learning`. Space excludes returned output.

| Problem and implementation | Method | Time | Auxiliary space |
| --- | --- | --- | --- |
| 132 pattern<br>[`monotonicstack.Pattern132`](src/main/java/com/learning/monotonicstack/Pattern132.java) · [Tests](src/test/java/com/learning/monotonicstack/Pattern132Test.java) | `contains132Pattern` | O(n) | O(n) |
| Longest substring without repeating characters<br>[`slidingwindow.LargestSubString`](src/main/java/com/learning/slidingwindow/LargestSubString.java) · [Tests](src/test/java/com/learning/slidingwindow/LargestSubStringTest.java) | `lengthOfLongestSubstring` | O(n) | O(u) |
| Distinct-character substrings of length three<br>[`slidingwindow.UniqueCharacterSubString`](src/main/java/com/learning/slidingwindow/UniqueCharacterSubString.java) · [Tests](src/test/java/com/learning/slidingwindow/UniqueCharacterSubStringTest.java) | `countGoodSubstrings` | O(n) | O(1) |
| Longest binary window with at most K flips<br>[`slidingwindow.MaxConsecutiveOnes`](src/main/java/com/learning/slidingwindow/MaxConsecutiveOnes.java) · [Tests](src/test/java/com/learning/slidingwindow/MaxConsecutiveOnesTest.java) | `findLongestOnes` | O(n) | O(1) |
| Add reverse-order digit lists<br>[`linkedlist.ListNodeIntegerSum`](src/main/java/com/learning/linkedlist/ListNodeIntegerSum.java) · [Tests](src/test/java/com/learning/linkedlist/ListNodeIntegerSumTest.java) | `sumTwoNumbers` | O(max(m,n)) | O(1) |
| Convert integers and digit lists<br>[`linkedlist.IntegerSum`](src/main/java/com/learning/linkedlist/IntegerSum.java) · [Tests](src/test/java/com/learning/linkedlist/IntegerSumTest.java) | `generateNumber`, `convertToList` | O(d) | O(1) |
| Median of two sorted arrays<br>[`binarysearch.TwoArraysMedian`](src/main/java/com/learning/binarysearch/TwoArraysMedian.java) · [Tests](src/test/java/com/learning/binarysearch/TwoArraysMedianTest.java) | `findMedianSortedArrays` | O(log(min(m,n) + 1)) | O(1) |
| Longest subarray with a target sum<br>[`prefixsum.LongestSubArray`](src/main/java/com/learning/prefixsum/LongestSubArray.java) · [Tests](src/test/java/com/learning/prefixsum/LongestSubArrayTest.java) | `findLongestSubArrayWithTarget` | O(n) | O(n) |
| Maximum subarray sum<br>[`dynamicprogramming.MaxSumSubArray`](src/main/java/com/learning/dynamicprogramming/MaxSumSubArray.java) · [Tests](src/test/java/com/learning/dynamicprogramming/MaxSumSubArrayTest.java) | `calculateMaxSumArray` | O(n) | O(1) |
| Product of array except self<br>[`prefixsum.ProductArray`](src/main/java/com/learning/prefixsum/ProductArray.java) · [Tests](src/test/java/com/learning/prefixsum/ProductArrayTest.java) | `productExceptSelf` | O(n) | O(1) |
| Pair with a target sum in a sorted array<br>[`twopointers.SumTwoNumber`](src/main/java/com/learning/twopointers/SumTwoNumber.java) · [Tests](src/test/java/com/learning/twopointers/SumTwoNumberTest.java) | `findTarget` | O(n) | O(1) |
| Trapping rainwater<br>[`twopointers.TrappingWater`](src/main/java/com/learning/twopointers/TrappingWater.java) · [Tests](src/test/java/com/learning/twopointers/TrappingWaterTest.java) | `calculateWaterAmount` | O(n) | O(1) |
| Climbing stairs<br>[`dynamicprogramming.ClimbingStairs`](src/main/java/com/learning/dynamicprogramming/ClimbingStairs.java) · [Tests](src/test/java/com/learning/dynamicprogramming/ClimbingStairsTest.java) | `countWays` | O(n) | O(1) |
| Climbing stairs<br>[`dynamicprogramming.ClimbingStairs`](src/main/java/com/learning/dynamicprogramming/ClimbingStairs.java) · [Tests](src/test/java/com/learning/dynamicprogramming/ClimbingStairsTest.java) | `countWaysWithTabulation` | O(n) | O(n) |
| Generate all subsets<br>[`backtracking.Subsets`](src/main/java/com/learning/backtracking/Subsets.java) · [Tests](src/test/java/com/learning/backtracking/SubsetsTest.java) | `subsets` | O(n * 2^n) | O(n) |
| Binary-tree level-order traversal<br>[`bfs.BinaryTreeLevelOrder`](src/main/java/com/learning/bfs/BinaryTreeLevelOrder.java) · [Tests](src/test/java/com/learning/bfs/BinaryTreeLevelOrderTest.java) | `levelOrder` | O(n) | O(w) |
| Search a rotated sorted array<br>[`binarysearch.SearchRotatedArray`](src/main/java/com/learning/binarysearch/SearchRotatedArray.java) · [Tests](src/test/java/com/learning/binarysearch/SearchRotatedArrayTest.java) | `search` | O(log n) | O(1) |
| Inorder, preorder, and postorder traversal<br>[`binarytree.BinaryTreeTraversal`](src/main/java/com/learning/binarytree/BinaryTreeTraversal.java) · [Tests](src/test/java/com/learning/binarytree/BinaryTreeTraversalTest.java) | `inorder`, `preorder`, `postorder` | O(n) | O(h) |
| Count islands in a grid<br>[`dfs.NumberOfIslands`](src/main/java/com/learning/dfs/NumberOfIslands.java) · [Tests](src/test/java/com/learning/dfs/NumberOfIslandsTest.java) | `numIslands` | O(rows * columns) | O(rows * columns) |
| Linked-list cycle detection and midpoint<br>[`fastslowpointers.LinkedListCycle`](src/main/java/com/learning/fastslowpointers/LinkedListCycle.java) · [Tests](src/test/java/com/learning/fastslowpointers/LinkedListCycleTest.java) | `hasCycle`, `findMiddle` | O(n) | O(1) |
| Reverse a list or a bounded segment<br>[`linkedlistreversal.ReverseLinkedList`](src/main/java/com/learning/linkedlistreversal/ReverseLinkedList.java) · [Tests](src/test/java/com/learning/linkedlistreversal/ReverseLinkedListTest.java) | `reverse`, `reverseBetween` | O(n) | O(1) |
| Flood fill a connected region<br>[`matrixtraversal.FloodFill`](src/main/java/com/learning/matrixtraversal/FloodFill.java) · [Tests](src/test/java/com/learning/matrixtraversal/FloodFillTest.java) | `floodFill` | O(rows * columns) | O(rows * columns) |
| Next greater element to the right<br>[`monotonicstack.NextGreaterElement`](src/main/java/com/learning/monotonicstack/NextGreaterElement.java) · [Tests](src/test/java/com/learning/monotonicstack/NextGreaterElementTest.java) | `nextGreaterElement` | O(n) | O(n) |
| Merge overlapping intervals<br>[`overlappingintervals.MergeIntervals`](src/main/java/com/learning/overlappingintervals/MergeIntervals.java) · [Tests](src/test/java/com/learning/overlappingintervals/MergeIntervalsTest.java) | `merge` | O(n log n) | O(n) |
| Kth-largest array element<br>[`topkelements.KthLargestElement`](src/main/java/com/learning/topkelements/KthLargestElement.java) · [Tests](src/test/java/com/learning/topkelements/KthLargestElementTest.java) | `findKthLargest` | O(n log(k + 1)) | O(k) |

Here, u is the number of distinct UTF-16 code units, d is the number of digits,
w is maximum tree width, and h is tree height. Recursive grid traversals can
consume one stack frame per cell and are limited by the JVM stack size.
Hash-based algorithms use expected constant-time map/set operations.
Maximum subarray uses Kadane's dynamic programming recurrence.

## Example

Add two numbers stored as reverse-order digit lists: `342 + 465 = 807`.
The statements below belong inside a method; the imports go at file scope.
For a runnable entry point, use
[AlgorithmExamples](src/main/java/com/learning/examples/AlgorithmExamples.java).

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

### Null, empty, and mutable inputs

- Reference inputs reject null with `NullPointerException`, except digit-list
  addition (null represents zero), tree traversals, midpoint search, and cycle
  detection (null is empty), list reversal (null is empty), and island counting
  (null returns zero).
- Empty arrays and strings return zero, false, or an empty result as appropriate.
  Maximum subarray rejects empty input; median rejects two empty arrays.
- Most algorithms leave caller inputs unchanged. Island counting, flood fill,
  and linked-list reversal explicitly modify their inputs in place. Addition
  allocates new nodes; array products and merged intervals return new arrays.

### Preconditions and numeric behavior

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

### Design examples and public node models

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

## Verification snapshot

Local verification on **27 October 2026**, covering the working-tree standards
updates based on commit `55c30c6`, using Java 8 and Java 17:

| Check | Result |
| --- | --- |
| Maven verification on Java 8 and Java 17 | Passed on both runtimes |
| JUnit tests | 196 passed; zero failures, errors, or skipped tests |
| Checkstyle | Zero violations across 88 production and test source files |
| PMD | Zero violations against the configured rules |
| JaCoCo line coverage | 77.9% |
| JaCoCo branch coverage | 90.1% |

Coverage includes all production classes, teaching examples, and standalone
demos. These figures are a dated snapshot; regenerate the reports with `verify`
after changing code. Java 21 is included in the CI matrix but was not part of
this local verification. After pushing changes, inspect the repository's
[Actions page](https://github.com/LamaBimal/leetcode-solutions/actions) for remote
CI results.

## Engineering Standards

The project follows these standards for implementation, review, and maintenance.
See [contribution standards](CONTRIBUTING.md) for the full contributor checklist.

- **Consistent structure and naming.** Use the standard Maven source and test
  directories, lowercase pattern packages, descriptive class and method names,
  and the formatting conventions in `.editorconfig`.
- **Focused, reusable algorithms.** Keep computation independent of console I/O,
  files, and global state. Return results through methods and use only JDK
  dependencies in production code. Console output belongs in the examples entry
  point or standalone `main` demos, never in computation methods.
- **Explicit API contracts.** Document preconditions, null and empty inputs,
  exceptions, mutation behavior, and time and auxiliary-space complexity.
  Public parameter and return types must be usable from an external package.
- **Safe numeric and state handling.** Use wide intermediates and checked
  conversions where overflow is possible. Enforce documented bounds, reject
  nonfinite values in finite domains, and validate before changing state.
  Preserve caller-owned data unless an operation explicitly promises mutation.
- **Behavior-focused testing.** Use JUnit Jupiter to cover representative cases,
  boundaries, failure contracts, and regressions. Use independent oracles for
  complex optimized algorithms and consumer-package tests for public node APIs.
- **Enforced quality checks.** Run the full Maven `verify` lifecycle before
  submitting changes. Configured Checkstyle and PMD violations fail the build
  for both production and test sources; automated checks complement code review.
- **Repeatable verification.** Use the pinned Maven wrapper and dependencies.
  Maintain Java 8 compatibility and the CI verification matrix for Java 8, 17,
  and 21. Report verification results with their runtime and commit context.
- **Meaningful coverage.** Inspect JaCoCo reports for untested behavior and
  branches. Coverage has no percentage gate; prioritize useful assertions over
  test counts or tests that mirror implementation details.
- **Documentation maintained with code.** Keep the solution catalog, complexity
  analysis, input contracts, and runnable examples aligned with implementation.
  Explain behavioral changes and design tradeoffs in reviews, with verification
  evidence appropriate to the change.

## Future Extensions

The following are proposed improvements, not currently implemented features.
Prioritize correctness and learning value while keeping the library easy to build,
read, and reuse.

| Priority | Extension | Intended outcome |
| --- | --- | --- |
| High | Iterative alternatives for recursive tree and grid traversals | Support deep trees and large connected regions without relying on JVM recursion depth; compare explicit-stack and queue tradeoffs. |
| High | Broader randomized and property-based testing | Extend independent reference checks to more algorithms and verify invariants such as input preservation, ordering, and result equivalence. |
| Medium | Richer problem walkthroughs | Add worked traces, alternative approaches, and verified original problem links alongside the existing source and test links. |
| Medium | Additional algorithm families | Introduce focused examples for union-find, topological sorting, tries, and weighted shortest paths, each with contracts, complexity analysis, and tests. |
| Medium | Automated verification artifacts | Publish test and coverage reports from CI so reviewers can inspect evidence for each change. |
| Later | Optional performance benchmarks | Compare algorithm variants with repeatable inputs and documented JVM settings; keep benchmark dependencies outside production code. |

Each extension should address a concrete learning or correctness need, include
appropriate tests, and update the solution catalog and affected contracts.
Introduce additional modules or dependencies only when their benefit justifies
the maintenance cost.

## Contributing and further reading

- [Contribution standards](CONTRIBUTING.md): adding solutions, documenting contracts,
  and running required checks.
- [Architecture guide](docs/ARCHITECTURE.md): module boundaries, design decisions,
  correctness policies, and tradeoffs.
- [Pattern guide](docs/PATTERNS.md): recognition cues and common pitfalls for all
  15 patterns, with prefix-sum, sliding-window, and traversal templates.
- [Tests](src/test/java/com/learning): worked examples, edge cases, and regression
  checks organized by package.

## License

This project is licensed under the [MIT License](LICENSE). Third-party files,
including the Maven wrapper, retain their own license notices.
