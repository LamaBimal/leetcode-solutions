package com.learning.principles;

/**
 * Separation of Concerns (SoC)
 *
 * Different aspects of a program should live in separate, focused layers.
 * Here: Repository (data), Service (business logic), and Presenter (formatting)
 * are kept completely independent.
 */
public class SeparationOfConcerns {

    public static class Product {
        public final int id;
        public final String name;
        public final double price;

        public Product(int id, String name, double price) {
            this.id = id; this.name = name; this.price = price;
        }
    }

    // Data concern — only knows about storage
    public static class ProductRepository {
        private final java.util.Map<Integer, Product> db = new java.util.HashMap<>();

        public void save(Product p)       { db.put(p.id, p); }
        public Product findById(int id)   { return db.get(id); }
    }

    // Business logic concern — only knows about rules
    public static class ProductService {
        private final ProductRepository repo;

        public ProductService(ProductRepository repo) { this.repo = repo; }

        public double discountedPrice(int id, double rate) {
            Product p = repo.findById(id);
            if (p == null) throw new IllegalArgumentException("Product not found");
            return p.price * (1 - rate);
        }
    }

    // Presentation concern — only knows about formatting
    public static class ProductPresenter {
        public String format(Product p) {
            return String.format("[%d] %s — $%.2f", p.id, p.name, p.price);
        }
    }
}
