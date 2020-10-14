package service;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class Publisher {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

        try {
            CustomApplicationConfig customApplicationConfig = new CustomApplicationConfig();
            // create and start a grizzly server
            HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, customApplicationConfig, true);

            System.out.println("Hosting resources at " + BASE_URI.toURL());

            //books get URLs
            String[] getOperations = {
                    BASE_URI.toURL() + "books/2", // show book with id 2
                    BASE_URI.toURL() + "books", // show all books
                    BASE_URI.toURL() + "books?language=EN", // filter books with language code EN
                    BASE_URI.toURL() + "books/5", // nothing will be shown just for testing that there is no book with id 5
                    BASE_URI.toURL() + "books?language=AR", // filter books with language code AR
                    BASE_URI.toURL() + "books?type=Fantasy", // filter books with book type classics


                    //users get URLs
                    BASE_URI.toURL() + "users/3", //show user with id 3
                    BASE_URI.toURL() + "users", //show all users
                    BASE_URI.toURL() + "users?language=EN", //show user speaks english
                    BASE_URI.toURL() + "users/10" // nothing will be shown for testing that there is no user with id 10

            };
            for (String getOperation : getOperations) {
                System.out.println(getOperation);
            }

        } catch (IOException ex) {
            Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
