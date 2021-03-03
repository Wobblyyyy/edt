package me.wobblyyyy.edt;

import me.wobblyyyy.edt.DynamicArray;
import org.junit.Test;

import java.util.Arrays;

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
}
