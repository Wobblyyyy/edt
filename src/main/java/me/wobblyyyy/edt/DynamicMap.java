package me.wobblyyyy.edt;

import java.util.ArrayList;

/**
 * A re-implementation of the map concept, designed to leverage a pair of
 * linked {@link DynamicArray} instances to improve performance.
 *
 * @param <K> the type used for key elements.
 * @param <V> the type used for value elements.
 * @author Colin Robertson
 */
public class DynamicMap<K, V> {
    private DynamicArray<K> keys;
    private DynamicArray<V> values;

    public DynamicMap() {
        keys = new DynamicArray<K>(10, new Object[0]);
        values = new DynamicArray<V>(10, new Object[0]);
    }

    public void add(K key,
                    V value) {
        keys.add(key);
        values.add(value);
    }

    public void add(int index,
                    K key,
                    V value) {
        keys.add(index, key);
        values.add(index, value);
    }

    public void add(KeyValue<K, V> keyValuePair) {
        add(keyValuePair.getKey(), keyValuePair.getValue());
    }

    public void add(int index, KeyValue<K, V> keyValuePair) {
        add(index, keyValuePair.getKey(), keyValuePair.getValue());
    }

    public void remove(int index) {
        keys.remove(index);
        values.remove(index);
    }

    public void remove(K key) {
        int index = indexOfKey(key);
        remove(index);
    }

    public void removeByValue(V value) {
        int index = indexOfValue(value);
        keys.remove(index);
        values.remove(index);
    }

    public void remove(K key, V value) {
        int indexKey = indexOfKey(key);
        int indexValue = indexOfValue(value);

        if (indexKey == indexValue) remove(key);
    }

    public void remove(KeyValue<K, V> keyValuePair) {
        remove(keyValuePair.getKey(), keyValuePair.getValue());
    }

    public V get(K key) {
        return values.get(keys.indexOf(key));
    }

    public K getKey(V value) {
        return keys.get(values.indexOf(value));
    }

    public int indexOfKey(K key) {
        return keys.indexOf(key);
    }

    public int indexOfValue(V value) {
        return values.indexOf(value);
    }

    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    public boolean containsValue(V value) {
        return values.contains(value);
    }

    public DynamicArray<K> getKeys() {
        return keys;
    }

    public DynamicArray<V> getValues() {
        return values;
    }

    public K[] getKeyArray() {
        return keys.toArray();
    }

    public V[] getValueArray() {
        return values.toArray();
    }

    public ArrayList<K> getKeyArrayList() {
        return keys.toArrayList();
    }

    public ArrayList<V> getValueArrayList() {
        return values.toArrayList();
    }

    public int size() {
        return keys.size();
    }

    public void trim() {
        keys.trim();
        values.trim();
    }

    public void clear() {
        keys.clear();
        values.clear();
    }

    public void reset() {
        keys.reset();
        values.reset();
    }
}
