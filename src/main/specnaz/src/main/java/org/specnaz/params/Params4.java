package org.specnaz.params;

/**
 * A simple tuple class holding 4 values.
 * You use it to provide parameters to the {@link ParamsExpected4}
 * and {@link ParamsExpectedException4} classes.
 */
public final class Params4<P1, P2, P3, P4> {
    /**
     * Factory method.
     *
     * @param param1 first parameter
     * @param param2 second parameter
     * @param param3 third parameter
     * @param param4 fourth parameter
     * @return a new instance of the {@link Params4} class
     */
    public static <P1, P2, P3, P4> Params4<P1, P2, P3, P4> p(P1 param1, P2 param2, P3 param3, P4 param4) {
        return new Params4<>(param1, param2, param3, param4);
    }

    public final P1 _1;
    public final P2 _2;
    public final P3 _3;
    public final P4 _4;

    private Params4(P1 param1, P2 param2, P3 param3, P4 param4) {
        _1 = param1;
        _2 = param2;
        _3 = param3;
        _4 = param4;
    }
}
