package com.github.chrishantha.lambda.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class HelloWorldRequestHandler implements RequestHandler<Request, Response> {

    private static final String HEADER_FUNCTION_NAME = "x-bal-lambda-function-name";
    private static final String HEADER_FUNCTION_VERSION = "x-bal-lambda-function-version";
    private static final String HEADER_REMAINING_TIME_IN_MILLIS = "x-bal-lambda-remaining-time-in-millis";

    public HelloWorldRequestHandler() {
        System.out.println("Request Handler Initialized.");
    }

    public Response handleRequest(Request request, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("Received : %s", request.getBody()));
        String responseBody = "Hello, World!";
        Map<String, String> headers = new HashMap<>();
        // AWS Request ID is already available as a header. See: x-amzn-RequestId
        headers.put(HEADER_FUNCTION_NAME, context.getFunctionName());
        headers.put(HEADER_FUNCTION_VERSION, context.getFunctionVersion());
        headers.put(HEADER_REMAINING_TIME_IN_MILLIS, String.valueOf(context.getRemainingTimeInMillis()));
        Response response = new Response();
        response.setHeaders(headers);
        response.setBody(responseBody);
        return response;
    }

}