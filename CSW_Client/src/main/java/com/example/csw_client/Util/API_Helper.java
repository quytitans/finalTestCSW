package com.example.csw_client.Util;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class API_Helper {
    public static Client createJerseyRestClient() {
        ClientConfig clientConfig = new ClientConfig();
        // Config logging for client side
        clientConfig.register( //
                new LoggingFeature( //
                        Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), //
                        Level.INFO, //
                        LoggingFeature.Verbosity.PAYLOAD_ANY, //
                        10000));
        return ClientBuilder.newClient(clientConfig);
    }
}
