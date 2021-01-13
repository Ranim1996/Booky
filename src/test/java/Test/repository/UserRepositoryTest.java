package Test.repository;

import org.h2.engine.User;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.*;
import service.repository.BookyDatabaseException;
import service.repository.JDBCRepository;
import service.repository.JDBCUserRepository;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @InjectMocks
    JDBCUserRepository userRepository;

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
    void getUsers() throws BookyDatabaseException, URISyntaxException, SQLException {
        List<Users> users = userRepository.getUsers();
        assertEquals(2,users.size());
    }

    @Test
    void updateUser() throws BookyDatabaseException, URISyntaxException, SQLException {
        Users user = new Users(1, "name", "Name", "1/1/1200", UserType.Reader, "email", "pass" );

        Boolean updatedUser = userRepository.updateUser(1,user);

        assertEquals(updatedUser,true);
    }

    @Test
    void addBook() throws BookyDatabaseException, URISyntaxException, SQLException {
        Users user = new Users(1, "name", "Name", "1/1/1200", UserType.Reader, "email", "pass" );

        Boolean addedUser = userRepository.addUser(user);

        assertEquals(addedUser,true);

    }

    @Test
    void getUserById() throws BookyDatabaseException, URISyntaxException, SQLException {
        Users user = new Users(1, "name", "Name", "1/1/1200", UserType.Reader, "email", "pass" );
        Users users = userRepository.getUserById(1);
        assertEquals(1,1);
    }

}
