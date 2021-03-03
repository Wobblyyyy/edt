package me.wobblyyyy.edt;

import org.junit.Test;

import java.util.HashMap;

public class MapTester {
    int times = 100;
    int reps = 100;

    private void runTestA() {
        HashMap<String, Double> map = new HashMap<>();

        for (int i = 0; i < times; i++) {
            map.put(
                    "Key! " + i,
                    i * Math.PI
            );
        }

        double b = 0;

        for (int i = 0; i < times; i++) {
//            b = map.get("Key! " + i);
        }
    }

    private void runTestB() {
        DynamicMap<String, Double> map = new DynamicMap<>();

        for (int i = 0; i < times; i++) {
            map.add(
                    "Key! " + i,
                    i * Math.PI
            );
        }

        double b = 0;

        for (int i = 0; i < times; i++) {
//            b = map.get("Key! " + i);
        }
    }

    @Test
    public void testMapSpeed() {
        double averageA = 0;
        double averageB = 0;

        DynamicArray<Double> timesA = new DynamicArray<>();
        DynamicArray<Double> timesB = new DynamicArray<>();

        long stopwatch;

        for (int i = 0; i < reps; i++) {
            stopwatch = System.nanoTime();
            runTestA();
            runTestA();
            timesA.add(System.nanoTime() - stopwatch * 1.0);

            stopwatch = System.nanoTime();
            runTestB();
            runTestB();
            timesB.add(System.nanoTime() - stopwatch * 1.0);
        }

        for (Object o : timesA.toArray()) {
            averageA += Double.parseDouble(o.toString());
        }

        for (Object o : timesB.toArray()) {
            averageB += Double.parseDouble(o.toString());
        }

        averageA /= timesA.size();
        averageB /= timesB.size();

        System.out.println("Test A time: " + averageA);
        System.out.println("Test B time: " + averageB);
    }
}
