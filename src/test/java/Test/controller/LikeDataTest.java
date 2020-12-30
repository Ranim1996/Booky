package Test.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ControllerPersistance.DataLikeController;
import service.model.*;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLikeRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LikeDataTest {

    @InjectMocks
    DataLikeController likeController;

    @Mock
    JDBCLikeRepository likeRepository;

    @Test
    void likebook() throws BookyDatabaseException, SQLException {
        Like like = new Like(1,2,3);

        when(likeRepository.addLike(like)).thenReturn(true);

        boolean liked = likeController.likeBook(like);

        assertEquals(true,liked);
    }

    @Test
    void getLikedBooksByUser() throws BookyDatabaseException, SQLException {
        List<Book> books = new ArrayList<>();
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Book book = new Book(1,"book","author", BookType.Classics,"info",date, language,"");

        books.add(book);

        when(likeRepository.getLikedBooksByUserId(1)).thenReturn(books);

        List<Book> bookList = likeController.likedBooksByUser(1);

        assertEquals(books,bookList);
    }

    @Test
    public void deleteBook() throws BookyDatabaseException, SQLException {
        LocalDate date = LocalDate.now();
        Language language = new Language("Fr","French");

        Users user = new Users(1,"Ranim","Alayoubi","06/06/1996", UserType.Admin,"email@gmail.com","password");
        Book book = new Book(1,"book1","author1",BookType.LitraryFiction,"info",date,language,"");

        when(likeRepository.deleteBook(book.getId(),user.getId())).thenReturn(true);

        boolean deletedBook = likeController.deleteBook(book.getId(),user.getId());

        Assert.assertEquals(true, deletedBook);
    }
}
