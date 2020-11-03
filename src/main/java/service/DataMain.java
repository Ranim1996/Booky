package service;

import service.model.Language;

import java.net.URI;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

        DataCountryController countryController = new DataCountryController();
        DataLanguageController languageController = new DataLanguageController();
        DataBookController bookController = new DataBookController();

        //show all countries and by code
        countryController.showAllCountries();
        countryController.showCountryByCode("USA");

        //show all languages and by code
        languageController.showAllLanguages();
        languageController.showLanguageByCode("AR");

        //show all books
        bookController.showAllBooks();
        //filter books by arabic language
        bookController.showBookByLanguageCode("FR");
    }
}
