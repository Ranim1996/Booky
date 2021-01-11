package service.repository;

import com.mysql.jdbc.Connection;

import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCRepository{

    protected Connection getDataBaseConneection () {

        String url = "jdbc:mysql://studmysql01.fhict.local:3306/dbi344446";

        String username = "dbi344446";
        String pass = "1996";

        try {
            Connection connection = (Connection) DriverManager.getConnection(url, username, pass);
            connection.setAutoCommit(false);
            return connection;
        }
        catch (SQLException e){
            throw new IllegalStateException("JDBC driver failed to connect to the server "
            + url + e);
        }

    }
}
