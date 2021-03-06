package me.wobblyyyy.edt;

import me.wobblyyyy.edt.DynamicArray;
import org.junit.Test;

import java.util.Arrays;
import java.util.function.Consumer;

public class DynamicArrayTest {
    @Test
    public void testDynamicArray() {
        DynamicArray<Double> array = new DynamicArray<>();

        array.add(10.0);
        array.add(10.0);
        array.add(10.0);
        System.out.println(Arrays.toString(array.toArray()));

        array.add(10.0);
        array.add(10.0);
        array.add(10.0);
        System.out.println(Arrays.toString(array.toArray()));

        array.add(10.0);
        array.add(10.0);
        array.add(10.0);
        System.out.println(Arrays.toString(array.toArray()));

        array.add(10.0);
        array.add(10.0);
        array.add(10.0);
        System.out.println(Arrays.toString(array.toArray()));

        array.remove();
        array.remove();
        array.remove();
        System.out.println(Arrays.toString(array.toArray()));

        array.add(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0});
        System.out.println(Arrays.toString(array.toArray()));

        array.remove();
        array.remove();
        array.remove();
        array.remove();
        array.remove();
        array.remove();
        array.remove();
        array.remove();
        array.remove();
        System.out.println(Arrays.toString(array.toArray()));

        array.add(2, 8.2);
        System.out.println(Arrays.toString(array.toArray()));

        array.removeAfter(3);
        System.out.println(Arrays.toString(array.toArray()));

        System.out.println("Fake Size: " + array.size());
        System.out.println("Real Size: " + array.realSize());
        array.trim();
        System.out.println("Fake Size: " + array.size());
        System.out.println("Real Size: " + array.realSize());
        array.add(new Double[] {
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0,
                10.0
        });
        array.add(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0});
        System.out.println("Fake Size: " + array.size());
        System.out.println("Real Size: " + array.realSize());
        array.trim();
        array.add(new Double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0});
        array.trim();
        System.out.println("Fake Size: " + array.size());
        System.out.println("Real Size: " + array.realSize());
    }

    @Test
    public void testCasting() {
        DynamicArray<Double> array = new DynamicArray<>();

        array.add(10.0);

        Double[] realArray = array.toDoubleArray();
        System.out.println(Arrays.toString(realArray));
    }

    @Test
    public void testIteration() {
        DynamicArray<String> array = new DynamicArray<>(10);

        for (int i = 0; i <= 10; i++) {
            array.add("Very cool string! " + i);
        }

        array.itr().forEach(() -> {
            System.out.println(
                    "Reading string! On index: " + array.itr().index()
            );

            System.out.println(
                    "Current element: " + array.itr().element().toString()
            );

            try {
                System.out.println(
                        "Next element: " + array.itr().next().toString()
                );
            } catch (Exception ignored) {

            }
        });

        System.out.println("");
        System.out.println("");
        System.out.println("");

        array.itr().forEach(s -> {
            System.out.println("String = " + s);
        });
    }

    @Test
    public void testIterationSpeed() {
        DynamicArray<String> cool = new DynamicArray<>(1000);
        DynamicArray<String> array = new DynamicArray<>(100);

        for (int i = 0; i <= 100; i++) {
            array.add("String " + i);
        }

        long stopwatch = System.nanoTime();
        array.itr().forEach(cool::add);
        stopwatch = System.nanoTime() - stopwatch;

        System.out.println("Time: " + (stopwatch / 10000));
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void testConstruction() {
        DynamicArray<String> a1 = new DynamicArray<>() {{
            add("Test 1");
            add("Test 2");
            add("Test 3");
        }};
        DynamicArray<String> a2 = new DynamicArray<>(
                "Test 1",
                "Test 2",
                "Test 3"
        );
        DynamicArray<String> a3 = new DynamicArray<>(new String[] {
                "Test 1",
                "Test 2",
                "Test 3"
        });

        a1.itr().forEach((Consumer<String>) System.out::println);
        a2.itr().forEach((Consumer<String>) System.out::println);
        a3.itr().forEach((Consumer<String>) System.out::println);
    }
}
