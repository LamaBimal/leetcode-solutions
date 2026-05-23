package com.learning.question3;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

class IntegerSumTest {

    @Test
    void generateNumberFromList() {
        // [2, 4, 3] represents 342 (least significant digit first)
        LinkedList<Integer> list = new LinkedList<>();
        list.add(2); list.add(4); list.add(3);
        assertEquals(342, IntegerSum.generateNumber(list));
    }

    @Test
    void convertNumberToList() {
        LinkedList<Integer> result = IntegerSum.convertToList(807);
        assertEquals(3, result.size());
        assertEquals(7, result.get(0));
        assertEquals(0, result.get(1));
        assertEquals(8, result.get(2));
    }

    @Test
    void sumOfTwoLists() {
        // [2,4,3] = 342, [5,6,4] = 465 → sum = 807
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(2); l1.add(4); l1.add(3);

        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(5); l2.add(6); l2.add(4);

        int sum = IntegerSum.generateNumber(l1) + IntegerSum.generateNumber(l2);
        assertEquals(807, sum);

        LinkedList<Integer> output = IntegerSum.convertToList(sum);
        assertEquals(7, output.get(0));
        assertEquals(0, output.get(1));
        assertEquals(8, output.get(2));
    }

    @Test
    void singleDigitLists() {
        LinkedList<Integer> l1 = new LinkedList<>();
        l1.add(3);
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(7);

        int sum = IntegerSum.generateNumber(l1) + IntegerSum.generateNumber(l2);
        assertEquals(10, sum);
    }
}
