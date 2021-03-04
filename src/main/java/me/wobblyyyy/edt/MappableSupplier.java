package me.wobblyyyy.edt;

import java.util.function.Supplier;

/**
 * Interface used in supplying {@link Mappable} instances.
 *
 * @param <K> the object type for keys.
 * @param <V> the object type for values.
 * @author Colin Robertson
 */
public interface MappableSupplier<K, V> extends Supplier<Mappable<K, V>> {
}
