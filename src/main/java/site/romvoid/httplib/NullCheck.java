package site.romvoid.httplib;

/**
 * @author ROMVoid
 */
public class NullCheck {

    /**
     * Checks if a object is null or not
     * @param object the object you want to check
     * @return true if the object is null - false if the object is not null
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * Checks if a object is null. If the object is null it throws a {@link NullPointerException} with a predefined text.
     * @param object the object you want to check
     */
    public static void isNullException(Object object) {
        if (isNull(object))
            throw new NullPointerException("Object cannot be null.");
    }
}
