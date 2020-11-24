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
    private BookType bookType; // type of the book
    private String describtion; // short describtion about the content of the book
    LocalDate time = LocalDate.now(); //the time when the admin publish info about a book
    private Language language_code; // book's language
    private static int idSeeder = 1;

    //constractures
    public Book(int id, String bookName, String authorName, BookType type, String describtion,
                LocalDate time, Language language) {
        this.id = id;
//        idSeeder++;
        this.bookName = bookName;
        this.authorName = authorName;
        this.time = time;
        this.describtion = describtion;
        this.bookType = type;
        this.language_code = language;
    }

    public Book(int id, String bookName, String authorName, BookType type){
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookType = type;
    }

    public Book() {

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

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
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

    public Language getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(Language language_code) {
        this.language_code = language_code;
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
                ", Book Type: " + bookType + '\'' +
                ", Published at: " + time + '\'' +
                ", Language: " + language_code + '\'' +
                '}';
    }
}
