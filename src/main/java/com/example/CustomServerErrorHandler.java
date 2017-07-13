package com.example;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;
import ratpack.http.MediaType;
import ratpack.http.RequestBodyTooLargeException;

import java.util.concurrent.ExecutionException;

/**
 * Created by razamd on 2/15/2017.
 */
public class CustomServerErrorHandler implements ServerErrorHandler {

    //private final Logger logger = LoggerFactory.getLogger(CustomServerErrorHandler.class);

    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        //throwable.printStackTrace();
        //logger.info(throwable.getMessage());
        System.out.println("Error occured");

        int status = 500;
        Response response = new Response();

        if(throwable instanceof RequestBodyTooLargeException){
            status = 413;
            response.setStatus("Request Body Too large");
            response.setMessage("Request Body Too large");
            response.setDevMessage(throwable.getMessage());
        }
        else {
            response.setStatus("FAIL");
            response.setDevMessage(throwable.getMessage());
        }
        response.setCode(status);
        context.getResponse().status(status).contentType(MediaType.APPLICATION_JSON).send(response.toString());
    }
}
