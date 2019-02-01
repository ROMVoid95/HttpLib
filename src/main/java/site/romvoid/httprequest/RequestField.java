package site.romvoid.httprequest;

/**
 * @author ROMVoid
 */
public class RequestField {

    private final String key;
    private final String value;

    /**
     * A holder for a field key and a field value
     * @param key String that you want to use as key
     * @param value String that you want to use as value
     */
    public RequestField(String key, String value) {
        this.key = key;
        this.value = value;
        NullCheck.isNullException(key);
        NullCheck.isNullException(value);
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
