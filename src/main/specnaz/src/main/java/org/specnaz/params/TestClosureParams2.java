package org.specnaz.params;

@FunctionalInterface
public interface TestClosureParams2<P1, P2> {
    void invoke(P1 p1, P2 p2) throws Exception;
}
