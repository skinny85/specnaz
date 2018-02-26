package org.specnaz.params;

/**
 * A simple tuple class holding 8 values.
 * You use it to provide parameters to the {@link ParamsExpected8}
 * and {@link ParamsExpectedException8} classes.
 */
public final class Params8<P1, P2, P3, P4, P5, P6, P7, P8> {
    /**
     * Factory method.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     * @param param5 fifth parameter
     * @param param6 sixth parameter
     * @param param7 seventh parameter
     * @param param8 eight parameter
     * @return a new instance of the {@link Params8} class
     */
    public static <P1, P2, P3, P4, P5, P6, P7, P8> Params8<P1, P2, P3, P4, P5, P6, P7, P8> p8(P1 param1, P2 param2,
            P3 param3, P4 param4, P5 param5, P6 param6, P7 param7, P8 param8) {
        return new Params8<>(param1, param2, param3, param4, param5, param6, param7, param8);
    }

    public final P1 _1;
    public final P2 _2;
    public final P3 _3;
    public final P4 _4;
    public final P5 _5;
    public final P6 _6;
    public final P7 _7;
    public final P8 _8;

    private Params8(P1 param1, P2 param2, P3 param3, P4 param4, P5 param5, P6 param6, P7 param7, P8 param8) {
        _1 = param1;
        _2 = param2;
        _3 = param3;
        _4 = param4;
        _5 = param5;
        _6 = param6;
        _7 = param7;
        _8 = param8;
    }
}
