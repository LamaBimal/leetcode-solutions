# Guide architecture

The static guide runs locally and on GitHub Pages without accounts or third-party scripts.

## Boundaries

- `docs/assets/*models.js`: deterministic teaching algorithms, independent of browser rendering.
- `lab-ui.js` or `atlas-app.js`: controls and rendering.
- `*enhancements.js`: navigation, progress, structural views, and additional exercises.
- `complexity.js`: counted growth examples shared in content across both repositories.

Browser teaching models do not execute Java/Python. Cross-language parity tests run actual repository APIs against matching inputs and compare final results. This guards semantic drift; it does not assert that instructional frames correspond one-to-one to language execution steps. Numeric domains remain bounded by the UI contracts.

Progress is optional local storage, versioned and scoped to the guide. Storage denial does not prevent learning. It is not synced across devices. No analytics are sent.

## Validation

Pattern Lab: `node scripts/test-learning-lab.cjs`, `node scripts/test-learning-parity.cjs` (requires javac/java), then `node scripts/build-learning-site.cjs`. The builder inlines local modules into a portable HTML distribution. All 15 algorithm lessons have Java parity cases, including empty and boundary cases.

Atlas: `node scripts/test-guide.cjs` (requires Python). All five search strategies are compared with actual Python functions; A* is loaded from the notebook's function definition without running plotting/demo cells. Five graph presets exercise no-route, cycle, tie, and reopening behavior.

Big O counts are illustrative operations, not elapsed-time benchmarks. Trace storage and input copies are separate from algorithmic auxiliary memory. The two repositories keep identical complexity module content; changes should be mirrored.

## Manual browser regression

Check deep links and browser Back/Forward, keyboard controls, storage denial, invalid inputs, preset changes, quiz answer locking, graph frontier labels, and printing. At narrow widths, permit scrolling within tables/diagrams while preventing document-wide overflow. Check screen-reader announcements without autoplay flooding them.
