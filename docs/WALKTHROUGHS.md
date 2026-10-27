# Algorithm walkthroughs

These examples connect a simple baseline to the invariant that makes an optimized
solution correct. See the [catalog](../README.md#solution-catalog) for all solutions
and [contracts](CONTRACTS.md) for input assumptions and failure behavior.

## Median of two sorted arrays

[Implementation](../src/main/java/com/learning/binarysearch/TwoArraysMedian.java)
· [Regression tests](../src/test/java/com/learning/binarysearch/TwoArraysMedianRegressionTest.java)

Given two ascending arrays, find the middle value of their combined order, or the
average of the two middle values for an even total. At least one array must be
nonempty. Inputs remain unchanged; sortedness is a caller precondition.

### Start with a baseline

Merge the arrays by repeatedly taking the smaller available head, then read the
middle element or elements. Materializing the merge takes O(m + n) time and space.
Keeping only the last two values and stopping at the middle reduces auxiliary
space to O(1), but still takes O(m + n) time in the worst case.

The optimized method avoids constructing or visiting the entire combined order.
It searches for a partition with the right number of values on each side.

### Partition invariant

Call the shorter array A, with length m, and the longer array B, with length n.
Let `L = (m + n + 1) / 2` using integer division. Choose a cut `i` in A, and let
`j = L - i` be the cut in B. The left side always contains exactly L values.
Because m <= n and 0 <= i <= m, the corresponding j stays within B's bounds.

Each array is already sorted internally. Therefore, the combined left side is
no larger than the combined right side precisely when these cross-boundary
conditions both hold:

```text
A[i - 1] <= B[j]
B[j - 1] <= A[i]
```

Treat missing left boundaries as negative infinity and missing right boundaries
as positive infinity. The implementation uses integer minimum and maximum
sentinels, which work with the non-strict comparisons even at those input limits.

If `A[i - 1] > B[j]`, the cut in A is too far right: moving it farther right can
only increase A's left boundary and decrease B's right boundary. Discard that
cut and all larger cuts. If `B[j - 1] > A[i]`, move the cut in A right instead.
Each step preserves the possibility of a valid cut in the remaining interval.

### Worked trace

For `A = [1, 3]` and `B = [2, 4, 5, 6]`, L is 3:

| Cut i | Cut j | A left / right | B left / right | Decision |
| --- | --- | --- | --- | --- |
| 1 | 2 | 1 / 3 | 4 / 5 | 4 > 3, so move i right |
| 2 | 1 | 3 / +infinity | 2 / 4 | Both cross-boundary conditions hold |

The left side is `[1, 3] + [2]`; the right side is `[] + [4, 5, 6]`.
For an even total, average the largest left value and smallest right value:
`(3 + 4) / 2.0 = 3.5`. For an odd total, return the largest left value.
The implementation widens the addition to long before averaging to avoid int
overflow. Duplicates are valid; two empty arrays are rejected.

### Tradeoff and independent verification

Binary search examines O(log(min(m, n) + 1)) cuts and uses O(1) auxiliary space.
It is faster asymptotically than merging but requires more careful boundary logic.
A merge is often easier to explain and serves as a useful baseline.

The existing randomized test uses an even simpler independent oracle: concatenate
both inputs, sort the combined array, and read its middle. That oracle costs
O((m + n) log(m + n)) time and O(m + n) space, which is acceptable for small test
inputs. Five hundred seeded cases complement explicit empty-side, odd/even, and
integer-limit cases; they do not replace those boundary tests or prove correctness.

## Sorted pair search

[Implementation](../src/main/java/com/learning/twopointers/SumTwoNumber.java)
· [Independent-baseline tests](../src/test/java/com/learning/twopointers/TwoPointerAlgorithmsTest.java)

The baseline checks every pair of distinct indices in O(n²) time. The optimized
method begins at the smallest and largest values. If their sum is below the
target, the current left value cannot work with any remaining right value, so
discard it. If the sum is above the target, discard the right value symmetrically.
The invariant is that any remaining solution lies within the active interval.

For `[1, 2, 4, 7]` and target 6: 1 + 7 is too large, 1 + 4 is too small,
and 2 + 4 succeeds. Each step eliminates one index, giving O(n) time and O(1)
space. Using `left < right` prevents reusing the same element. Wide sums prevent
overflow from changing the comparison. This reasoning requires sorted input.

Five hundred seeded cases compare the implementation with exhaustive pair
enumeration, including duplicates, empty arrays, negatives, and integer limits.
Each case also checks that the input is unchanged.

## Trapping rainwater

[Implementation](../src/main/java/com/learning/twopointers/TrappingWater.java)
· [Independent-baseline tests](../src/test/java/com/learning/twopointers/TwoPointerAlgorithmsTest.java)

At position i, water depth is the smaller of the maximum heights on its left and
right, minus its own height. Include i in both maxima so the result is nonnegative.
A direct scan for both maxima at every position costs O(n²) time and O(1) space.

For `[3, 0, 2, 0, 4]`, the depths are `[0, 3, 1, 3, 0]`, totaling 7.
The production method instead moves inward from both ends, maintaining the
maximum seen from each side. It processes the lower current endpoint: the
opposite endpoint provides a sufficient boundary while that side's running
maximum determines the water contribution. Any previously established larger
maximum was encountered with a sufficient opposite boundary before that pointer
could advance. Thus processed positions have final contributions, and only the
unprocessed interval remains.

Each pointer moves in one direction, giving O(n) time and O(1) auxiliary space.
Heights must be nonnegative; the accumulated result is checked before narrowing
to int. Five hundred seeded small profiles are compared with the direct boundary
scans, and inputs must remain unchanged. Explicit tests separately cover invalid
heights and totals beyond the int range.
