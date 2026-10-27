package com.learning.principles;

/**
 * YAGNI - You Aren't Gonna Need It
 *
 * Don't add functionality until it is actually required.
 *
 * BAD: UserService pre-builds export, analytics, and audit features nobody asked for.
 * GOOD: implement only what is needed right now — save, findById.
 */
public class YAGNI {

    public static class User {
        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId()     { return id; }
        public String getName() { return name; }
    }

    // GOOD: only what is currently needed
    public static class UserService {
        private final java.util.Map<Integer, User> store = new java.util.HashMap<>();

        public void save(User user) {
            store.put(user.getId(), user);
        }

        public User findById(int id) {
            return store.get(id);
        }

        // BAD (commented out): pre-built features nobody asked for yet
        // public byte[] exportToCsv() { ... }
        // public Map<String, Integer> analytics() { ... }
        // public List<String> auditLog() { ... }
    }
}
