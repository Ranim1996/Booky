package service.ControllerPersistance;

import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataLanguageController extends JDBCLanguageRepository {

    JDBCLanguageRepository languagesRepository = new JDBCLanguageRepository();

    //show all languages
    public List<Language> showAllLanguages() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Language> languages = new ArrayList<>();

        languages = languagesRepository.getLanguages();
        return languages;
    }

    /**
     * Show/print the language with given code
     * @param code of the language to be shown.
     */
    //show language by it's code
    public Language showLanguageByCode(String code) throws BookyDatabaseException, SQLException, URISyntaxException {

        return languagesRepository.getLanguageByCode(code);
    }
}
