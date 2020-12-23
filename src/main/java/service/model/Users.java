package service.model;

import javax.xml.bind.annotation.XmlRootElement;
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
    private String dateOfBirth; // user's date of birth

//    private String image;


    //constractures
    public Users(int id, String firstName, String lastName, String dateOfBirth, UserType type,
                 String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.usertype = type;
        this.email = email;
        this.password = password;
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
                ", User Type: " + usertype + '\'' +
                '}';
    }
}
