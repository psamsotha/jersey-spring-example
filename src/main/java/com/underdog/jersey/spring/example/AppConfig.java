
package com.underdog.jersey.spring.example;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 *
 * @author Paul Samsotha
 */
@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {
    
    public AppConfig() {
        packages("com.underdog.jersey.spring.example.resource");
        register(RequestContextFilter.class);
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
