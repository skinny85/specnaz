package org.specnaz;

import org.specnaz.utils.TestClosure;

public interface SpecBuilder {
    void beginsAll(TestClosure closure);

    void beginsEach(TestClosure closure);

    void should(String description, TestClosure testBody);

    void endsEach(TestClosure closure);

    void endsAll(TestClosure closure);

    void describes(String description, Runnable specClosure);
}
