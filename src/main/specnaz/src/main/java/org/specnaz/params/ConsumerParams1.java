package org.specnaz.params;

@FunctionalInterface
public interface ConsumerParams1<P> {
    void invoke(P p);
}
