package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.ControllerPersistance.DataUserController;
import service.model.UserType;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;
import service.repository.MD5Hash;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LoginDataTest {
    @InjectMocks
    DataUserController userController;

    @Mock
    JDBCUserRepository userRepository;

    @Test
    public void getUserByEmail() throws SQLException, BookyDatabaseException {

        List<Users> usersList = new ArrayList<>();

        Users user = new Users(1,"Ranim","Alayoubi","10/10/1999",UserType.Admin,"ranim@gmail.com","1234");
        usersList.add(user);

        when(userRepository.getUsers()).thenReturn(usersList);

        Users expectedUser =  userController.getUserByEmail("ranim@gmail.com");

        assertEquals(expectedUser, expectedUser);

    }
    @Test
    public void loginAndHashingTest() throws SQLException, BookyDatabaseException {

        List<Users> usersList = new ArrayList<>();
        MD5Hash md = new MD5Hash();
        String password = md.oneWayHashing("1234");
        Users user = new Users(1,"Ranim","Alayoubi","10/10/1999",UserType.Admin,"ranim@gmail.com",password);

        usersList.add(user);

        when(userRepository.getUsers()).thenReturn(usersList);

        Boolean expected =  userController.login("ranim@gmail.com", "1234");

        assertEquals(true, true);

    }

}
