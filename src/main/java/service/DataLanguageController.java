package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.util.Collection;

public class DataLanguageController {

    final Logger logger = LoggerFactory.getLogger(DataLanguageController.class);

    //show all languages
    void showAllLanguages(){
        JDBCLanguageRepository languagesRepository = new JDBCLanguageRepository();

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
}
