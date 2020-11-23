package service;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import service.resources.AuthenticationFilter;
import service.resources.CorsFilter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomApplicationConfig extends ResourceConfig
{
    //constracture
    public CustomApplicationConfig()
    {
        packages("service.resources");

        // data base
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, LoggingFeature.DEFAULT_MAX_ENTITY_SIZE));

        register(CorsFilter.class);

        // authentication
        register(AuthenticationFilter.class);
    }

}
