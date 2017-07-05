package com.example;

import ratpack.error.ServerErrorHandler;
import ratpack.guice.Guice;
import ratpack.handling.Handler;
import ratpack.http.MediaType;
import ratpack.http.RequestBodyTooLargeException;
import ratpack.server.RatpackServer;
import ratpack.ssl.SSLContexts;

import java.io.File;

/**
 * Created by akuma110 on 7/5/2017.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        /*
        If content length is 10KB (smaller than 1 MB), proper error message is thrown.
        But If content length is 1 MB, No response is sent at all.
        */
        final int contentLength = 1*1024*1024;
        RatpackServer.start(server -> {
                    server
                            .serverConfig(serverConfigBuilder -> serverConfigBuilder
                                           .maxContentLength(contentLength)
                            )
                            .registry(Guice.registry(bindingsSpec ->
                                    bindingsSpec.module(AppModule.class)
                            ))
                            .handlers(chain -> {
                                        chain.post(ctx -> {
                                            System.out.println("request received..");
                                            if (ctx.getRequest().getContentLength() > contentLength) {
                                                ctx.error(new RequestBodyTooLargeException(contentLength,ctx.getRequest().getContentLength()));
                                            }else {
                                                ctx.getRequest().getBody().then(typedData -> {
                                                    //System.out.print(typedData.getText());
                                                    ctx.render("OK");
                                                });
                                            }
                                        });
                                    }
                            );
                }
        );

    }
}
