package service;

import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.net.URI;
import java.time.LocalDate;

/**
 * This class deploys CustomApplicationConfig on a Grizzly server
 */
class DataMain {

    private static final URI BASE_URI = URI.create("http://localhost:9090/booky/");

    public static void main(String[] args) {

        DataCountryController countryController = new DataCountryController();
        DataLanguageController languageController = new DataLanguageController();
        DataBookController bookController = new DataBookController();

        Language language = new Language("EN", "English");
        Book book = new Book(0, "New Book", "New writer",
                BookType.Horror, "Information here", LocalDate.now(), language);

        //show all countries and by code
        countryController.showAllCountries();
        countryController.showCountryByCode("USA");

        //show all languages and by code
        languageController.showAllLanguages();
        languageController.showLanguageByCode("AR");

        //show all books
        bookController.showAllBooks();
        //filter books by french language
        bookController.showBookByLanguageCode("FR");
        //add book to the db
        bookController.addBook(book);

    }
}
