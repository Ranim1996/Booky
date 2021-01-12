package service.ControllerPersistance;

import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;
import service.repository.BookyDatabaseException;
import service.repository.JDBCStatisticsRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataStatisticsController extends JDBCStatisticsRepository {

    JDBCStatisticsRepository statisticsRepository = new JDBCStatisticsRepository();

    /**********************************************Statistics****************************************************/
    /**
     * Show/print how many books are posted for specific type
     */
    //get books statistics per type
    public List<StatisticsType> staisticsPerType() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<StatisticsType> statisticsTypeArrayList = new ArrayList<>();

        statisticsTypeArrayList = statisticsRepository.getStatisticsPerType();
        return statisticsTypeArrayList;
    }

    /**
     * Show/print how many books are posted for specific language
     */
    //get books statistics per language
    public List<StatisticsLanguage> statisticsPerLanguage() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<StatisticsLanguage> statisticsLanguageArrayList = new ArrayList<>();

        statisticsLanguageArrayList = statisticsRepository.getStatisticsPerLanguage();
        return statisticsLanguageArrayList;
    }

}
