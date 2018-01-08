public class ServiceA {
    private final DaoA daoA;

    public ServiceA(DaoA daoA) {
        this.daoA = daoA;
    }

    public String findA() {
        return "ServiceA:" + daoA.getA();
    }
}
