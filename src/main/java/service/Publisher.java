package service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.grizzly.websockets.WebSocketAddOn;
import org.glassfish.grizzly.websockets.WebSocketEngine;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import service.repository.ChatRoomRepository;
import service.resources.ChatResources;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class Publisher {

    private static final URI BASE_URI = URI.create("http://0.0.0.0:9090/booky/");

    public static void main(String[] args) throws IOException {

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();
            // create and start a grizzly server
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, customApplicationConfig, true);

            //books get URLs
            String[] getOperations = {
                    BASE_URI.toURL() + "books?type=Fantasy", // filter books with book type classics
                    BASE_URI.toURL() + "users?language=EN", //show user speaks english

            };
            for (String getOperation : getOperations) {
                System.out.println(getOperation);
            }

        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9988).build();
        ResourceConfig resourceConfig = new ResourceConfig(ChatResources.class);

        resourceConfig.packages("myrest");
        // disable authentication
        //resourceConfig.register(AuthenticationFilter.class);


        // don't start the server yet, wait until the websocket addon has been added
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig,false);

        // setup static file handler so that we can also serve html pages.
        StaticHttpHandler staticHandler = new StaticHttpHandler("static");
        staticHandler.setFileCacheEnabled(false);
        server.getServerConfiguration().addHttpHandler(staticHandler,"/static/");

        // Create websocket addon
        WebSocketAddOn webSocketAddOn = new WebSocketAddOn();
        server.getListeners().forEach(listener -> { listener.registerAddOn(webSocketAddOn);});

        // register my websocket app
        ChatRoomRepository chatRoomRepository = new ChatRoomRepository();
        WebSocketEngine.getEngine().register("/ws", "/project", chatRoomRepository);

        // Now start the server
        server.start();

        // prevent the app from closing
        System.out.println("Press enter to stop the server...");
        System.in.read();
    }
}
