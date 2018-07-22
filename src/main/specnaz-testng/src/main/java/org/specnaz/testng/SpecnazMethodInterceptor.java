package org.specnaz.testng;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

import java.util.List;

public final class SpecnazMethodInterceptor implements IMethodInterceptor {
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods,
            ITestContext context) {
        methods.sort((mi1, mi2) -> {
            if (!(mi1.getInstance() instanceof SpecnazTests) ||
                    !(mi2.getInstance() instanceof SpecnazTests))
                return 0;
            SpecnazTests st1 = (SpecnazTests) mi1.getInstance();
            SpecnazTests st2 = (SpecnazTests) mi2.getInstance();
            return st1.getSignature().compareTo(st2.getSignature());
        });
        return methods;
    }
}
