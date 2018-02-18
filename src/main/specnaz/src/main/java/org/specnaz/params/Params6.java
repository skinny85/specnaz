package org.specnaz.params;

/**
 * A simple tuple class holding 6 values.
 * You use it to provide parameters to the {@link ParamsExpected6}
 * and {@link ParamsExpectedException6} classes.
 */
public final class Params6<P1, P2, P3, P4, P5, P6> {
    /**
     * Factory method.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     * @param param5 fifth parameter
     * @param param6 sixth parameter
     * @return a new instance of the {@link Params6} class
     */
    public static <P1, P2, P3, P4, P5, P6> Params6<P1, P2, P3, P4, P5, P6> p6(P1 param1, P2 param2, P3 param3, P4 param4,
            P5 param5, P6 param6) {
        return new Params6<>(param1, param2, param3, param4, param5, param6);
    }

    public final P1 _1;
    public final P2 _2;
    public final P3 _3;
    public final P4 _4;
    public final P5 _5;
    public final P6 _6;

    private Params6(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6) {
        _1 = param1;
        _2 = param2;
        _3 = param3;
        _4 = param4;
        _5 = param5;
        _6 = param6;
    }
}
