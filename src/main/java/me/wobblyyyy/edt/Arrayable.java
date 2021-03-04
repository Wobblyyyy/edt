package me.wobblyyyy.edt;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface to be shared by all implementations of the core array concept
 * that much of edt is based on.
 *
 * @param <E> the type of element that's stored in the array.
 */
public interface Arrayable<E> {
    void set(int index, E value);
    E get(int index);
    int size();
    int indexOf(E value);
    boolean isEmpty();
    boolean contains(E value);
    List<Object> toList();
    ArrayList<Object> toArrayList();
    Object[] toArray();
    E[] toArray(E[] base);
    Double[] toDoubleArray();
    Integer[] toIntegerArray();
    ItrSingle<E> itr();
}
