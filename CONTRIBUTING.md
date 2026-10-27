# Contribution standards

## Adding a solution

1. Choose an existing lowercase pattern package, or introduce a narrowly named
   pattern package when needed.
2. Use a descriptive PascalCase class name and a camelCase operation name.
   Prefer problem terminology over numbered methods.
3. Keep algorithms independent of console input, output, files, and global state.
4. Document the problem, preconditions, empty-input behavior, mutation behavior,
   exceptions, time complexity, and auxiliary space.
5. Use four-space indentation, explicit imports, braces for control flow, and
   meaningful local names. Follow `.editorconfig`.
6. Add JUnit Jupiter tests under the matching `src/test/java` package. Cover a
   representative example, boundaries, failure contracts, and any bug regression.
   Use an independent oracle when testing a complex optimized algorithm.
7. Update the README solution catalog and [input contracts](docs/CONTRACTS.md)
   when behavior changes. Keep related [walkthroughs](docs/WALKTHROUGHS.md) aligned.
   Describe compatibility changes and their impact in the review description.
8. Run `.\mvnw.cmd verify` on Windows or `sh ./mvnw verify` on Linux/macOS,
   and inspect the diff before submitting. An installed Maven can run `mvn verify`.

The verification lifecycle fails on Checkstyle or PMD violations in production
or test code. Rules are in `config/checkstyle.xml` and `config/pmd.xml`; keep
changes narrowly justified. Braces are enforced for control flow. The basic style
gate does not replace the full
formatting and readability standards above.

Open `target/site/jacoco/index.html` after verification to inspect uncovered
branches relevant to your change. Coverage is reported across all production
code, including demos, and has no percentage threshold. Add tests for meaningful
behavior and failure contracts instead of reproducing private implementation.

## Numeric and collection contracts

Use long intermediates when an int sum can overflow. Check narrowing conversions
and integer products. Document intentional input bounds. Return computed values
instead of printing them. Reject invalid input consistently with the
[documented contracts](docs/CONTRACTS.md).
For floating-point domains with finite invariants, reject NaN and infinities
explicitly, check finite arithmetic results, and validate before mutating state.

Do not mutate caller-owned arrays or lists unless the method explicitly promises
an in-place operation. Use interfaces for collection variables and modern JDK
collections such as `ArrayDeque` in place of the legacy `Stack` class.

Public algorithm parameters and return types must be usable by an external
package. Add a consumer-package test when introducing or changing a node API.

## Review expectations

A change should have a clear problem, the resulting behavior, and verification
evidence. Explain any package migration, complexity change, input mutation, numeric
limit, or new dependency. Keep examples runnable and production code dependency-free.
