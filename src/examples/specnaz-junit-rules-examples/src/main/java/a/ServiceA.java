package a;

/**
 * An example 'service'.
 * It has one 'business' method
 * ({@link #findA}) and one dependency
 * (on the {@link DaoA} interface).
 *
 * @see #findA
 */
public final class ServiceA {
    private final DaoA daoA;

    public ServiceA(DaoA daoA) {
        this.daoA = daoA;
    }

    /**
     * The 'business' method of {@link ServiceA}.
     *
     * @return the String {@code "ServiceA:"}
     *   concatenated with whatever {@link DaoA#getA} returns
     */
    public String findA() {
        return "ServiceA:" + daoA.getA();
    }
}
