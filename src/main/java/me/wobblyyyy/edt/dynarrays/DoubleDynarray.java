package me.wobblyyyy.edt.dynarrays;

import java.util.Arrays;

/**
 * A dynamic array for the primitive type "double." This dynamic array is
 * significantly faster than a comparable {@code ArrayList} component, only
 * if the dynamic array's minimum size is set effectively. Memory allocation
 * can take a long time on larger minimum size values, meaning large dynamic
 * arrays may take a while to get going. However, these run about 4x as fast
 * as a comparable {@code ArrayList} when optimized correctly.
 *
 * @author Colin Robertson
 */
public class DoubleDynarray {
    /**
     * The array's maximum size.
     */
    private int maxSize = -1;

    /**
     * The array's minimum size.
     */
    private int minSize = -1;

    /**
     * The size of the ACTIVE portion of the array.
     */
    private int size = 0;

    /**
     * The entire array.
     *
     * <p>
     * This array is often larger than the active portion of the array. This
     * is because the array overestimates the size of it's contents in order
     * to more effectively allow for quick set and add operations.
     * </p>
     */
    private double[] array;

    public DoubleDynarray() {
        this(new double[0]);
    }

    public DoubleDynarray(double[] contents) {
        array = contents;
        size = array.length;
    }

    public DoubleDynarray(double[] contents,
                          int maxSize) {
        array = contents;
        this.maxSize = maxSize;
        size = array.length;
    }

    public DoubleDynarray(double[] contents,
                          int minSize,
                          int maxSize) {
        array = contents;
        this.minSize = minSize;
        this.maxSize = maxSize;
        size = array.length;
    }

    public DoubleDynarray(int minSize,
                          int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        array = new double[minSize];
    }

    public DoubleDynarray(int maxSize) {
        this.maxSize = maxSize;
    }

    private void grow() {
        if (array.length < minSize) {
            array = Arrays.copyOf(array, minSize);
        } else {
            array = Arrays.copyOf(
                    array,
                    (int) Math.ceil(
                            array.length * 1.5
                    )
            );
        }
    }

    private boolean isLargeEnough(int increase) {
        return array.length >= size + increase;
    }

    public void add(double[] values) {
        while (!isLargeEnough(values.length)) {
            grow();
        }

        for (double v : values) {
            unsafeAdd(v);
        }
    }

    private void unsafeAdd(double value) {
        array[size] = value;
        size++;
    }

    public void add(double value) {
        if (!isLargeEnough(1)) {
            grow();
        }

        array[size] = value;
        size++;
    }

    public void remove() {
        array = Arrays.copyOf(
                array,
                array.length - 1
        );
    }

    public void remove(int index) {
        double[] newArray = new double[array.length - 1];

        for (int i = 0; i < array.length - 1; i++) {
            if (i < index - 1) {
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i + 1];
            }
        }

        array = newArray;
    }

    /**
     * Set an element of the array based on a specified index. Unlike the
     * add method, this method sets a value at a given index. If the index
     * is out of the array's active bounds, we throw an exception, indicating
     * that the index is out of bounds.
     *
     * @param index the index of the value to set.
     * @param value the value to set to the specified index.
     */
    public void set(int index,
                    double value) {
        if (index > size) throw new ArrayIndexOutOfBoundsException(
                "Index " + index + " out of bounds for the requested " +
                        "dynamic double array!"
        );
        array[index] = value;
    }

    /**
     * Get an element of the array based on the index you'd like to query.
     *
     * <p>
     * If the queried index is not available, or it isn't in the active
     * component of the array, we throw an ArrayOutOfBounds exception.
     * </p>
     *
     * @param index the index to query.
     * @return a value based on the queried index. If the queried index isn't
     * a part of the active array, we throw an exception, indicating that the
     * queried element is considered to be out of bounds.
     */
    public double get(int index) {
        if (index > size) throw new ArrayIndexOutOfBoundsException(
                "Index " + index + " out of bounds for the queried " +
                        "dynamic double array!"
        );
        return array[index];
    }

    /**
     * Get the current active array.
     *
     * <p>
     * This array is different than the internally stored array. The internally
     * stored array is often larger than the active portion of the array, in
     * an attempt to optimize adding operations. This method, instead of
     * returning the entire array, will only return the active portion of it.
     * </p>
     *
     * @return the internally-stored array.
     */
    public double[] getArray() {
        return Arrays.copyOf(array, size);
    }
}
