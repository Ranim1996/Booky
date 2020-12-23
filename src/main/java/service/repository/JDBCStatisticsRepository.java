package service.repository;

import service.model.BookType;
import service.model.DTO.StatisticsLanguage;
import service.model.DTO.StatisticsType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCStatisticsRepository extends JDBCRepository{

    public List<StatisticsType> getStatisticsPerType() throws BookyDatabaseException, SQLException {

        List<StatisticsType> statisticsTypeArrayList = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT bookType, COUNT(id) AS numberOfBooks FROM book WHERE isDeleted = 0 Group BY bookType";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt("numberOfBooks");
                BookType type =  BookType.valueOf(resultSet.getString("bookType"));

                StatisticsType statisticsType = new StatisticsType(type.name(),count);
                statisticsTypeArrayList.add(statisticsType);
            }

            connection.close();

            return statisticsTypeArrayList;
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot count books from the database.",throwable);
        }
        finally {
            connection.close();
        }
    }

    public List<StatisticsLanguage> getStatisticsPerLanguage() throws BookyDatabaseException, SQLException {

        List<StatisticsLanguage> statisticsLanguageArrayList = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT language_code, COUNT(id) AS numberOfBooks FROM book WHERE isDeleted = 0 Group BY language_code";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int count = resultSet.getInt("numberOfBooks");
                String language = resultSet.getString("language_code");

                StatisticsLanguage statisticsLanguage = new StatisticsLanguage(language,count);
                statisticsLanguageArrayList.add(statisticsLanguage);
            }

            connection.close();

            return statisticsLanguageArrayList;
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot count books from the database.",throwable);
        }
        finally {
            connection.close();
        }
    }
}
