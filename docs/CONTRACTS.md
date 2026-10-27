# Input and result contracts

[Back to the README](../README.md) · [Solution catalog](../README.md#solution-catalog)

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

