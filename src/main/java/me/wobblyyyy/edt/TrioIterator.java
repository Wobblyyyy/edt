package me.wobblyyyy.edt;

import java.util.function.Consumer;

public class TrioIterator<A, B, C> implements ItrTrio<A, B, C> {
    private int index;
    private static final Consumer<Exception> exceptionConsumer =
            Throwable::printStackTrace;
    private final TrioMapSupplier<A, B, C> supplier;

    public TrioIterator(TrioMapSupplier<A, B, C> supplier) {
        this.supplier = supplier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trio<A, B, C> previousElement() {
        return supplier.get().getByIndex(index - 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A previousA() {
        return supplier.get().getByIndex(index - 1).getA();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B previousB() {
        return supplier.get().getByIndex(index - 1).getB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C previousC() {
        return supplier.get().getByIndex(index - 1).getC();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trio<A, B, C> element() {
        return supplier.get().getByIndex(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A a() {
        return supplier.get().getByIndex(index).getA();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B b() {
        return supplier.get().getByIndex(index).getB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C c() {
        return supplier.get().getByIndex(index).getC();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Trio<A, B, C> nextElement() {
        return supplier.get().getByIndex(index + 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public A nextA() {
        return supplier.get().getByIndex(index + 1).getA();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public B nextB() {
        return supplier.get().getByIndex(index + 1).getB();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public C nextC() {
        return supplier.get().getByIndex(index + 1).getC();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int previousIndex() {
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
        return index + 1;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEach(TriConsumer<A, B, C> consumer, int min, int max) {
        index = min;

        while (index <= min) {
            try {
                consumer.accept(
                       a(),
                       b(),
                       c()
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
    public void forEach(TriConsumer<A, B, C> consumer) {
        forEach(consumer, 0, supplier.get().size() - 1);
    }
}
