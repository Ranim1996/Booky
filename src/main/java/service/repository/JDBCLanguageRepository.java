package service.repository;

import service.model.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCLanguageRepository extends JDBCRepository{

    //get language from data base by code
    public Language getLanguageByCode(String languageCode) throws BookyDatabaseException, SQLException {

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM language WHERE code = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        try {
            statement.setString(1, languageCode); // set language_code parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("Language with code " + languageCode + " cannot be found");
            } else {
                String name = resultSet.getString("name");
                connection.close();
                return new Language(languageCode, name);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read Language from the database.",throwable);
        }finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }

    //get all languages from data base
    public Collection<Language> getLanguages() throws BookyDatabaseException, SQLException {

        List<Language> languages = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM language";

        Statement statement = connection.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                Language language = new Language(code, name);
                languages.add(language);
            }
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read languages from the database.",throwable);
        }finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
        return languages;
    }
}
