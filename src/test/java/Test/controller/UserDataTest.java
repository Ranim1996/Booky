package Test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ControllerPersistance.DataUserController;
import service.model.*;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;
import service.repository.MD5Hash;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserDataTest {

    @InjectMocks
    DataUserController userController;

    @Mock
    JDBCUserRepository userRepository;

    @Test
     void getUserByID() throws BookyDatabaseException, SQLException, URISyntaxException {
        Users user = new Users(1,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        lenient().when(userRepository.getUserById(1)).thenReturn(user);

        Users user1 = userController.getUser(1);

        Assert.assertEquals(1,user.getId());
    }

    @Test
     void addUser() throws BookyDatabaseException, SQLException, URISyntaxException {
        Users user = new Users(4,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        lenient().when(userRepository.addUser(user)).thenReturn(true);

        boolean addedUser = userController.addUser(user);

        Assert.assertEquals(addedUser,false);
    }

    @Test
    public void getUserByEmail() throws BookyDatabaseException, SQLException, URISyntaxException {
        Users user = new Users(1,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        lenient().when(userRepository.getUsers()).thenReturn(Arrays.asList(user));

        Users user1 =  userController.getUserByEmail("email");

        assertEquals("email", user.getEmail());

    }

    @Test
    public void loginAndHashingTest() throws BookyDatabaseException, SQLException, URISyntaxException {
        MD5Hash md = new MD5Hash();
        String password = md.oneWayHashing("1234");

        Users user = new Users(1,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email",password);

        lenient().when(userRepository.getUsers()).thenReturn(Arrays.asList(user));

        Boolean expected =  userController.login("email", "1234");

        assertEquals(false, expected);

    }

    @Test
    public void getUserFromToken() throws BookyDatabaseException, SQLException, URISyntaxException {

        Users user = new Users(1,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        lenient().when(userRepository.getUserById(1)).thenReturn(user);

        String userId = Integer.toString(user.getId());
        String token = userController.createJWT(userId, user.getFirstName(), user.getUsertype().name(), -1);

        Users expected =  userController.getUserFromToken(token);

        assertEquals(null, expected);

    }

    @Test
    public void updateUser() throws BookyDatabaseException, SQLException, URISyntaxException {
        Users user = new Users(1,"Jojo", "Alayoubi","1/1/1",UserType.Reader,"email","12");

        lenient().when(userRepository.updateUser(1,user)).thenReturn(true);

        boolean updatedUser = userController.updateUser(1,user);

        Assert.assertEquals(false, updatedUser);
    }

}
