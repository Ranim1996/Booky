package service.ControllerPersistance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.util.Collection;

public class DataLanguageController {

    final Logger logger = LoggerFactory.getLogger(DataLanguageController.class);
    JDBCLanguageRepository languagesRepository = new JDBCLanguageRepository();

    //show all languages
    void showAllLanguages(){

        try {
            Collection<Language> languages = languagesRepository.getLanguages();
            for (Language language : languages) {
                logger.info(language.toString());
            }
        }
        catch (BookyDatabaseException e) {
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
            Language language = languagesRepository.getLanguageByCode(code);
            logger.info(language.toString());
            return language;
        }
        catch (BookyDatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
