package site.romvoid.httplib.impl;

import site.romvoid.httplib.RequestResponse;

/*
 * @author ROMVoid
 */
public class RequestResponseImpl implements RequestResponse {

    private final int responseCode;
    private final String responseMessage;
    private final String endpointUrl;

    public RequestResponseImpl(int responseCode, String responseMessage, String endpointUrl) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.endpointUrl = endpointUrl;
    }

    @Override
    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getResponseMessage() {
        return responseMessage;
    }

    @Override
    public String getEndpointUrl() {
        return endpointUrl;
    }
}
