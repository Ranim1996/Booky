package service.repository;

import service.model.*;

import java.util.ArrayList;
import java.util.List;

public class UsersFakeDataStore {
    //fields
    private final List<Users> usersList = new ArrayList<>();
    private final List<Language> languagesList = new ArrayList<>();

    //constracture "Some fake data are added here"
    public UsersFakeDataStore() {

        //adding to language list
        Language arabic = new Language("AR", "Arabic");
        Language english = new Language("EN", "English");
        Language french = new Language("FR", "French");

        languagesList.add(arabic);
        languagesList.add(english);
        languagesList.add(french);

        //Users lists for now there are 4 different users
        usersList.add(new Users(1, "Ranim", "Alayoubi", "06/06/1996" , UserType.Admin,
                "ranim@gmail.com","password199"));
        usersList.add(new Users(2, "Obaida", "Bulbul", "29/01/1994" , UserType.Reader,
                "obaida@gmail.com","password123"));
        usersList.add(new Users(3, "Jwana", "Alayoubi", "23/03/2000" , UserType.Reader,
                "jwana@gmail.com","password100"));

    }

    // getter
    public List<Users> getUsers() {
        return usersList;
    }

    //methods

    //get user by language good to practice with filter here
//    public List<Users> getUsers(Language language) {
//        List<Users> filetered = new ArrayList<>();
//        for (Users u : usersList) {
//            if (u.getLanguage_code().equals(language)) {
//                filetered.add(u);
//            }
//        }
//        return filetered;
//    }

    //get user by country good to practice with filter here
//    public List<Users> getUsers(Country country) {
//        List<Users> filetered = new ArrayList<>();
//        for (Users u : usersList) {
//            if (u.getCountry_code().equals(country)) {
//                filetered.add(u);
//            }
//        }
//        return filetered;
//    }

    //get user by his id
    public Users getUser(int id) {
        for (Users u : usersList) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }

    //find whether user is deleted or not for deleteing user with specific id
    public boolean deleteUser(int id) {
        Users u = getUser(id);
        if (u == null){
            return false;
        }
        return usersList.remove(u);
    }

    //add new user
    public boolean addUser(Users u) {
        if (this.getUser(u.getId()) != null){
            return false;
        }
        usersList.add(u);
        return true;
    }

    //find whether user is updated or not for updating user object
    public boolean updatePersonalInfo(int id, Users u) {
        Users old = this.getUser(id);
        if (old == null) {
            return false;
        }
        old.setFirstName(u.getFirstName());
        old.setLastName(u.getLastName());
        old.setDateOfBirth(u.getDateOfBirth());
//        old.setCountry_code(u.getCountry_code());
        old.setEmail(u.getEmail());
        old.setPassword(u.getPassword());
//        old.setPhoneNumber(u.getPhoneNumber());
//        old.setLanguage_code(u.getLanguage_code());
        return true;
    }

    //update the user type by the admin
    public boolean manageUserType(Users u) {
        Users old = this.getUser(1);
        if (old == null) {
            return false;
        }
        old.setUsertype(u.getUsertype());
        return true;
    }

    // get language by its code
    public Language getLanguage(String languageCode) {
        for (Language l : languagesList) {
            if (l.getCode().equals(languageCode)) {
                return l;
            }
        }
        return null;
    }

}

