package me.wobblyyyy.edt;

/**
 * A map designed to link three different object types together, allowing
 * you to get a {@link Pair} (or {@link Trio}) with ease. Note that this
 * class is a bit harder to use than a regular map. In addition to being
 * harder to use, this map takes up more memory and requires more CPU power
 * to get and set items. But oh well!
 *
 * @param <A> the first of three types in the map.
 * @param <B> the second of three types in the map.
 * @param <C> the third of three types in the map.
 * @author Colin Robertson
 */
public class TrioMap<A, B, C> {
    private final DynamicArray<A> elementsA;
    private final DynamicArray<B> elementsB;
    private final DynamicArray<C> elementsC;

    public TrioMap() {
        this.elementsA = new DynamicArray<>(10);
        this.elementsB = new DynamicArray<>(10);
        this.elementsC = new DynamicArray<>(10);
    }

    public void add(A valueA,
                    B valueB,
                    C valueC) {
        elementsA.add(valueA);
        elementsB.add(valueB);
        elementsC.add(valueC);
    }

    public Pair<B, C> getWithA(A a) {
        int index = elementsA.indexOf(a);

        return new Pair<>(
                elementsB.get(index),
                elementsC.get(index)
        );
    }

    public Pair<A, C> getWithB(B b) {
        int index = elementsB.indexOf(b);

        return new Pair<>(
                elementsA.get(index),
                elementsC.get(index)
        );
    }

    public Pair<A, B> getWithC(C c) {
        int index = elementsC.indexOf(c);

        return new Pair<>(
                elementsA.get(index),
                elementsB.get(index)
        );
    }
}
