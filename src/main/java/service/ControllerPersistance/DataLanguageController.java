package service.ControllerPersistance;

import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLanguageController extends JDBCLanguageRepository {

    JDBCLanguageRepository languagesRepository = new JDBCLanguageRepository();

    //show all languages
    public List<Language> showAllLanguages(){

        List<Language> languages = new ArrayList<>();

        try {
            languages = languagesRepository.getLanguages();
            return languages;
        }
        catch (BookyDatabaseException | SQLException e) {
        }
        return languages;
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
            return null;
        }
    }
}
