package site.romvoid.httplib;

/**
 * @author ROMVoid
 */
public interface RequestResponse {

    /**
     * Returns the response code.
     * @return the response code that was returned by the request endpoint
     */
    int getResponseCode();

    /**
     * Returns the response message.
     * @return the message that was returned by the request endpoint
     */
    String getResponseMessage();

    /**
     * Returns the used endpoint url.
     * @return the endpoint url that was used by the request
     */
    String getEndpointUrl();
}
