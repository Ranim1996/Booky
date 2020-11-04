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
    private static int idSeeder = 1;

    //constractures
    public Book(int id, String bookName, String authorName, BookType type, String describtion,
                LocalDate time, Language language) {
        this.id = idSeeder;
        idSeeder++;
        this.bookName = bookName;
        this.authorName = authorName;
        this.time = time;
        this.describtion = describtion;
        this.type = type;
        this.language = language;
    }

    public Book() {
        this.id = idSeeder;
        idSeeder++;
    }

    //geters and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

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
                ", Book Type: " + type + '\'' +
                ", Published at: " + time + '\'' +
                ", Language: " + language + '\'' +
                '}';
    }
}
