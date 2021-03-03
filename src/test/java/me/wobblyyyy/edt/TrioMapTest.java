package me.wobblyyyy.edt;

import org.junit.Test;

public class TrioMapTest {
    @Test
    public void testTrioMap() {
        String stringA = "String A";
        String stringB = "String B";
        String stringC = "String C";

        TrioMap<String, Integer, Double> testMap = new TrioMap<>();

        testMap.add(
                stringA,
                10,
                11.3
        );

        System.out.println("B from A: " + testMap.getWithA(stringA).getB());
        System.out.println("C from A: " + testMap.getWithA(stringA).getB());
    }
}
