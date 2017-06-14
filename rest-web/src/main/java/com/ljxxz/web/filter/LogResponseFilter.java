package com.ljxxz.web.filter;

import com.ljxxz.util.LoggerFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * Created by fuzhao on 2015/11/9.
 */
public class LogResponseFilter implements ContainerResponseFilter {

    private static final Logger logger = LoggerFactory.getMainLogger();

    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {
        String method = requestContext.getMethod();
        String path = requestContext.getUriInfo().getPath();
        Object entity = responseContext.getEntity();
        if (entity != null) {
            logger.debug("request {} {}, response {}. ", method, path,
                    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(entity));
        }

    }


}