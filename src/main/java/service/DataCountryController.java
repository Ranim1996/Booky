package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Country;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCCountryRepository;
import service.repository.JDBCLanguageRepository;

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

    /**
     * Show/print the country with given code
     * @param code of the country to be shown.
     */
    //show country by it's code
    void showCountryByCode(String code){
        JDBCCountryRepository countriesRepository = new JDBCCountryRepository();

        try {
            Country country = countriesRepository.getCountryByCode("USA");
            logger.info(country.toString());
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
        }
    }

}
