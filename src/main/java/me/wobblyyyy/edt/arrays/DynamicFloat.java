package me.wobblyyyy.edt.arrays;

import me.wobblyyyy.edt.Arrayable;
import me.wobblyyyy.edt.DynamicArray;

import java.util.ArrayList;

/**
 * A specialized dynamic array type designed to reduce clutter in code (and
 * the amount of those damn diamond brackets).
 *
 * <p>
 * This class doesn't actually provide any utility other than making it easier
 * to construct and access a specific type of dynamic array.
 * </p>
 *
 * @author Colin Robertson
 */
public class DynamicFloat extends DynamicArray<Float> {
    /**
     * Create a new {@code DynamicArray} instance without any values or
     * elements. Because there are no values or elements inserted into the
     * array at construction, the array will be entirely empty. New values
     * and elements can be added to the {@code DynamicArray} using any of the
     * insertion or append methods provided in this class.
     *
     * @see DynamicArray#add(Object)
     * @see DynamicArray#add(Object[])
     * @see DynamicArray#add(int, Object)
     */
    public DynamicFloat() {
        super();
    }

    /**
     * Create a new {@code DynamicArray} instance using a plain array of
     * elements as the initialization elements. These elements can be updated
     * later with the methods provided in the {@link DynamicArray} class.
     *
     * @param elements an array, containing all of the {@link Object} instances
     *                 that should be set as the {@code DynamicArray}'s
     *                 contents upon construction.
     */
    public DynamicFloat(Float... elements) {
        super(elements);
    }

    /**
     * Create a new {@code DynamicArray} instance without any data. Instead of
     * data, we have a lovely integer, representing the minimum allocation
     * size of the array. This allocation size can be used to boost performance.
     * Allocating a larger size uses more memory but reduces CPU time when
     * dealing with arrays of that size of slightly smaller.
     *
     * @param minSize the minimum size of the array. By default, this value is
     *                10. Larger arrays can have sizes up to 10,000, 100,000,
     *                or even 1,000,000 - it's entirely up to you.
     */
    public DynamicFloat(int minSize) {
        super(minSize);
    }

    /**
     * Create a new {@code DynamicArray} instance using a plain array of
     * elements as the initialization elements. These elements can be updated
     * later with the methods provided in the {@link DynamicArray} class.
     *
     * @param minSize  the array's minimum size.
     * @param elements an array, containing all of the {@link Object} instances
     *                 that should be set as the {@code DynamicArray}'s
     */
    public DynamicFloat(int minSize, Object[] elements) {
        super(minSize, elements);
    }

    /**
     * Create a new {@code DynamicArray} instance by using an {@code ArrayList}
     * instance to provide initialization values for the newly-constructed
     * array.
     *
     * @param elements an {@code ArrayList} of elements that should be
     *                 set to the dynamic array's contents. These elements
     *                 must be of the same type the newly-created
     *                 {@code DynamicArray} is of.
     */
    public DynamicFloat(ArrayList<Float> elements) {
        super(elements);
    }

    /**
     * Create a new {@code DynamicArray} instance by using an {@code Arrayable}
     * object as the "base" that the dynamic array should be based on. This
     * can be useful when converting a static array to a dynamic array.
     *
     * @param arrayable the array object that should be the base of the new
     *                  dynamic array.
     */
    public DynamicFloat(Arrayable<Float> arrayable) {
        super(arrayable);
    }
}
