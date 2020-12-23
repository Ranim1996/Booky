package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Book;
import service.model.BookType;
import service.model.Language;

import java.time.LocalDate;

public class BookTest {

    @Test //check whether book info are correct
    void NewBookTest()
    {
        Language arabic = new Language("AR", "Arabic");

        Book b = new Book(1, "BookName1","Author1", BookType.Classics, "Info", LocalDate.now(), arabic);

        assertEquals(1, b.getId());
        assertEquals("BookName1", b.getBookName());
        assertEquals("Author1", b.getAuthorName());
        assertEquals(BookType.Classics, b.getBookType());
        assertEquals("Info", b.getDescribtion());
        assertEquals(LocalDate.now(), b.getTime());
        assertEquals(arabic, b.getLanguage_code());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // book name null
    void getBookNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Book name must not be null");

        Book b = new Book();
        b.setBookName(null);
    }

    @Test // book name string is empty
    void getBookNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Book name must not be empty");

        Book b = new Book();
        b.setBookName(" ");
    }

    @Test // author name null
    void getAuthorNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Author name must not be null");

        Book b = new Book();
        b.setAuthorName(null);
    }

    @Test // author name string is empty
    void getAuthorNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Author name must not be empty");

        Book b = new Book();
        b.setAuthorName(" ");
    }

    @Test // book language null
    void getBookLanguageWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Book language must not be null required!");

        Book b = new Book();
        b.setLanguage_code(null);
    }

}

