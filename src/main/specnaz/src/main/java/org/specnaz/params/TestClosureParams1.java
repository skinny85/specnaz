package org.specnaz.params;

@FunctionalInterface
public interface TestClosureParams1<T> {
    void invoke(T t) throws Exception;
}
