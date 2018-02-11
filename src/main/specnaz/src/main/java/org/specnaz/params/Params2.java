package org.specnaz.params;

public final class Params2<P1, P2> {
    public static <P1, P2> Params2<P1, P2> p2(P1 p1, P2 p2) {
        return new Params2<>(p1, p2);
    }

    public final P1 _1;
    public final P2 _2;

    private Params2(P1 p1, P2 p2) {
        _1 = p1;
        _2 = p2;
    }
}
