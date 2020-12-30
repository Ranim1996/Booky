package service.repository;

import service.model.Book;
import service.model.BookType;
import service.model.Language;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookFakeDataStore {
    //fields
    private final List<Book> bookList = new ArrayList<>();
    private final List<Language> languagesList = new ArrayList<>();

    //constracture "Some fake data are added here"
    public BookFakeDataStore() {

        //adding to language list
        Language arabic = new Language("AR", "Arabic");
        Language english = new Language("EN", "English");
        Language french = new Language("FR", "French");

        languagesList.add(arabic);
        languagesList.add(english);
        languagesList.add(french);

        //book lists for now there are 4 different books
        bookList.add(new Book(1, "Name1","Author1", BookType.Classics, "Info", LocalDate.now(), arabic, ""));
        bookList.add(new Book(2, "Name2","Author2", BookType.Fantasy, "Info",LocalDate.now(), french, ""));
        bookList.add(new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),english, ""));
        bookList.add(new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), english,""));

    }

    // getter
    public List<Book> getBooks() {
        return bookList;
    }

    //methods

    //get book by language good to practice with filter here
    public List<Book> getBooksByLanguage(Language language) {
        List<Book> filetered = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getLanguage_code().equals(language)) {
                filetered.add(b);
            }
        }
        return filetered;
    }

    // filter by type
    public List<Book> getBooksByBookType(BookType type) {
        List<Book> filetered = new ArrayList<>();
        for (Book b : bookList) {
            if (b.getBookType().equals(type)) {
                filetered.add(b);
            }
        }
        return filetered;
    }

    //get book by its id
    public Book getBook(int id) {
        for (Book b : bookList) {
            if (b.getId() == id)
                return b;
        }
        return null;
    }

    //find whether book is deleted or not for deleteing book with specific id
    public boolean deleteBook(int id) {
        Book b = getBook(id);
        if (b == null){
            return false;
        }
        return bookList.remove(b);
    }

    // find whether book is added or not for adding book object
    public boolean add(Book b) {
        if (this.getBook(b.getId()) != null) {
            return false;
        }
        bookList.add(b);
        return true;
    }

    //find whether book is updated or not for updating book object
    public boolean update(int id,Book b) {
        Book old = this.getBook(id);
        if (old == null) {
            return false;
        }
        old.setBookName(b.getBookName());
        old.setAuthorName(b.getAuthorName());
        old.setLanguage_code(b.getLanguage_code());
        old.setDescribtion(b.getDescribtion());
        old.setBookType(b.getBookType());
        old.setTime(b.getTime());
        return true;
    }

    // get language by its code
    public Language getLanguage(String languageCode) {
        for (Language l : languagesList) {
            if (l.getCode().equals(languageCode)) {
                return l;
            }
        }
        return null;
    }

    // get book type
    public Book getBookType(BookType type) {
        for (Book b : bookList) {
            if (b.getBookType().equals(type)) {
                return b;
            }
        }
        return null;
    }

}
