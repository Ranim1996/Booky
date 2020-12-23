package service;

import service.ControllerPersistance.DataBookController;
import service.ControllerPersistance.DataStatisticsController;
import service.model.BookType;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    public static void main(String[] args) {

        DataStatisticsController statisticsController = new DataStatisticsController();

        statisticsController.statisticsPerLanguage();

    }
}
