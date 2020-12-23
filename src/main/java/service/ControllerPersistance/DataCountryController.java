package service.ControllerPersistance;

import service.model.Country;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataCountryController {

    JDBCCountryRepository countriesRepository = new JDBCCountryRepository();

    //show all countries
    public List<Country> showAllCountries(){

        List<Country> countries = new ArrayList<>();

        try {
            countries = countriesRepository.getCountries();
            return countries;
        }
        catch (BookyDatabaseException | SQLException e) {
        }
        return countries;
    }

    /**
     * Show/print the country with given code
     * @param code of the country to be shown.
     */
    //show country by it's code
    public Country showCountryByCode(String code){

        Country country = new Country();

        try {
            country = countriesRepository.getCountryByCode("USA");
            return country;
        }
        catch (BookyDatabaseException | SQLException e) {
        }
        return country;
    }

}
