package me.wobblyyyy.edt.dynarrays;

import org.junit.Test;

import java.util.ArrayList;

public class DoubleDynarrayTest {
    private void runTestA(double c) {
        DoubleDynarray array = new DoubleDynarray((int) (c * 1000), 10000);

        for (int i = 0; i < c * 1000; i++) {
            array.add(i * 3 * c);
        }
    }

    private void runTestB(double c) {
        ArrayList<Double> array = new ArrayList<>();

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
        timeTestA = System.nanoTime() - timeTestA;

        timeTestB = System.nanoTime();
        runTestB(1000);
        timeTestB = System.nanoTime() - timeTestB;

        double delta = 100 - (timeTestA / timeTestB);

        System.out.println("Time A: " + timeTestA);
        System.out.println("Time B: " + timeTestB);
        System.out.println("Time Delta: " + delta);
    }
}
