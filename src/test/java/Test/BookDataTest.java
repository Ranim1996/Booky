package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookDataTest {

    @Test // test the size of the array
    void AddToLanguageList(){
        List<Language> languages = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        int size;
        size = languages.size();

        assertEquals(2, size);
    }

    @Test // test the size of book array
    void AddToBookList(){
        List<Language> languages = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        books.add(new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),l1));
        books.add(new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), l2));

        int size;
        size = books.size();

        assertEquals(2, size);
    }

    @Test // test get book by language
    void getBookByLanguage(){
        List<Language> languages = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Book b1 = new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),l1);
        Book b2 = new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), l2);

        books.add(b1);
        books.add(b2);

        assertEquals(l1.getName(), b1.getLanguage_code().getName());
    }

    @Test // test get book by type
    void getBookByType(){
        List<Language> languages = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Book b1 = new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),l1);
        Book b2 = new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), l2);

        books.add(b1);
        books.add(b2);

        assertEquals(BookType.DetectiveandMystery, b1.getBookType());
    }

    @Test // test get book id
    void getBookId(){
        List<Language> languages = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Book b1 = new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),l1);
        Book b2 = new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), l2);

        books.add(b1);
        books.add(b2);

        assertEquals(3, b1.getId());
    }

    @Test // test delete book
    void deleteBook(){

        List<Language> languages = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Book b1 = new Book(3, "Name3","Author3", BookType.DetectiveandMystery, "Info", LocalDate.now(),l1);
        Book b2 = new Book(4, "Name4","Author4", BookType.LitraryFiction, "Info",LocalDate.now(), l2);

        books.add(b1);
        books.add(b2);

        books.remove(b1);

        assertEquals(1, books.size());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // test book object must not be null
    void getBook(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Book Object must not be null");

        Book b = null;
    }



}
