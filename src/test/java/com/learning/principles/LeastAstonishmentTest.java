package com.learning.principles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeastAstonishmentTest {

    @Test
    void sizeDoesNotClearList() {
        LeastAstonishment.PredictableList list = new LeastAstonishment.PredictableList();
        list.add("a");
        list.add("b");
        assertEquals(2, list.size());
        assertEquals(2, list.size()); // calling again — still 2, no surprise
    }

    @Test
    void clearEmptiesList() {
        LeastAstonishment.PredictableList list = new LeastAstonishment.PredictableList();
        list.add("a");
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    void getAllReturnsUnmodifiableView() {
        LeastAstonishment.PredictableList list = new LeastAstonishment.PredictableList();
        list.add("x");
        assertThrows(UnsupportedOperationException.class,
                () -> list.getAll().add("y"));
    }

    @Test
    void surprisingListClearsOnGetSize() {
        LeastAstonishment.SurprisingList list = new LeastAstonishment.SurprisingList();
        list.add("a");
        list.add("b");
        int size = list.getSizeAndClear(); // BAD: side effect hidden in getter
        assertEquals(2, size);
        // list is now empty — caller had no idea
        assertEquals(0, list.getSizeAndClear());
    }
}
