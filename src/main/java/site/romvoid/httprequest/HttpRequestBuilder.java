package site.romvoid.httprequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import site.romvoid.httprequest.impl.RequestResponseImpl;

/**
 * @asuthor ROMVoid
 */
public class HttpRequestBuilder {

    private final RequestType requestType;
    private final String endpointUrl;
    private final ArrayList<RequestField> parameterList;
    private RequestHeader requestHeader;

    /**
     * Initialises the {@link HttpRequestBuilder}.
     * The requestURI must start either with a http:// or an https://
     * Without these it doesn't work and you will get an error.
     * @param endpointUrl url where the request should be accepted. (request endpoint)
     * @param requestType {@link RequestType} specify the request type whether it is a post or a get request
     */
    public HttpRequestBuilder(String endpointUrl, RequestType requestType) {
        this.requestType = requestType;
        this.endpointUrl = endpointUrl;
        NullCheck.isNullException(requestType);
        NullCheck.isNullException(endpointUrl);

        parameterList = new ArrayList<>();
    }

    /**
     * You can use the {@link RequestHeader} to add an Authorization or an custom User-Agent to your request.
     * @param requestHeader a {@link RequestHeader} where you defined the keys and the values
     * @return {@link HttpRequestBuilder} with data you already set
     */
    public HttpRequestBuilder setRequestHeader(RequestHeader requestHeader) {
        NullCheck.isNullException(requestHeader);
        this.requestHeader = requestHeader;
        return this;
    }

    /**
     * Use {@link HttpRequestBuilder#addParameter(String, String)}
     * @param requestField already defined {@link RequestField}
     * @return {@link HttpRequestBuilder} with data you already set
     */
    public HttpRequestBuilder addParameter(RequestField requestField) {
        NullCheck.isNullException(requestField);
        parameterList.add(requestField);
        return this;
    }

    /**
     * This method adds a parameter to your request.
     * That's the main function to transfer data to the request endpoint.
     * @param key parameter key
     * @param value parameter value
     * @return {@link HttpRequestBuilder} with data you already set
     */
    public HttpRequestBuilder addParameter(String key, String value) {
        //No null check because its already in the RequestField constructor
        parameterList.add(new RequestField(key, value));
        return this;
    }

    /**
     * This method is used to execute the request.
     * @return a {@link RequestResponse} which you can use to evaluate the response
     * @throws IOException will be thrown if a error occurs
     */
    public RequestResponse sendRequest() throws IOException {
        String sRequestUrl = requestType == RequestType.GET ? endpointUrl + "?" + constructParameters() : endpointUrl;
        URL requestUrl = new URL(sRequestUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) requestUrl.openConnection();
        httpURLConnection.setRequestMethod(requestType == RequestType.GET ? "GET" : "POST");
        appendHeader(httpURLConnection);

        if (requestType == RequestType.POST) {
            httpURLConnection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.writeBytes(constructParameters());
            dataOutputStream.flush();
            dataOutputStream.close();
        }

        int responseCode = httpURLConnection.getResponseCode();

        BufferedReader connectionInput = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = connectionInput.readLine()) != null)
            responseBuilder.append(line);
        connectionInput.close();
        return (RequestResponse) new RequestResponseImpl(responseCode, responseBuilder.toString(), endpointUrl);
    }

    private String constructParameters() throws IOException {
        if (parameterList.size() == 0)
            return "";

        StringBuilder parameterBuilder = new StringBuilder();

        for (RequestField requestParameter : parameterList) {
            if (!parameterBuilder.toString().equals(""))
                parameterBuilder.append("&");
            parameterBuilder.append(URLEncoder.encode(requestParameter.getKey(), StandardCharsets.UTF_8.toString()))
                    .append("=")
                    .append(URLEncoder.encode(requestParameter.getValue(), StandardCharsets.UTF_8.toString()));
        }
        return parameterBuilder.toString();
    }

    private void appendHeader(HttpURLConnection httpURLConnection) {
        if (requestHeader != null) {
            for (RequestField requestHeaderField : requestHeader.getRequestFields()) {
                httpURLConnection.setRequestProperty(requestHeaderField.getKey(), requestHeaderField.getValue());
            }
        }
    }
}
