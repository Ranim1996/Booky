package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.UserType;
import service.model.Users;

public class UsersTest {

    @Test //check whether user info are correct
    void NewUserTest()
    {
        Users u = new Users(1, "Ranim", "Alayoubi", "06/06/1996" , UserType.Admin,
                "ranim@gmail.com","password199");
        assertEquals(1, u.getId());
        assertEquals("Ranim", u.getFirstName());
        assertEquals("Alayoubi", u.getLastName());
        assertEquals("06/06/1996", u.getDateOfBirth());
        assertEquals(UserType.Admin, u.getUsertype());
        assertEquals("ranim@gmail.com", u.getEmail());
        assertEquals("password199", u.getPassword());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    void getUserFirstNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be null");

        Users u= new Users();
        u.setFirstName(null);
    }

    @Test // user first name string is empty
    void getUserFirstNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be empty");

        Users u= new Users();
        u.setFirstName(" ");
    }

    @Test // user last name null
    void getUserLastNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be null");

        Users u= new Users();
        u.setLastName(null);
    }

    @Test // user last name string is empty
    void getUserLastNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be empty");

        Users u= new Users();
        u.setLastName(" ");
    }

    @Test // user email null
    void getUserEmailWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be null");

        Users u= new Users();
        u.setEmail(null);
    }

    @Test // user email string is empty
    void getUserEmailWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be empty");

        Users u= new Users();
        u.setEmail(" ");
    }

    @Test // user password null
    void getUserPasswordWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be null");

        Users u= new Users();
        u.setPassword(null);
    }

    @Test // user password string is empty
    void getUserPasswordWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be empty");

        Users u= new Users();
        u.setPassword(" ");
    }

    @Test // user date birth null
    void getUserDateBirthWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User date birth must not be null");

        Users u= new Users();
        u.setDateOfBirth(null);
    }

    @Test // user date birth string is empty
    void getUserDateBirthWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User date birth must not be empty");

        Users u= new Users();
        u.setDateOfBirth(" ");
    }

    @Test // user type null
    void getUserTypeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User type must not be null");

        Users u= new Users();
        u.setUsertype(null);
    }

    @Test // user id zero
    void getUserIdWithZeroValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User id must not be zero");

        Users u= new Users();
        u.setId(0);
    }

}
