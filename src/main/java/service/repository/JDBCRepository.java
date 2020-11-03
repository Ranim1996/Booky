package service.repository;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRepository{

    protected Connection getDataBaseConneection () throws BookyDatabaseException   {

        String url = "jdbc:mysql://studmysql01.fhict.local:3306/dbi344446";

        String username = "dbi344446";
        String password = "1996";

        try {
            Connection connection = (Connection) DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return connection;
        }
        catch (SQLException e){
            throw new IllegalStateException("JDBC driver failed to connect to the server "
            + url + e);
        }

    }
}
