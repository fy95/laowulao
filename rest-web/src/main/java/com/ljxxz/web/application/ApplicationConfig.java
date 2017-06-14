package com.ljxxz.web.application;

import com.ljxxz.web.filter.LogResponseFilter;
import com.ljxxz.web.filter.LoginCheckFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by fuzhao on 2015/9/21.
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig(){
        packages("com.ljxxz.web.api");
        register(LoginCheckFilter.class);
        register(LogResponseFilter.class);
        //json转换
        register(JacksonFeature.class);
    }
}
