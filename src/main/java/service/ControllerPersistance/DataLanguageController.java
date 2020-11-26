package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.sql.SQLException;
import java.util.Collection;

public class DataLanguageController {

    JDBCLanguageRepository languagesRepository = new JDBCLanguageRepository();

    //show all languages
    void showAllLanguages(){

        try {
            Collection<Language> languages = languagesRepository.getLanguages();
        }
        catch (BookyDatabaseException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show/print the language with given code
     * @param code of the language to be shown.
     */
    //show language by it's code
    public Language showLanguageByCode(String code){

        try {
            return languagesRepository.getLanguageByCode(code);
        }
        catch (BookyDatabaseException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
