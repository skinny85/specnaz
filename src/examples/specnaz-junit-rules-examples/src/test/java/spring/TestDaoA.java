package spring;

import a.DaoA;

public class TestDaoA implements DaoA {
    @Override
    public String getA() {
        return "TestDaoA";
    }
}
