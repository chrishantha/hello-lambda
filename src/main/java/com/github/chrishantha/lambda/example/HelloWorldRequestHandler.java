package com.github.chrishantha.lambda.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HelloWorldRequestHandler implements RequestHandler<Request, Response> {

    private static final String HEADER_FUNCTION_NAME = "x-function-name";
    private static final String HEADER_FUNCTION_VERSION = "x-function-version";
    private static final String HEADER_REMAINING_TIME_IN_MILLIS = "x-remaining-time-in-millis";
    private static final String HEADER_UP_TIME = "x-up-time-in-millis";
    private static final String HEADER_VM_STARTUP_TIME = "x-vm-startup-time-in-millis";
    private static final String HEADER_HANDLER_INIT_TIME = "x-handler-init-time-in-millis";
    private static final String HEADER_PROCESSING_TIME = "x-processing-time-in-millis";
    private static final String HEADER_OS_NAME = "x-os-name";
    private static final String HEADER_OS_ARCH = "x-os-arch";
    private static final String HEADER_OS_VERSION = "x-os-version";
    private static final String HEADER_AVAILABLE_PROCESSORS = "x-available-processors";
    private static final String HEADER_FREE_MEMORY = "x-free-memory-in-bytes";
    private static final String HEADER_MAX_MEMORY = "x-max-memory-in-bytes";
    private static final String HEADER_TOTAL_MEMORY = "x-total-memory-in-bytes";

    private final long vmStartupTime;
    private final long handlerInitTime;

    public HelloWorldRequestHandler() {
        long startNanoTime = System.nanoTime();
        long startTime = System.currentTimeMillis();
        long vmStartTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        vmStartupTime = startTime - vmStartTime;
        System.out.println(String.format("VM Startup Time: %d ms", vmStartupTime));
        handlerInitTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanoTime);
        System.out.println(String.format("Request Handler Initialized. Initialization Time %d ms",
                handlerInitTime));
    }

    public Response handleRequest(Request request, Context context) {
        long startNanoTime = System.nanoTime();
        LambdaLogger logger = context.getLogger();
        logger.log(String.format("Received : %s", request.getBody()));
        String responseBody = "Hello, World!";
        Map<String, String> headers = new HashMap<>();
        Response response = new Response();
        response.setHeaders(headers);
        response.setBody(responseBody);
        // AWS Request ID is already available as a header. See: x-amzn-RequestId
        headers.put(HEADER_FUNCTION_NAME, context.getFunctionName());
        headers.put(HEADER_FUNCTION_VERSION, context.getFunctionVersion());
        headers.put(HEADER_REMAINING_TIME_IN_MILLIS, String.valueOf(context.getRemainingTimeInMillis()));
        headers.put(HEADER_UP_TIME, String.valueOf(ManagementFactory.getRuntimeMXBean().getUptime()));
        headers.put(HEADER_VM_STARTUP_TIME, String.valueOf(vmStartupTime));
        headers.put(HEADER_HANDLER_INIT_TIME, String.valueOf(handlerInitTime));
        headers.put(HEADER_OS_NAME, System.getProperty("os.name"));
        headers.put(HEADER_OS_ARCH, System.getProperty("os.arch"));
        headers.put(HEADER_OS_VERSION, System.getProperty("os.version"));
        headers.put(HEADER_AVAILABLE_PROCESSORS, String.valueOf(Runtime.getRuntime().availableProcessors()));
        headers.put(HEADER_FREE_MEMORY, String.valueOf(Runtime.getRuntime().freeMemory()));
        headers.put(HEADER_MAX_MEMORY, String.valueOf(Runtime.getRuntime().maxMemory()));
        headers.put(HEADER_TOTAL_MEMORY, String.valueOf(Runtime.getRuntime().totalMemory()));
        long requestProcessingTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanoTime);
        headers.put(HEADER_PROCESSING_TIME, String.valueOf(requestProcessingTime));
        return response;
    }

}