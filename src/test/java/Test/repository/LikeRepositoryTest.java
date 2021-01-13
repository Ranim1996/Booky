package Test.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Book;
import service.model.BookType;
import service.model.Language;
import service.model.Like;
import service.repository.BookyDatabaseException;
import service.repository.JDBCBookRepository;
import service.repository.JDBCLikeRepository;
import service.repository.JDBCRepository;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class LikeRepositoryTest {

    @InjectMocks
    JDBCLikeRepository likeRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        lenient().when(jdbcRepository.getDataBaseConneection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test"));

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }

    @Test
    void getLikes() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Like> likes = likeRepository.getLikes();
        assertEquals(0,likes.size());
    }

    @Test
    void getLikedBooksByUserId() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Book> likedBooks = likeRepository.getLikedBooksByUserId(1);
        assertEquals(0,likedBooks.size());
    }

//    @Test
//    void addLike() throws BookyDatabaseException, URISyntaxException, SQLException {
//        Like like = new Like(1,2,3);
//        Boolean addedLike = likeRepository.addLike(like);
//        assertEquals(addedLike,true);
//
//    }

    @Test
    void deleteLike() throws BookyDatabaseException, URISyntaxException, SQLException {
        Like like = new Like(1,2,3);
        Boolean deletedLike = likeRepository.deleteBook(2,3);
        assertEquals(deletedLike,true);

    }
}
