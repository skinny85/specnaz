package spring;

import a.DaoA;

/**
 * A test implementation of {@link DaoA}.
 *
 * @see #getA
 */
public final class TestDaoA implements DaoA {
    /**
     * @return {@code "TestDaoA"}
     */
    @Override
    public String getA() {
        return "TestDaoA";
    }
}
