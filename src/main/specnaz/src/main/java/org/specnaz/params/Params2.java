package org.specnaz.params;

/**
 * A simple tuple class holding 2 values.
 * You use it to provide parameters to the {@link ParamsExpected2}
 * and {@link ParamsExpectedException2} classes.
 */
public final class Params2<P1, P2> {
    /**
     * Factory method.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @return a new instance of the {@link Params2} class
     */
    public static <P1, P2> Params2<P1, P2> p2(P1 param1, P2 param2) {
        return new Params2<>(param1, param2);
    }

    public final P1 _1;
    public final P2 _2;

    private Params2(P1 param1, P2 param2) {
        _1 = param1;
        _2 = param2;
    }
}
