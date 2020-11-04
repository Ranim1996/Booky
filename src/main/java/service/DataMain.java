package service;

import service.model.*;

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
        DataUserController userController = new DataUserController();

        Language language = new Language("FR", "French");
        Country country = new Country("FR", "France");

        Book book = new Book(0, "New Book", "New Author",
                BookType.Horror, "New Information here", LocalDate.now(), language);

        Book updatedBook = new Book(0, "Updated Book", "Updated Author",
                BookType.Horror, "Updated Information here", LocalDate.now(), language);

        Users updatedUser = new Users(0,"Rama", "AL", "12-06-1999",UserType.Reader,"rama@gmail.com",
                "mynewpass", "09638796578", country, language);

        Users user = new Users(0,"Ivana", "Kurd", "10-09-1990",UserType.Reader,"Ivana@gmail.com",
                "ivananan", "0649897659", country, language);

        /* for languages and countries*/
        //show all countries and by code
//        countryController.showAllCountries();
//        countryController.showCountryByCode("USA");

        //show all languages and by code
//        languageController.showAllLanguages();
//        languageController.showLanguageByCode("AR");

        /* for books*/
        //show all books
//        bookController.showAllBooks();
        //filter books by french language
//        bookController.showBookByLanguageCode("FR");
        //add book to the db
//        bookController.addBook(book);
        //update book in db
//        bookController.updateBook(updatedBook);
        //show book by its id
//        bookController.ShowBookById(1);
        //delete book from db
//        bookController.DeleteBook(1);

        /* for users*/
        //show all users from the db
        userController.showAllUsers();
        //show user by id
        userController.ShowUserById(1);
        //update user data in the db
//        userController.updateUser(2, updatedUser);
        //add new user to the db
//        userController.addUser(user);


    }
}
