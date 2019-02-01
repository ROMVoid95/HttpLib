package site.romvoid.httplib;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ROMVoid
 */
public class RequestHeader {

    private List<RequestField> fields;

    public RequestHeader() {
        fields = new ArrayList<>();
    }

    /**
     * Adds a field to the Header
     * @param key field key
     * @param value field value
     */
    public RequestHeader addField(String key, String value) {
        if (key != null || value != null) {
            fields.add(new RequestField(key, value));
        } else {
            throw new NullPointerException("Key or value can not be null.");
        }
        return this;
    }

    /**
     * Adds a already defined {@link RequestField} to the header
     * @param field {@link RequestField}
     * @return a {@link RequestHeader} with data you already set
     */
    public RequestHeader addField(RequestField field) {
        if (field != null) {
            fields.add(field);
        } else {
            throw new NullPointerException("Key or value can not be null.");
        }
        return this;
    }

    /**
     *
     * @return all fields that are added
     */
    public List<RequestField> getRequestFields() {
        return fields;
    }
}

