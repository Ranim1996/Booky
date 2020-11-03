package service;

import java.net.URI;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

        DataCountryController countryController = new DataCountryController();
        DataLanguageController languageController = new DataLanguageController();

        countryController.showAllCountries();
        languageController.showAllLanguages();
    }
}
