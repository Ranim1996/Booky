package service;


import service.ControllerPersistance.DataBookController;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    public static void main(String[] args) {

    // this java class is created to test everything in the controller
        DataBookController controller = new DataBookController();

        controller.bookFilterByFirstNameChars("sw");
    }
}
