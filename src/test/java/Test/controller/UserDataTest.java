package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.ControllerPersistance.DataUserController;
import service.model.UserType;
import service.model.Users;
import service.repository.BookyDatabaseException;
import service.repository.JDBCUserRepository;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserDataTest {

    @InjectMocks
    DataUserController userController;

    @Mock
    JDBCUserRepository userRepository;

    @Test
    void UpdateUser() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim","Alayoubi","06/06/1996", UserType.Admin,"email@gmail.com","password");

        when(userRepository.updateUser(1,user)).thenReturn(true);

        boolean updatedUser = userController.updateUser(1,user);

        assertEquals(true, updatedUser);
    }

    @Test
    void GetUserById() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim","Alayoubi","06/06/1996", UserType.Admin,"email@gmail.com","password");

        when(userRepository.getUserById(1)).thenReturn(user);

        Users user1 = userController.getUser(1);

        assertEquals(user, user1);
    }

    @Test
    void AddUser() throws BookyDatabaseException, SQLException {
        Users user = new Users(1,"Ranim","Alayoubi","06/06/1996", UserType.Admin,"email@gmail.com","password");

        when(userRepository.addUser(user)).thenReturn(true);

        boolean addedUser = userController.addUser(user);

        assertEquals(true, addedUser);
    }

}
