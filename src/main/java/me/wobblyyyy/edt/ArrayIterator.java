package me.wobblyyyy.edt;

import java.util.function.Consumer;

/**
 * Iterator used and shared by all sorts of arrays.
 *
 * {@inheritDoc}
 *
 * @param <E> the type of content that the iterator holds.
 * @author Colin Robertson
 */
public class ArrayIterator<E> implements ItrSingle<E> {
    /**
     * Provide a method of accessing the original arrayable element.
     */
    private final ArrayableSupplier<E> supplier;

    /**
     * Provide a consumer for exceptions. We don't want to just throw them
     * into the void, so we do something about it here. Yay!
     */
    private final Consumer<Exception> exceptionConsumer =
            Throwable::printStackTrace;

    /**
     * An internal index counter. This is reset on every iteration sequence.
     */
    private int index = 0;

    /**
     * Create a new {@code ArrayIterator} instance, using a supplier to supply
     * ourselves with an {@link Arrayable} instance.
     *
     * @param supplier a supplier for the target {@code Arrayable}.
     */
    public ArrayIterator(ArrayableSupplier<E> supplier) {
        this.supplier = supplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E previous() {
        if (supplier.get().checkIndex(index - 1)) {
            return supplier.get().get(index - 1);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E element() {
        if (supplier.get().checkIndex(index)) {
            return supplier.get().get(index);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
        if (supplier.get().checkIndex(index + 1)) {
            return supplier.get().get(index + 1);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int previousIndex() {
        if (supplier.get().checkIndex(index - 1)) {
            return index - 1;
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int index() {
        return index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextIndex() {
        if (supplier.get().checkIndex(index + 1)) {
            return index + 1;
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<E> consumer, int min, int max) {
        index = min;

        while (index <= max) {
            try {
                consumer.accept(element());
            } catch (Exception e) {
                exceptionConsumer.accept(e);
            }

            index += 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<E> consumer) {
        forEach(consumer, 0, supplier.get().size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Runnable runnable, int min, int max) {
        index = min;

        while (index <= max) {
            try {
                runnable.run();
            } catch (Exception e) {
                exceptionConsumer.accept(e);
            }

            index += 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Runnable runnable) {
        forEach(runnable, 0, supplier.get().size() - 1);
    }
}
