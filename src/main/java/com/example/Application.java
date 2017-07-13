package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.exec.Blocking;
import ratpack.guice.Guice;
import ratpack.http.RequestBodyTooLargeException;
import ratpack.server.RatpackServer;

/**
 * Created by akuma110 on 7/5/2017.
 */
public class Application {

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(Application.class);
        /*
        If content length is 10KB (smaller than 1 MB), proper error message is thrown.
        But If content length is 1 MB, No response is sent at all.
        */
        final int contentLength = 1*1024*1024;
        RatpackServer.start(server -> {
                    server
                            .serverConfig(serverConfigBuilder -> serverConfigBuilder
                                           .maxContentLength(contentLength)
                                    .development(false)
                            )
                            .registry(Guice.registry(bindingsSpec ->
                                    bindingsSpec.module(AppModule.class)
                            ))
                            .handlers(chain -> {
                                        chain.post(ctx -> {
                                            logger.info("In Main Thread");
                                            if (ctx.getRequest().getContentLength() > contentLength) {
                                                ctx.error(new RequestBodyTooLargeException(contentLength,ctx.getRequest().getContentLength()));
                                            }else {
                                                ctx.getRequest().getBody().then(typedData -> {

                                                    Blocking.get(()-> {
                                                        logger.info("Inside Blocking thread");
                                                        return "OK";
                                                    }).then(s -> ctx.render(s));
                                                });
                                            }
                                        });
                                    }
                            );
                }
        );

    }
}
