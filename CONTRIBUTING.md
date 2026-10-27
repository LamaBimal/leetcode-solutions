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
7. Update the README solution catalog and migration notes when contracts change.
8. Run `.\mvnw.cmd verify` on Windows or `sh ./mvnw verify` on Linux/macOS,
   and inspect the diff before submitting. An installed Maven can run `mvn verify`.

## Numeric and collection contracts

Use long intermediates when an int sum can overflow. Check narrowing conversions
and integer products. Document intentional input bounds. Return computed values
instead of printing them. Reject invalid input consistently with the README.

Do not mutate caller-owned arrays or lists unless the method explicitly promises
an in-place operation. Use interfaces for collection variables and modern JDK
collections such as `ArrayDeque` in place of the legacy `Stack` class.

## Review expectations

A change should have a clear problem, the resulting behavior, and verification
evidence. Explain any package migration, complexity change, input mutation, numeric
limit, or new dependency. Keep examples runnable and production code dependency-free.
