package me.wobblyyyy.edt;

import java.util.ArrayList;
import java.util.List;

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
}
