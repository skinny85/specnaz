package spring;

/**
 * A dependency of {@link ServiceA}.
 *
 * @see #getA
 */
public interface DaoA {
    /**
     * Used in {@link ServiceA#findA}.
     *
     * @return String
     */
    String getA();
}
