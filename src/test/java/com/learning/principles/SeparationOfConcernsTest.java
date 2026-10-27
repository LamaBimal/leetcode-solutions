package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SeparationOfConcernsTest {

    @Test
    void repositorySavesAndFinds() {
        SeparationOfConcerns.ProductRepository repo = new SeparationOfConcerns.ProductRepository();
        SeparationOfConcerns.Product p = new SeparationOfConcerns.Product(1, "Widget", 10.0);
        repo.save(p);
        assertEquals("Widget", repo.findById(1).name);
    }

    @Test
    void serviceAppliesDiscount() {
        SeparationOfConcerns.ProductRepository repo = new SeparationOfConcerns.ProductRepository();
        repo.save(new SeparationOfConcerns.Product(1, "Widget", 100.0));
        SeparationOfConcerns.ProductService service = new SeparationOfConcerns.ProductService(repo);
        assertEquals(80.0, service.discountedPrice(1, 0.20), 1e-9);
    }

    @Test
    void presenterFormatsProduct() {
        SeparationOfConcerns.Product p = new SeparationOfConcerns.Product(1, "Widget", 9.99);
        SeparationOfConcerns.ProductPresenter presenter = new SeparationOfConcerns.ProductPresenter();
        assertEquals("[1] Widget — $9.99", presenter.format(p));
    }
}
