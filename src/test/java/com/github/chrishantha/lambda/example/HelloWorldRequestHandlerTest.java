package com.github.chrishantha.lambda.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class HelloWorldRequestHandlerTest {

    private HelloWorldRequestHandler helloWorldRequestHandler;

    @BeforeClass
    private void initialize() {
        helloWorldRequestHandler = new HelloWorldRequestHandler();
    }

    @Test
    public void handleRequestTest() {
        String payload = "Hello, World!";
        Request request = new Request();

        Context context = Mockito.mock(Context.class);
        when(context.getLogger()).thenReturn(new LambdaLogger() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }

            @Override
            public void log(byte[] message) {

            }
        });

        Response response = helloWorldRequestHandler.handleRequest(request, context);
        System.out.println(response);
        Assert.assertEquals(response.getBody(), payload);
    }

}
