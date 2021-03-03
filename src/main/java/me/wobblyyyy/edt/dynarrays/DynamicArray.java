package me.wobblyyyy.edt.dynarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An implementation of the dynamic array concept commonly seen in computer
 * science related fields. Although Java comes stock with several different
 * implementations of dynamic arrays, most of these are rather abstract and,
 * in turn, can lead to some performance losses.
 *
 * <p>
 * A dynamic array is an array that can be resized - meaning it can be grown,
 * shrunk, etc - whatever you'd like. Regular {@code Array} instances in Java
 * function fairly well already - they can store things and do tons of cool
 * stuff, y'know?
 * </p>
 *
 * <p>
 * The performance gains provided by utilizing a {@code DynamicArray} over
 * an {@link ArrayList}, for example, are rather noticeable. Performance tests
 * for an array 100,000 in size indicate that a {@code DynamicArray} instance
 * performs nearly 4x as fast as an {@code ArrayList} instance. These gains
 * don't transfer over nearly as cleanly to lower throughput operations,
 * however. As the {@code DynamicArray} can be effectively optimized for
 * dealing with tons of data, it performs faster on huge arrays. The
 * {@code ArrayList} gains a bit on the {@code DynamicArray} when it comes to
 * speed with lower-array-size operations, but the {@code DynamicArray} should
 * still outperform the {@code ArrayList}.
 * </p>
 *
 * @param <E> the type of elements stored in the dynamic array.
 * @author Colin Robertson
 */
public class DynamicArray<E> {
    /**
     * The default size of the dynamic array. We don't want to make this too
     * big or too small. Going too big means we'll end up allocating more
     * memory than we could ever even need, and going to small means we'll end
     * up having to grow the array very quickly.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * An empty object array. Whenever a {@code DynamicArray} instance is
     * created without any input values, the {@code DynamicArray} uses this
     * value as the element value.
     */
    private static final Object[] EMPTY = new Object[DEFAULT_SIZE];

    /**
     * The size of the currently-active portion of the array. Because this
     * {@code DynamicArray} class doesn't grow and shrink the array whenever
     * elements are added or removed in order to conserve processing power,
     * the active size of the array almost never matches the size of the
     * element array - where objects are actually stored.
     */
    private int activeSize;

    /**
     * The array's minimum size. This is used mostly in optimization for
     * very large arrays - say you have a {@code DynamicArray} instance that
     * you know will need to store thousands of objects. The minimum size of
     * the {@code DynamicArray} can be set somewhere in the thousands to
     * prevent having to grow the {@code DynamicArray} frequently.
     */
    private int minSize = 0;

    /**
     * All of the elements stored and used within the {@code DynamicArray}
     * instance. These elements are updated and queried via methods
     * provided in this class.
     */
    private Object[] elements;

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
    public DynamicArray() {
        elements = EMPTY;
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
    public DynamicArray(Object[] elements) {
        this.elements = elements;
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
    public DynamicArray(ArrayList<E> elements) {
        this.elements = elements.toArray();
    }

    /**
     * Get an out-of-bounds exception message based on a given index and
     * whether that index was too low or too high.
     *
     * @param h     if that index was too low or if it was too high. A state of
     *              true indicates that the index was too low, while a state of
     *              false indicates that the index was too high.
     * @param index the index that is out of bounds.
     * @param size  the maximum allowable size / index.
     * @return a {@code String} message that can be thrown as an exception
     * by another calling method. In this case, the calling method of this
     * method is almost always {@link DynamicArray#checkIndex(int)}.
     */
    private static String getOobException(boolean h,
                                          int index,
                                          int size) {
        String hm = h ? "LOW" : "HIGH";
        return "The specified index {" + index + "} is TOO " + hm + " " +
                "for a dynamic array of allocated size " + size;
    }

    /**
     * Check a given index to see if it is within the allowable range of
     * minimum and maximum index values. If it isn't, throw an exception
     * indicating that the specified index is invalid.
     *
     * @param index the index to check.
     */
    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException(getOobException(
                    true,
                    index,
                    elements.length
            ));
        } else if (index > activeSize) {
            throw new ArrayIndexOutOfBoundsException(getOobException(
                    false,
                    index,
                    elements.length
            ));
        }
    }

    /**
     * Check an array of index values by using the (provided elsewhere)
     * {@link DynamicArray#checkIndex(int)} method for the highest and lowest
     * index values. This operation can be expensive, as it requires the
     * array to be sorted before checking values.
     *
     * @param indices an array of the index values to check.
     */
    private void checkIndices(int[] indices) {
        Arrays.sort(indices);

        checkIndex(indices[0]);
        checkIndex(indices[indices.length - 1]);
    }

    /**
     * Calculate a new size for the array based on a minimum value. This method
     * over-estimates the array's new size to optimize CPU performance.
     *
     * @param minimum the minimum size of the array.
     * @return a new size for the array.
     */
    private int calculateNewSize(int minimum) {
        return (int) Math.ceil(minimum * 1.75);
    }

    /**
     * Calculate a minimum size requirement for the {@code DynamicArray} based
     * on the required growth of the array.
     *
     * @param required the required growth of the array.
     * @return a newly-calculated minimum.
     */
    private int calculateMinimum(int required) {
        return Math.max(
                minSize,
                required + activeSize
        );
    }

    /**
     * Grow the {@code DynamicArray} to extend to a specified minimum value.
     *
     * @param minimum the minimum value that the {@code DynamicArray} should
     *                extend to, at the very least.
     */
    private void grow(int minimum) {
        int newSize = calculateNewSize(minimum);
        elements = Arrays.copyOf(elements, newSize);
    }

    /**
     * Grow the {@code DynamicArray} by a specified size.
     *
     * @param required the required growth of the array.
     * @see DynamicArray#calculateMinimum(int)
     * @see DynamicArray#calculateNewSize(int)
     * @see DynamicArray#grow(int)
     */
    private void growByAtLeast(int required) {
        int minimum = calculateMinimum(required);
        grow(minimum);
    }

    /**
     * Add a value to the {@code DynamicArray} without checking if the value
     * can be added or if the array is large enough to fit the value.
     *
     * @param value the value that should be added to the array.
     */
    private void unsafeAdd(E value) {
        elements[activeSize] = value;
        activeSize++;
    }

    /**
     * Remove a value from the {@code DynamicArray} without performing any
     * checks to see if removing the value would work.
     */
    private void unsafeRemove() {
        activeSize--;
    }

    /**
     * Remove a value from the {@code DynamicArray} without performing any
     * background checks to ensure that the value can be removed.
     *
     * @param index the index of the element that should be removed.
     */
    private void unsafeRemoveAtIndex(int index) {
        System.arraycopy(
                elements,
                index + 1,
                elements,
                index,
                (activeSize - 1) - index
        );

        activeSize--;
    }

    /**
     * Remove all elements after a specified index.
     *
     * @param index the cutoff point - any element after this point will
     *              be removed from the {@code DynamicArray}'s array.
     */
    private void unsafeRemoveAfterIndex(int index) {
        int difference = activeSize - index;

        activeSize -= difference;
    }

    /**
     * Is the current array large enough to support a growth by a defined
     * increase in array size?
     *
     * @param increase the amount of growth required.
     * @return whether or not the current array is large enough.
     */
    private boolean isLargeEnough(int increase) {
        return elements.length >= activeSize + increase;
    }

    /**
     * Expand the {@code DynamicArray} instance based on a specified amount
     * to increase by.
     *
     * @param increase how much the size of the {@code DynamicArray} should
     *                 be increased by.
     */
    private void expandArray(int increase) {
        if (!isLargeEnough(increase)) {
            growByAtLeast(increase);
        }
    }

    /**
     * Add an array of elements to the {@code DynamicArray}'s internal list
     * of elements.
     *
     * <p>
     * These elements are, by default, appended to the end of the dynamic
     * array. If you'd like to control where the elements go, you can use...
     * {@link DynamicArray#add(int, Object)}
     * </p>
     *
     * @param values the values that should be appended to the
     *               {@code DynamicArray} instance's elements array.
     */
    public void add(E[] values) {
        expandArray(values.length);

        for (E value : values) {
            unsafeAdd(value);
        }
    }

    /**
     * Add a value to the end of the {@code DynamicArray}'s internal array
     * of elements. If the array isn't large enough to support the requested
     * operation, the array will be grown by a factor that allows the array
     * to support the requested operation.
     *
     * @param value the value that should be appended to the end of the
     *              {@code DynamicArray}'s element array.
     */
    public void add(E value) {
        expandArray(1);

        unsafeAdd(value);
    }

    /**
     * Add a value to the {@code DynamicArray}'s internal array of elements.
     * If the array isn't large enough to support the requested
     * operation, the array will be grown by a factor that allows the array
     * to support the requested operation.
     *
     * <p>
     * Unlike the {@link DynamicArray#add(Object)} method, this method gives
     * you control over the index of where the element is inserted. This method
     * will split the array in half and insert the value you requested at the
     * index you specified.
     * </p>
     *
     * @param index the target index for the specified value.
     * @param value the value to set to the index.
     */
    public void add(int index,
                    E value) {
        checkIndex(index);
        expandArray(1);

        System.arraycopy(
                elements,
                index,
                elements,
                index + 1,
                activeSize - index
        );

        elements[index] = value;

        activeSize++;
    }

    /**
     * Remove the last-added element from the dynamic array. This simply
     * removes the element that's at the array's end point.
     */
    public void remove() {
        if (activeSize - 1 > minSize) {
            unsafeRemove();
        }
    }

    /**
     * Remove a set of values based on an array of index targets. Each of the
     * values for each of the corresponding index targets will be removed from
     * the {@code DynamicArray}'s register of elements.
     *
     * <p>
     * This method is rather expensive, and, preferably, should be avoided if
     * at all possible. This isn't to say that it won't work, or that it's a
     * slow method - it's still blazing fast, but by comparison to the rest
     * of the {@code DynamicArray} methods, this one is a bit slower.
     * </p>
     *
     * @param indices all the index targets to be removed.
     */
    public void remove(int[] indices) {
        checkIndices(indices);

        for (int index : indices) {
            unsafeRemoveAtIndex(index);
        }
    }

    /**
     * Remove a value from a specified index in the {@code DynamicArray}'s
     * internal array of elements.
     *
     * @param index the index that should have its corresponding value
     *              removed from existence.
     */
    public void remove(int index) {
        checkIndex(index);

        unsafeRemoveAtIndex(index);
    }

    /**
     * Remove any elements after a specified cutoff index. Any element after
     * this point will be removed from the active array's data.
     *
     * @param cutoff the cutoff point. Any element after this point will be
     *               removed from the {@code DynamicArray}'s active area.
     */
    public void removeAfter(int cutoff) {
        checkIndex(cutoff);

        unsafeRemoveAfterIndex(cutoff);
    }

    /**
     * Set a value at a specific index.
     *
     * <p>
     * Unlike the {@link DynamicArray#add(Object)} methods provided in this
     * class, this method will not add anything to the array. Rather, it will
     * simply set a value.
     * </p>
     *
     * @param index the index that should be set.
     * @param value the value to set to the given index.
     */
    public void set(int index,
                    E value) {
        final int increase = index > activeSize ? index - activeSize : 0;
        expandArray(increase);

        elements[index] = value;
    }

    /**
     * Get the index of a specified element. This method searches the entire
     * active array and attempts to find the index of the first instance of
     * that object. If that object is stored in the array multiple times,
     * only the first index will be returned.
     *
     * <p>
     * This method uses the {@link DynamicArray#indexOfInRange(Object, int, int)}
     * method to determine the index of a given object within a certain range.
     * This method is essentially an overload - we use the default range of
     * the array, which is zero (array start) to the array's maximum.
     * </p>
     *
     * @param query the element to query. This element should be exactly
     *              the same as any of the elements that may be found. If this
     *              element isn't the same as any of the elements contained
     *              in the {@code DynamicArray}, we return -1.
     * @return if the queried element is contained within the predefined range
     * of (0) to (array max), return the index of the first instance of the
     * queried element. If the queried element is not contained within the
     * given range, return -1.
     */
    public int indexOf(E query) {
        return indexOfInRange(query, 0, activeSize);
    }

    /**
     * Get the index of a specified element, so long as it fits within that
     * range. If the element does not fit within that range, the element is
     * ignored, and thus, the index is ignored as well.
     *
     * @param query the element to query. This element should be the same
     *              element as the requested one. If it isn't, nothing will
     *              be found. If it is, the index will be returned.
     * @param min   the minimum index that should be searched. By default,
     *              this is zero.
     * @param max   the maximum index that should be searched. By default,
     *              this is the length of the {@code DynamicArray}.
     * @return if the queried element is contained in the range, return the
     * index of that queried element. If the queried element is not in the
     * range, however, return -1.
     */
    public int indexOfInRange(E query,
                              int min,
                              int max) {
        Object[] es = elements;
        if (query == null) {
            for (int i = min; i < max; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = min; i < max; i++) {
                if (query.equals(es[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Trim the currently-stored element array into a smaller element array
     * by reducing the size of the array to the active size of the array.
     *
     * <p>
     * Array elements aren't always wiped from the array whenever that
     * element is removed. In order to optimize performance, these values are
     * often left alone until they're no longer needed. However, if you would
     * like to cut down on memory usage, using this method will allow you to
     * reduce the memory footprint of the {@code DynamicArray} by trimming
     * any values that aren't actively being used by the array.
     * </p>
     */
    public void trim() {
        elements = Arrays.copyOf(elements, activeSize);
    }

    /**
     * Convert this instance of a {@code DynamicArray} into an instance of the
     * much more well-known {@code List}.
     *
     * @return a converted {@code DynamicArray} that has been transformed into
     * a list. Not an array list!
     * @see DynamicArray#toArray()
     * @see DynamicArray#toArrayList()
     */
    public List<E> toList() {
        return Arrays.asList(toArray());
    }

    /**
     * Convert this instance of a {@code DynamicArray} into an instance of
     * an {@link ArrayList}. This method calls the
     * {@link DynamicArray#toArray()} method in order to convert the internal
     * facing array into an external facing one, and then creates a new
     * {@code ArrayList} instance based on the converted array.
     *
     * @return a newly-created {@code ArrayList} instance, composed of the
     * same elements as the calling {@code DynamicArray}.
     * @see DynamicArray#toArray()
     * @see DynamicArray#toList()
     */
    public ArrayList<E> toArrayList() {
        return new ArrayList<E>(Arrays.asList(toArray()));
    }

    /**
     * Get the size of the active portion of the element array.
     *
     * @return the size of the active portion of the element array.
     */
    public int size() {
        return activeSize;
    }

    /**
     * Get the real size of the {@code DynamicArray}. The (very lovely)
     * {@link DynamicArray#size()} method will return the size of the active
     * portion of the array, while this method will return the size of the
     * entire element array.
     *
     * @return the size of the entire element array.
     */
    public int realSize() {
        return elements.length;
    }

    /**
     * Convert this instance of a {@code DynamicArray} into a plain array
     * instance by creating a clone of the elements contained in the calling
     * instance that is the same size as the active size.
     *
     * <p>
     * The internally-stored array is often larger than the external-facing
     * array for performance reasons - although it uses more memory, it takes
     * less CPU power to store un-used array values even when they aren't
     * accessible to the user. This means that the returned array here isn't
     * exactly the same as the array that's used inside of this class.
     * </p>
     *
     * @return the elements of the {@code DynamicArray}, in array form. This
     * is a new array, not the same array - you can't modify this array and
     * expect the values contained in the internally-used array to be updated,
     * as this method is intentionally immutable.
     * @see DynamicArray#toArrayList()
     * @see DynamicArray#toList()
     */
    @SuppressWarnings("unchecked")
    public E[] toArray() {
        return (E[]) Arrays.copyOf(elements, activeSize);
    }
}
