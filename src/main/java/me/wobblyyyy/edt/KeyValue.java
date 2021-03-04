package me.wobblyyyy.edt;

/**
 * A key-value pair, used mostly in creating and modifying map instances.
 *
 * @param <K> the pair's key type.
 * @param <V> the pair's value type.
 * @author Colin Robertson
 */
public class KeyValue<K, V> {
    /**
     * The pair's key.
     */
    private final K key;

    /**
     * The pair's value.
     */
    private final V value;

    /**
     * Create a new key/value pair.
     *
     * @param key   the pair's key.
     * @param value the pair's value.
     */
    public KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the pair's key.
     *
     * @return the pair's key.
     */
    public K getKey() {
        return key;
    }

    /**
     * Get the pair's value.
     *
     * @return the pair's value.
     */
    public V getValue() {
        return value;
    }
}
