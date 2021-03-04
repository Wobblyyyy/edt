package me.wobblyyyy.edt;

import org.junit.Test;

public class StaticArrayTest {
    @Test
    public void testStaticArray() {
        Double[] doubles = new Double[] {10.0, 20.0, 30.0};
        StaticArray<Double> array = new StaticArray<>(doubles);

        array.itr().forEach(v -> {
            System.out.println("Value: " + v);
            System.out.println("Index of: " + array.indexOf(v));
        });
    }

    @SuppressWarnings("RedundantArrayCreation")
    @Test
    public void fromDynamicArray() {
        DynamicArray<String> dynamic = new DynamicArray<>(new String[] {
                "String 1",
                "String 2",
                "String 3"
        });
        StaticArray<String> staticArray = new StaticArray<>(dynamic);
        DynamicArray<String> normal = new DynamicArray<>(staticArray);

        normal.itr().forEach(v -> System.out.println("Value: " + v));
    }
}
