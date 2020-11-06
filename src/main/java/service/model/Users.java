package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
@XmlRootElement
public class Users {
    //fields
    private int id;
    private String firstName; // user's first name
    private String lastName; // user's last name
    private UserType usertype; // type of the user
    private String email; // user's email
    private String password; // user's password
    private String phoneNumber; // user's phone number
    private Country country_code; // user's country
    private Language language_code; // user's language
    private String dateOfBirth; // user's date of birth

//    private String image;
    private static int idSeeder = 1;


    //constractures
    public Users(int id, String firstName, String lastName, String dateOfBirth, UserType type,
                 String email, String password,
                 String phoneNumbar, Country country, Language language) {
        this.id = id;
//        idSeeder++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.usertype = type;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumbar;
        this.language_code = language;
        this.country_code = country;
    }

    public Users() {

    }

    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUsertype() {
        return usertype;
    }
    public void setUsertype(UserType usertype) {
        this.usertype = usertype;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Language getLanguage_code() {
        return language_code;
    }
    public void setLanguage_code(Language language_code) {
        this.language_code = language_code;
    }

    public Country getCountry_code() {
        return country_code;
    }
    public void setCountry_code(Country country_code) {
        this.country_code = country_code;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users u = (Users) o;
        return id == u.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "User ID: " + id +
                ", First Name: '" + firstName + '\'' +
                ", Last Name: '" +  lastName + '\'' +
                ", Date of Birth: '" + dateOfBirth + '\'' +
                ", Email: '" + email + '\'' +
                ", Password: '" + password + '\'' +
                ", Phone Number: " + phoneNumber + '\'' +
                ", User Type: " + usertype + '\'' +
                ", Country: " + country_code + '\'' +
                ", Language: " + language_code + '\'' +
                '}';
    }
}
