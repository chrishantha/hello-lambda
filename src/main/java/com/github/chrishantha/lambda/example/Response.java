package com.github.chrishantha.lambda.example;

import java.io.Serializable;
import java.util.Map;

/**
 * Response POJO class
 */
public class Response implements Serializable {

    /**
     * Response headers
     */
    private Map<String, String> headers;

    /**
     * Response body
     */
    private String body;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response{" +
                "headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}
