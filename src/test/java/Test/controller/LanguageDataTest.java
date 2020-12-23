package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.ControllerPersistance.DataLanguageController;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LanguageDataTest {

    @InjectMocks
    DataLanguageController languageController;

    @Mock
    JDBCLanguageRepository languageRepository;

    @Test
    void showAllLanguages() throws BookyDatabaseException, SQLException {

        List<Language> languages = new ArrayList<>();
        Language language = new Language("FR","French");

        languages.add(language);

        when(languageRepository.getLanguages()).thenReturn(languages);

        List<Language> languageList = languageController.showAllLanguages();

        assertEquals(languages, languageList);
    }

    @Test
    void getLanguageByCode() throws BookyDatabaseException, SQLException {

        Language language = new Language("FR","French");

        when(languageRepository.getLanguageByCode("FR")).thenReturn(language);

        Language language1 = languageController.showLanguageByCode("FR");

        assertEquals(language, language1);
    }
}
