package Test.repository;

import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.model.Language;
import service.repository.BookyDatabaseException;
import service.repository.JDBCLanguageRepository;
import service.repository.JDBCRepository;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.glassfish.jersey.message.internal.ReaderWriter.UTF8;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class LanguageRepositoryTest {

    @InjectMocks
    JDBCLanguageRepository languageRepository;

    @Mock
    JDBCRepository jdbcRepository;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException, URISyntaxException {
        Class.forName ("org.h2.Driver");

        lenient().when(jdbcRepository.getDataBaseConneection()).thenReturn(
                DriverManager.getConnection("jdbc:h2:mem:~/test"));

        RunScript.execute("jdbc:h2:mem:~/test", "", "", "classpath:data.sql", UTF8, false);
    }

    @Test
    void getLanguageByCode() throws BookyDatabaseException, SQLException, URISyntaxException {
        Language language1 = new Language("AR", "Arabic");
        Language language = languageRepository.getLanguageByCode("AR");

        assertEquals(language1,language);
    }

    @Test
    void getLanguages() throws BookyDatabaseException, SQLException, URISyntaxException {
        List<Language>languages = languageRepository.getLanguages();

        assertEquals(2,languages.size());
    }
}
