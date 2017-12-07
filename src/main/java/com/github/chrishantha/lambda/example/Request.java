package com.github.chrishantha.lambda.example;

import java.io.Serializable;
import java.util.Map;

/**
 * Request POJO class
 */
public class Request implements Serializable {

    public enum HttpMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;
    }

    /**
     * Resource path
     */
    private String resource;
    /**
     * Path parameter
     */
    private String path;
    /**
     * Incoming request's method name
     */
    private HttpMethod httpMethod;
    /**
     * Request headers
     */
    private Map<String, String> headers;
    /**
     * Query string parameters
     */
    private Map<String, String> queryStringParameters;
    /**
     * Path parameters
     */
    private Map<String, String> pathParameters;
    /**
     * The request payload
     */
    private String body;

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryStringParameters() {
        return queryStringParameters;
    }

    public void setQueryStringParameters(Map<String, String> queryStringParameters) {
        this.queryStringParameters = queryStringParameters;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Request{" +
                "resource='" + resource + '\'' +
                ", path='" + path + '\'' +
                ", httpMethod=" + httpMethod +
                ", headers=" + headers +
                ", queryStringParameters=" + queryStringParameters +
                ", pathParameters=" + pathParameters +
                ", body='" + body + '\'' +
                '}';
    }
}
