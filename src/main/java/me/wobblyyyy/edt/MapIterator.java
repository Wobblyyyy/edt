package me.wobblyyyy.edt;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Iterator class used in iterating over maps, such as the {@link FrozenMap},
 * the {@link DynamicMap}, and the {@link StaticMap}.
 *
 * @param <K> the object type for key values.
 * @param <V> the object type for value values.
 * @author Colin Robertson
 */
public class MapIterator<K, V> implements ItrPair<K, V> {
    /**
     * Internal reference to the {@code MappableSupplier} that provides this
     * iterator class with a {@link Mappable} object.
     */
    private final MappableSupplier<K, V> supplier;

    /**
     * Default exception consumer. Exceptions are passed to this consumer,
     * which will print the stack trace of the exception. Exceptions can
     * be ignored by the end-user by using try/catch blocks - we assume
     * that the user wants to see the exception here.
     */
    private final Consumer<Exception> exceptionConsumer =
            Throwable::printStackTrace;

    /**
     * The current index of the iterator. This is used internally (by
     * the iterator only) to get the current, previous, and next elements
     * and indexes.
     */
    private int index = 0;

    /**
     * Create a new {@code MapIterator} instance.
     *
     * @param supplier a supplier capable of supplying an instance of a
     *                 {@link Mappable}.
     */
    public MapIterator(MappableSupplier<K, V> supplier) {
        this.supplier = supplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V previous() {
        supplier.get().getValues().checkIndex(index - 1);

        return supplier.get().getValues().get(index - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V value() {
        return supplier.get().getValues().get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V next() {
        supplier.get().getValues().checkIndex(index + 1);

        return supplier.get().getValues().get(index + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K previousKey() {
        supplier.get().getValues().checkIndex(index - 1);

        return supplier.get().getKeys().get(index - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K key() {
        return supplier.get().getKeys().get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public K nextKey() {
        supplier.get().getKeys().checkIndex(index + 1);

        return supplier.get().getKeys().get(index + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int previousIndex() {
        supplier.get().getValues().checkIndex(index - 1);

        return index - 1;
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
        supplier.get().getValues().checkIndex(index + 1);

        return index + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(Consumer<V> consumer, int min, int max) {
        index = min;

        while (index <= max) {
            try {
                consumer.accept(value());
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
    public void forEach(Consumer<V> consumer) {
        forEach(consumer, 0, supplier.get().getValues().size() - 1);
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
        forEach(runnable, 0, supplier.get().getValues().size() - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(BiConsumer<K, V> keyValueConsumer,
                        int min,
                        int max) {
        index = min;

        while (index <= max) {
            try {
                keyValueConsumer.accept(
                        key(),
                        value()
                );
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
    public void forEach(BiConsumer<K, V> keyValueConsumer) {
        forEach(keyValueConsumer, 0, supplier.get().getValues().size() - 1);
    }
}
