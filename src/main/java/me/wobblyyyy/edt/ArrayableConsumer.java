package me.wobblyyyy.edt;

import java.util.function.Consumer;

/**
 * Interface used in consuming an {@link Arrayable} instance.
 *
 * @param <E> the type of data stored in the {@code Arrayable} element.
 * @author Colin Robertson
 */
public interface ArrayableConsumer<E> extends Consumer<Arrayable<E>> {
}
