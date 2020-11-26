package service.ControllerPersistance;

import service.model.Country;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;


import java.sql.SQLException;
import java.util.Collection;

public class DataCountryController {

    JDBCCountryRepository countriesRepository = new JDBCCountryRepository();

    //show all countries
    void showAllCountries(){

        try {
            Collection<Country> countries = countriesRepository.getCountries();
        }
        catch (BookyDatabaseException | SQLException e) {
        }
    }

    /**
     * Show/print the country with given code
     * @param code of the country to be shown.
     */
    //show country by it's code
    void showCountryByCode(String code){

        try {
            Country country = countriesRepository.getCountryByCode("USA");
        }
        catch (BookyDatabaseException | SQLException e) {
        }
    }

}
