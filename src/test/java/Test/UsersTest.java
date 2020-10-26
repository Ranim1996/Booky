package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Country;
import service.model.Language;
import service.model.UserType;
import service.model.Users;

public class UsersTest {

    @Test //check whether user info are correct
    public void NewCountryTest()
    {
        Country syria = new Country("SY", "Syria");
        Language arabic = new Language("AR", "Arabic");


        Users u = new Users(1, "Ranim", "Alayoubi", "06/06/1996" , UserType.Admin,
                "ranim@gmail.com","password199","0684567447", syria, arabic);
        assertEquals(1, u.getId());
        assertEquals("Ranim", u.getFirstName());
        assertEquals("Alayoubi", u.getLastName());
        assertEquals("06/06/1996", u.getDateOfBirth());
        assertEquals(UserType.Admin, u.getType());
        assertEquals("ranim@gmail.com", u.getEmail());
        assertEquals("password199", u.getPassword());
        assertEquals("0684567447", u.getPhoneNumber());
        assertEquals(syria, u.getCountry());
        assertEquals(arabic, u.getLanguage());
    }

    @Rule // this rule is added to throw exceptions when its needed
    public ExpectedException thrown = ExpectedException.none();

    @Test // user first name null
    public void getUserFirstNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be null");

        Users u= new Users();
        u.setFirstName(null);
    }

    @Test // user first name string is empty
    public void getUserFirstNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User first name must not be empty");

        Users u= new Users();
        u.setFirstName(" ");
    }

    @Test // user last name null
    public void getUserLastNameWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be null");

        Users u= new Users();
        u.setLastName(null);
    }

    @Test // user last name string is empty
    public void getUserLastNameWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User last name must not be empty");

        Users u= new Users();
        u.setLastName(" ");
    }

    @Test // user email null
    public void getUserEmailWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be null");

        Users u= new Users();
        u.setEmail(null);
    }

    @Test // user email string is empty
    public void getUserEmailWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Email must not be empty");

        Users u= new Users();
        u.setEmail(" ");
    }

    @Test // user password null
    public void getUserPasswordWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be null");

        Users u= new Users();
        u.setPassword(null);
    }

    @Test // user password string is empty
    public void getUserPasswordWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password must not be empty");

        Users u= new Users();
        u.setPassword(" ");
    }

    @Test // user date birth null
    public void getUserDateBirthWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User date birth must not be null");

        Users u= new Users();
        u.setDateOfBirth(null);
    }

    @Test // user date birth string is empty
    public void getUserDateBirthWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User date birth must not be empty");

        Users u= new Users();
        u.setDateOfBirth(" ");
    }

    @Test // user type null
    public void getUserTypeWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User type must not be null");

        Users u= new Users();
        u.setType(null);
    }

    @Test // user phone number null
    public void getUserPhoneNumberWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Phone number must not be null");

        Users u= new Users();
        u.setPhoneNumber(null);
    }

    @Test // user phone number string is empty
    public void getUserPhoneNumberWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Phone number must not be empty");

        Users u= new Users();
        u.setPhoneNumber(" ");
    }

    @Test // user language null
    public void getUserLanguageWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User language must not be null");

        Users u= new Users();
        u.setLanguage(null);
    }

    @Test // user country null
    public void getUserCountryWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User country must not be null");

        Users u= new Users();
        u.setCountry(null);
    }


}
