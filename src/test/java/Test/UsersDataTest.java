package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsersDataTest {

    @Test // test the size of the array languages
    void AddToLanguageList(){
        List<Language> languages = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        int size;
        size = languages.size();

        assertEquals(2, size);
    }

    @Test // test the size of the array country
    void AddToCountryList(){
        List<Country> countries = new ArrayList<>();

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        int size;
        size = countries.size();

        assertEquals(2, size);
    }

    @Test // test the size of the array
    void AddToUsersList() {
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

        int size;
        size = users.size();

        assertEquals(2, size);
    }

    @Test // test get user by language
    void getUserByLanguage(){
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

//        assertEquals(l1.getName(), u1.getLanguage_code().getName());
    }

    @Test // test get user by country
    void getUserByCountry(){
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

//        assertEquals(c1.getName(), u1.getCountry_code().getName());
    }

    @Test // test get user by type
    void getBookByType(){
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

        assertEquals(UserType.Admin, u1.getUsertype());
    }

    @Test // test get user id
    void getUserID(){
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

        assertEquals(1, u1.getId());
    }

    @Test // test delete user
    void deleteUser(){
        List<Language> languages = new ArrayList<>();
        List<Country> countries = new ArrayList<>();
        List<Users> users = new ArrayList<>();

        Language l1 = new Language("AR", "Arabic");
        Language l2 = new Language("EN", "English");

        languages.add(l1);
        languages.add(l2);

        Country c1 = new Country("USA", "America");
        Country c2 = new Country("FR", "France");

        countries.add(c1);
        countries.add(c2);

        Users u1 = new Users(1, "Ranim", "Alayoubi", "06/06/1996", UserType.Admin,
                "ranim@gmail.com", "password199");
        Users u2 = new Users(2, "Obaida", "Bulbul", "29/01/1994", UserType.Reader,
                "obaida@gmail.com", "password123");

        users.add(u1);
        users.add(u2);

        users.remove(u1);

        assertEquals(1, users.size());
    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // test book object must not be null
    void getUser(){
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("User Object must not be null");

        Users u = null;
    }

}
