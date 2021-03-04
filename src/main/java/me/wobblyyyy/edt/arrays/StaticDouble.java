package me.wobblyyyy.edt.arrays;

import me.wobblyyyy.edt.Arrayable;
import me.wobblyyyy.edt.StaticArray;

/**
 * A specialized static array type designed to reduce clutter in code (and
 * the amount of those damn diamond brackets).
 *
 * <p>
 * This class doesn't actually provide any utility other than making it easier
 * to construct and access a specific type of static array.
 * </p>
 *
 * @author Colin Robertson
 */
public class StaticDouble extends StaticArray<Double> {
    /**
     * Create a new static array.
     *
     * <p>
     * Please note that when creating a static array, it is VERY STRONGLY
     * suggested that you make use of the constructor that accepts an array
     * as a parameter. Upcasting may sometimes cause issues here, which can
     * be very frustrating to debug.
     * </p>
     *
     * @param size how large the array should be.
     */
    public StaticDouble(int size) {
        super(size);
    }

    /**
     * Create a new static array.
     *
     * @param elements the array that the static array should use. This array
     *                 WILL NOT be copied. Remember, arrays are MUTABLE objects,
     *                 meaning that any reference to them returns the same object.
     *                 If you create a static array WITHOUT copying the array,
     *                 the array you passed to this constructor will be the
     *                 static array's array - modifying the array via the
     *                 methods in this class will modify that array as well.
     */
    public StaticDouble(Double... elements) {
        super(elements);
    }

    /**
     * Create a new {@code StaticArray} instance by using an {@code Arrayable}
     * object to get the newly-created static array's contents.
     *
     * @param arrayable the array object that should be the base of the new
     *                  dynamic array. Ex: {@link StaticArray} instance.
     */
    public StaticDouble(Arrayable<Double> arrayable) {
        super(arrayable);
    }
}
