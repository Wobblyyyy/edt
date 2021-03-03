package me.wobblyyyy.edt.dynarrays;

import org.junit.Test;

import java.util.ArrayList;

public class DoubleDynarrayTest {
    private void runTestA(double c) {
        DoubleDynarray array = new DoubleDynarray((int) (c * 1100), 10000);

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
        runTestA(1031);
        timeTestA = System.nanoTime() - timeTestA;

        timeTestB = System.nanoTime();
        runTestB(1031);
        timeTestB = System.nanoTime() - timeTestB;

        System.out.println("Time A: " + timeTestA);
        System.out.println("Time B: " + timeTestB);

        timeTestA = System.nanoTime();
        runTestA(4114);
        timeTestA = System.nanoTime() - timeTestA;

        timeTestB = System.nanoTime();
        runTestB(4114);
        timeTestB = System.nanoTime() - timeTestB;

        System.out.println("Time A: " + timeTestA);
        System.out.println("Time B: " + timeTestB);

        timeTestA = System.nanoTime();
        runTestA(41452);
        timeTestA = System.nanoTime() - timeTestA;

        timeTestB = System.nanoTime();
        runTestB(41452);
        timeTestB = System.nanoTime() - timeTestB;

        System.out.println("Time A: " + timeTestA);
        System.out.println("Time B: " + timeTestB);
    }
}
