package me.wobblyyyy.edt;

import java.util.function.Supplier;

/**
 * Interface used in supplying an {@link Arrayable} instance.
 *
 * @param <E> the type of data stored in the {@code Arrayable} element.
 * @author Colin Robertson
 */
public interface ArrayableSupplier<E> extends Supplier<Arrayable<E>> {
}
