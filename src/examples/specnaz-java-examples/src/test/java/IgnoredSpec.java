import org.specnaz.junit.SpecnazJUnit;

import static org.assertj.core.api.Assertions.fail;

/**
 * This example shows how to ignore tests in Specnaz.
 */
public class IgnoredSpec extends SpecnazJUnit {{
    describes("A spec with ignored tests", it -> {
        it.xshould("not run xshould tests", () -> {
            fail("this should not have been executed");
        });

        it.xshouldThrow(Exception.class, "when being ignored", () -> {
        });

        it.xdescribes("can also ignore an entire sub-spec with xdescribes", () -> {
            it.should("not run in an ignored sub-spec even when created with should", () -> {
                fail("this should not have been executed");
            });

            it.describes("with a describes inside an xdescribes", () -> {
                it.should("not run a test defined with 'should'", () -> {
                    fail("this should not have been executed");
                });
            });
        });
    });
}}
