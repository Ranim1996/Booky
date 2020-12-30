package service.ControllerPersistance;

import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;
import service.repository.BookyDatabaseException;
import service.repository.JDBCStatisticsRepository;

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
    public List<StatisticsType> staisticsPerType(){

        List<StatisticsType> statisticsTypeArrayList = new ArrayList<>();

        try {
            statisticsTypeArrayList = statisticsRepository.getStatisticsPerType();
            System.out.println("total controller= "+ statisticsTypeArrayList);
            return statisticsTypeArrayList;
        }
        catch (BookyDatabaseException | SQLException e) {
        }
        return null;
    }

    /**
     * Show/print how many books are posted for specific language
     */
    //get books statistics per language
    public List<StatisticsLanguage> statisticsPerLanguage(){

        List<StatisticsLanguage> statisticsLanguageArrayList = new ArrayList<>();

        try {
            statisticsLanguageArrayList = statisticsRepository.getStatisticsPerLanguage();
            System.out.println("total controller= "+ statisticsLanguageArrayList);
            return statisticsLanguageArrayList;
        }
        catch (BookyDatabaseException | SQLException e) {
        }
        return null;
    }

}
