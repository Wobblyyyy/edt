package me.wobblyyyy.edt;

import org.junit.Test;

import java.util.HashMap;

public class MapTester {
    int itrTimes = 13000;
    int times = 1000;
    int reps = 10;

    private void runTestA() {
        HashMap<String, Double> map = new HashMap<>(1000);

        for (int i = 0; i < times; i++) {
            map.put(
                    "Key! " + i,
                    i * Math.PI
            );
        }

        double b = 0;

        for (int i = 0; i < times; i++) {
            b = map.get("Key! " + i);
        }

        for (int i = 0; i < times; i++) {
            map.remove("Key! " + i);
        }
    }

    private void runTestB() {
        DynamicMap<String, Double> map = new DynamicMap<>(1000);

        for (int i = 0; i < times; i++) {
            map.add(
                    "Key! " + i,
                    i * Math.PI
            );
        }

        double b = 0;

        for (int i = 0; i < times; i++) {
            b = map.get("Key! " + i);
        }

        for (int i = 0; i < times; i++) {
            map.remove("Key! " + i);
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
//            times = i * 100;

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

    public void testIteration(int s) {
        HashMap<String, Double> hashMap = new HashMap<>();
        DynamicMap<String, Double> dynamicMap = new DynamicMap<>();

        long h = System.nanoTime();
        for (int i = 0; i < itrTimes * s; i++) {
            hashMap.put("Key " + i, i * Math.PI);
        }
        System.out.println("Hash put time: " + (System.nanoTime() - h));

        long d = System.nanoTime();
        for (int i = 0; i < itrTimes * s; i++) {
            dynamicMap.add("Key " + i, i * Math.PI);
        }
        System.out.println("Dynamic put time: " + (System.nanoTime() - d));

        DynamicArray<String> strings = new DynamicArray<>(itrTimes);
        DynamicArray<Double> doubles = new DynamicArray<>(itrTimes);

        long hashMapStart = System.nanoTime();
        for (HashMap.Entry<String, Double> e : hashMap.entrySet()) {
            strings.add(e.getKey());
            doubles.add(e.getValue());
        }
        long hashMapTime = System.nanoTime() - hashMapStart;

        strings = new DynamicArray<>(itrTimes);
        doubles = new DynamicArray<>(itrTimes);

        DynamicArray<String> finalStrings = strings;
        DynamicArray<Double> finalDoubles = doubles;

        long dynamicMapStart = System.nanoTime();
        dynamicMap.itr().forEach((k, v) -> {
            finalStrings.add(k);
            finalDoubles.add(v);
        });
        long dynamicMapTime = System.nanoTime() - dynamicMapStart;

        System.out.println("Hash Map Time: " + hashMapTime / 1000);
        System.out.println("Dynamic Map Time: " + dynamicMapTime / 1000);
    }

    @Test
    public void multiTestIteration() {
        for (int i = 0; i < 10; i++) {
            testIteration(i);
        }
    }

    @Test
    public void mapIterationTest() {
        DynamicMap<String, Double> map = new DynamicMap<>();

        for (int i = 0; i < itrTimes; i++) {
            map.add(
                    "Key " + i,
                    i * Math.PI
            );
        }

        map.itr().forEach((k, v) -> {
            try {
                System.out.println("Previous key: " +
                        map.itr().previousKey());
                System.out.println("Previous value: " +
                        map.itr().previous());
            } catch (Exception ignored) {

            }

            try {
                System.out.println("Next key: " +
                        map.itr().nextKey());
                System.out.println("Next value: " +
                        map.itr().next());
            } catch (Exception ignored) {

            }

            System.out.println("Key: " + k);
            System.out.println("Value: " + v);
            System.out.println("Index: " + map.itr().index());

            final double[] total = {0};
            map.itr().forEach((v1) -> total[0] += v1);
            System.out.println("Total: " + total[0]);

            System.out.println("");
        });
    }

    @Test
    public void styleTest() {
        DynamicMap<String, Double> map = new DynamicMap<>();
        map.add("test 1", 1.0);
        map.add("test 2", 1.0);

        map.itr().forEach(() -> {
            System.out.println(map.itr().index());
        });
    }
}
