package service;

import service.ControllerPersistance.*;
import service.model.*;

import java.net.URI;
import java.time.LocalDate;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

    // this java class is created to test everything in the controller

        DataLikeController likeController = new DataLikeController();
        likeController.DeleteBook(9,7);

    }
}
