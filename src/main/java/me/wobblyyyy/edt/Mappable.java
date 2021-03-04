package me.wobblyyyy.edt;

/**
 * Interface shared between all map implementations, such as the (sexy)
 * {@code DynamicMap} and the (equally sexy) {@code StaticMap}.
 *
 * @param <K> the type of key element in the map.
 * @param <V> the type of value element in the map.
 */
public interface Mappable<K, V> {
    /**
     * Put a given pair into the map. If the map already contains an element
     * with the same key as the key that was inputted, replace that key's
     * value. If the map doesn't contain the given key and the map can be
     * expanded to include it, insert the value into the map.
     *
     * @param key   the key that the value should go under.
     * @param value the value the key should have.
     * @see Mappable#put(KeyValue)
     */
    void put(K key, V value);

    /**
     * Put a given pair into the map. If the map already contains an element
     * with the same key as the key that was inputted, replace that key's
     * value. If the map doesn't contain the given key and the map can be
     * expanded to include it, insert the value into the map.
     *
     * @param keyValuePair a pair of a key and a value that should be inserted
     *                     into the map. This pair contains both a key and a
     *                     value.
     * @see Mappable#put(Object, Object)
     */
    void put(KeyValue<K, V> keyValuePair);

    /**
     * Get the value that a specified key in the map contains.
     *
     * @param key the key to query for.
     * @return if the map contains the requested key, return the key's value.
     * If the map doesn't contain the requested key, however, return null,
     * indicating that the requested key could not be found.
     */
    V get(K key);

    /**
     * Get the key associated with a specified value.
     *
     * @param value the value to search for.
     * @return the first instance of a key that matches with the given value.
     * If there are no instances of the value in the map, return null. If there
     * are multiple instances of the requested key in the map, only return the
     * key of the first of those indexes.
     */
    K getKey(V value);

    /**
     * Check to see if the map contains a specified key.
     *
     * @param key the key to look for.
     * @return true if the map contains the requested key. Return false if the
     * map doesn't contain the requested key.
     */
    boolean containsKey(K key);

    /**
     * Check to see if the map contains a specified value.
     *
     * @param value the value to look for.
     * @return true if the map contains at least one instance of the specified
     * value, and false if it doesn't.
     */
    boolean containsValue(V value);

    /**
     * Get an {@code Arrayable} element based on all of the keys contained in
     * the map's data set.
     *
     * @return an {@code Arrayable} element composed of all of the map's keys.
     */
    Arrayable<K> getKeys();

    /**
     * Get an {@code Arrayable} element based on all of the values contained in
     * the map's data set.
     *
     * @return an {@code Arrayable} element composed of all of the map's values.
     */
    Arrayable<V> getValues();

    /**
     * Get the size of the map.
     *
     * <p>
     * This attribute is based on the quantity of entries contained in the map.
     * Lone keys and lone values (if those exist) are not counted towards the
     * total size of the map. In the case of map implementations that use
     * dynamic arrays, size should still represent the total ACTIVE size, not
     * the total allocated size.
     * </p>
     *
     * @return the map's size.
     */
    int size();

    /**
     * Clear all of the values from a map.
     *
     * <p>
     * Clearing a map will <strong>not</strong> reset the entire map to a new
     * map. Rather, it will clear each of the values from the map. Each of the
     * key elements should remain entirely intact. The value data structure
     * should not be de-allocated - rather, it should remain allocated and
     * ready to receive new information.
     * </p>
     *
     * <p>
     * If you're interested in "starting fresh," so to speak, you can create a
     * new instance of the map you're using. Otherwise, this lovely clear
     * method should get the job done fairly well.
     * </p>
     */
    void clear();

    /**
     * Get the map's iterator.
     *
     * @return the map's iterator. The specific implementation of the iterator
     * is left entirely up to the implementing class, but each iterator is an
     * implementation of the {@link ItrPair} interface, so they function near
     * identically in practice.
     */
    ItrPair<K, V> itr();
}
