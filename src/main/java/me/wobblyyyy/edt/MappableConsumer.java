package me.wobblyyyy.edt;

import java.util.function.Consumer;

/**
 * Interface used in consuming {@link Mappable} instances.
 *
 * @param <K> the object type for keys.
 * @param <V> the object type for values.
 * @author Colin Robertson
 */
public interface MappableConsumer<K, V> extends Consumer<Mappable<K, V>> {
}
