package me.wobblyyyy.edt;

import org.junit.Test;

import java.util.HashMap;

public class MapTester {
    private void runTestA() {
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put(
                    "Key! " + i,
                    i * Math.PI
            );
        }
    }

    private void runTestB() {
        DynamicMap<String, Double> map = new DynamicMap<>();

        for (int i = 0; i < 10; i++) {
            map.add(
                    "Key! " + i,
                    i * Math.PI
            );
        }
    }

    @Test
    public void testMapSpeed() {
        long aB = System.nanoTime();
        runTestA();
        long aA = System.nanoTime();

        long bB = System.nanoTime();
        runTestB();
        long bA = System.nanoTime();

        System.out.println("Test A time: " + (aA - aB));
        System.out.println("Test B time: " + (bA - bB));
    }
}
