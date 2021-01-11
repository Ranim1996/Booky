package Test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ControllerPersistance.DataUserController;
import service.model.*;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;
import service.repository.MD5Hash;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class UserDataTest {

    @InjectMocks
    DataUserController userController;

    @Mock
    JDBCUserRepository userRepository;

    @Test
    public void getUserByID() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        when(userRepository.getUserById(1)).thenReturn(user);

        Users user1 = userController.getUser(1);

        Assert.assertEquals(user.getId(),user1.getId());
    }

    @Test
    public void addUser() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        when(userRepository.addUser(user)).thenReturn(true);

        boolean addedUser = userController.addUser(user);

        Assert.assertEquals(true, addedUser);
    }

    @Test
    public void getUserByEmail() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        when(userRepository.getUsers()).thenReturn(Arrays.asList(user));

        Users expected =  userController.getUserByEmail("email");

        assertEquals(user, expected);

    }

    @Test
    public void loginAndHashingTest() throws BookyDatabaseException, SQLException {
        MD5Hash md = new MD5Hash();
        String password = md.oneWayHashing("1234");

        Users user = new Users(1,"Ranim", "Alayoubi","1/1/1",UserType.Reader,"email",password);

        when(userRepository.getUsers()).thenReturn(
                Arrays.asList(user));

        Boolean expected =  userController.login("email", "1234");

        assertEquals(true, expected);

    }

    @Test
    public void getUserFromToken() throws BookyDatabaseException, SQLException {

        Users user = new Users(1,"Ranim", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        when(userRepository.getUserById(1)).thenReturn(user);

        String userId = Integer.toString(user.getId());
        String token = userController.createJWT(userId, user.getFirstName(), user.getUsertype().name(), -1);

        Users expected =  userController.getUserFromToken(token);

        assertEquals(user, expected);

    }

}
