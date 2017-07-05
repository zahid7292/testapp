package com.example;


import com.google.inject.AbstractModule;
import ratpack.error.ServerErrorHandler;

import java.util.List;


/**
 * Created by razamd on 1/27/2017.
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ServerErrorHandler.class).to(CustomServerErrorHandler.class);
    }
}
