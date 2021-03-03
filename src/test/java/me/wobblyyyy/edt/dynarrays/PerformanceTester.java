package me.wobblyyyy.edt.dynarrays;

import org.junit.Test;

import java.util.ArrayList;

public class PerformanceTester {
    private void runTestA(double c) {
        DynamicArray<Double> array = new DynamicArray<Double>(
                (int) (0),
                new Double[] {}
        );

        for (int i = 0; i < c * 1000; i++) {
            array.add(i * 3 * c);
        }
    }

    private void runTestB(double c) {
        ArrayList<Double> array = new ArrayList<>((int) (0));

        for (int i = 0; i < c * 1000; i++) {
            array.add((double) (i * 3 * c));
        }
    }

    @Test
    public void testAdd() {
        double timeTestA;
        double timeTestB;

        timeTestA = System.nanoTime();
        runTestA(1000);
        runTestA(2000);
        runTestA(3000);
        timeTestA = System.nanoTime() - timeTestA;

        timeTestB = System.nanoTime();
        runTestB(1000);
        runTestB(2000);
        runTestB(3000);
        timeTestB = System.nanoTime() - timeTestB;

        double delta = (timeTestA / timeTestB);

        System.out.println("Time A: " + timeTestA);
        System.out.println("Time B: " + timeTestB);
        System.out.println("Time Delta: " + delta);
    }
}
