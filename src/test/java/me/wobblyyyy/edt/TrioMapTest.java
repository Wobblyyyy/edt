package me.wobblyyyy.edt;

import org.junit.Test;

public class TrioMapTest {
    @Test
    public void testTrioMap() {
        String stringA = "String A";
        String stringB = "String B";
        String stringC = "String C";

        TrioMap<String, String, String> testMap = new TrioMap<>();

        testMap.add(
                stringA,
                stringB,
                stringA
        );

        System.out.println("B from A: " + testMap.getWithA(stringA).getA());
        System.out.println("C from A: " + testMap.getWithA(stringA).getB());
    }
}
