package org.specnaz.params;

/**
 * A simple tuple class holding 3 values.
 * You use it to provide parameters to the {@link ParamsExpected3}
 * and {@link ParamsExpectedException3} classes.
 */
public final class Params3<P1, P2, P3> {
    /**
     * Factory method.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @return a new instance of the {@link Params3} class
     */
    public static <P1, P2, P3> Params3<P1, P2, P3> p(P1 param1, P2 param2, P3 param3) {
        return new Params3<>(param1, param2, param3);
    }

    public final P1 _1;
    public final P2 _2;
    public final P3 _3;

    private Params3(P1 param1, P2 param2, P3 param3) {
        _1 = param1;
        _2 = param2;
        _3 = param3;
    }
}
