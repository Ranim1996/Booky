package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.ControllerPersistance.DataStatisticsController;
import service.model.BookType;
import service.model.Country;
import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;
import service.repository.BookyDatabaseException;
import service.repository.JDBCStatisticsRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StatisticsDataTest {

    @InjectMocks
    DataStatisticsController statisticsController;

    @Mock
    JDBCStatisticsRepository statisticsRepository;

    @Test
    void ShowStaisticsPerType() throws BookyDatabaseException, SQLException {

        List<StatisticsType> statisticsTypes = new ArrayList<>();
        StatisticsType statisticsType = new StatisticsType(BookType.DetectiveandMystery.name(), 4);

        statisticsTypes.add(statisticsType);

        when(statisticsRepository.getStatisticsPerType()).thenReturn(statisticsTypes);

        List<StatisticsType> statisticsTypeList = statisticsController.staisticsPerType();

        assertEquals(statisticsTypes, statisticsTypeList);
    }

    @Test
    void ShowStatisticsPerLanguage() throws BookyDatabaseException, SQLException {

        List<StatisticsLanguage> statisticsLanguages = new ArrayList<>();
        StatisticsLanguage statisticsLanguage = new StatisticsLanguage("FR", 4);

        statisticsLanguages.add(statisticsLanguage);

        when(statisticsRepository.getStatisticsPerLanguage()).thenReturn(statisticsLanguages);

        List<StatisticsLanguage> statisticsLanguageList = statisticsController.statisticsPerLanguage();

        assertEquals(statisticsLanguages, statisticsLanguageList);
    }
}
