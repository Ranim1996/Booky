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
    public Language getLanguageByCode(String languageCode) throws BookyDatabaseException {
        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM language WHERE code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
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
        }
    }

    //get all languages from data base
    public Collection<Language> getLanguages() throws BookyDatabaseException {
        List<Language> countries = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM language";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                Language language = new Language(code, name);
                countries.add(language);
            }

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read languages from the database.",throwable);
        }
        return countries;
    }
}
