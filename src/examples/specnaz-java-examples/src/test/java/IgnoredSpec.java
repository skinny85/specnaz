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

        it.xdescribes("and an ignored sub-spec with xdescribes", () -> {
            it.beginsAll(() -> {
                fail("beginsAll");
            });

            it.beginsEach(() -> {
                fail("beginsEach");
            });

            it.endsEach(() -> {
                fail("endsEach");
            });

            it.endsAll(() -> {
                fail("endsAll");
            });

            it.should("not run the begins/ends fixtures", () -> {
                fail("xdescribes fixtures test");
            });
        });

        it.describes("and a sub-spec with only ignored tests", () -> {
            it.beginsAll(() -> {
                fail("beginsAll");
            });

            it.beginsEach(() -> {
                fail("beginsEach");
            });

            it.endsEach(() -> {
                fail("endsEach");
            });

            it.endsAll(() -> {
                fail("endsAll");
            });

            it.xshould("not run the begins/ends fixtures", () -> {
                fail("describes->xshould fixtures test");
            });
        });
    });
}}
