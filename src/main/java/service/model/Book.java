package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@XmlRootElement
public class Book {
    //fields
    private int id;
    private String bookName; // book name
    private String authorName; // for whom is the book
    private BookType type; // type of the book
    private String describtion; // short describtion about the content of the book
    LocalDate time = LocalDate.now(); //the time when the admin publish info about a book
    private Language language; // book's language

    //constractures
    public Book(int id, String bookName, String authorName, BookType type, String describtion, LocalDate time, Language language) {
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.time = time;
        this.describtion = describtion;
        this.type = type;
        this.language = language;
    }

    public Book() {
    }

    //geteers and setters
    public int getBookID() {
        return id;
    }
    public void setBookID(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String name) {
        this.bookName = name;
    }

    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String name) {
        this.authorName = name;
    }

    public BookType getBookType() {
        return type;
    }
    public void setBookType(BookType type) {
        this.type = type;
    }

    public LocalDate getTime() {
        return time;
    }
    public void setTime(LocalDate time) { this.time = time; }

    public String getBookContent() {
        return describtion;
    }
    public void setBookContent(String content) {
        this.describtion = content;
    }

    public Language getBookLanguage() {
        return language;
    }
    public void setBookLanguage(Language language) { this.language = language; }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book b = (Book) o;
        return id == b.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book ID: " + id +
                ", Book Name: '" + bookName + '\'' +
                ", Written by: " + authorName +
                ", Book Type: " + type +
                ", Published at: " + time +
                '}';
    }
}
