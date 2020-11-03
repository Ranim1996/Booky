package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Country;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;

import java.util.Collection;

public class DataCountryController {

    final Logger logger = LoggerFactory.getLogger(DataCountryController.class);

    //show all countries
    void showAllCountries(){
        JDBCCountryRepository countriesRepository = new JDBCCountryRepository();

        try {
            Collection<Country> countries = countriesRepository.getCountries();
            for (Country country : countries) {
                logger.info(country.toString());
            }
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

}
