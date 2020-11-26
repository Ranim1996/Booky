package service.repository;

import service.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCCountryRepository extends JDBCRepository{

    //get country from data base by code
    public Country getCountryByCode(String countryCode) throws BookyDatabaseException {
        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM country WHERE code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, countryCode); // set country_code parameter
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new BookyDatabaseException("Country with code " + countryCode + " cannot be found");
            } else {
                String name = resultSet.getString("name");
                connection.close();
                return new Country(countryCode, name);
            }
        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read country from the database.",throwable);
        }
    }

    //get all countries from data base
    public Collection<Country> getCountries() throws BookyDatabaseException {
        List<Country> countries = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();
        String sql = "SELECT * FROM country";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                Country country = new Country(code, name);
                countries.add(country);
            }
            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read countries from the database.",throwable);
        }
        return countries;
    }

}
