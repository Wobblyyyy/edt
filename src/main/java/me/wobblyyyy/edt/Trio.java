package me.wobblyyyy.edt;

public class Trio<A, B, C> extends Pair<A, B> {
    private final C c;

    public Trio(A a, B b, C c) {
        super(a, b);

        this.c = c;
    }

    public C getC() {
        return c;
    }
}
