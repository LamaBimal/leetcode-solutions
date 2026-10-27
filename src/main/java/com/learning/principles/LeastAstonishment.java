package com.learning.principles;

/**
 * Principle of Least Astonishment (POLA)
 *
 * Code should behave in a way that doesn't surprise the reader or caller.
 * Method names, return values, and side effects should match expectations.
 *
 * BAD: a method called "getSize" that also clears the collection — shocking.
 * GOOD: getSize() only reads; clear() only clears. No hidden surprises.
 */
public class LeastAstonishment {

    // BAD: surprising side effect hidden inside a getter
    public static class SurprisingList {
        private final java.util.List<String> items = new java.util.ArrayList<>();

        public void add(String item) { items.add(item); }

        // Caller expects a read — gets a destructive side effect instead
        public int getSizeAndClear() {
            int size = items.size();
            items.clear(); // SURPRISE — nobody expects a getter to clear data
            return size;
        }
    }

    // GOOD: each method does exactly what its name says
    public static class PredictableList {
        private final java.util.List<String> items = new java.util.ArrayList<>();

        public void add(String item)  { items.add(item); }
        public int size()             { return items.size(); }   // query only
        public void clear()           { items.clear(); }         // command only
        public java.util.List<String> getAll() {
            return java.util.Collections.unmodifiableList(items); // no mutation
        }
    }
}
