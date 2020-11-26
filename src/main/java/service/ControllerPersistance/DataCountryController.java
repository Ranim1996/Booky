package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Country;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;


import java.sql.SQLException;
import java.util.Collection;

public class DataCountryController {

    final Logger logger = LoggerFactory.getLogger(DataCountryController.class);
    JDBCCountryRepository countriesRepository = new JDBCCountryRepository();

    //show all countries
    void showAllCountries(){

        try {
            Collection<Country> countries = countriesRepository.getCountries();
            for (Country country : countries) {
                logger.info(country.toString());
            }
        }
        catch (BookyDatabaseException | SQLException e) {
            e.printStackTrace();
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
            logger.info(country.toString());
        }
        catch (BookyDatabaseException | SQLException e) {
            e.printStackTrace();
        }
    }

}
