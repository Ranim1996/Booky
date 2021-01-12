package service.repository;

import org.glassfish.jersey.jaxb.internal.XmlRootElementJaxbProvider;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCRepository{

    public Connection getDataBaseConneection () throws URISyntaxException{

        String url = "";
        String username = "";
        String pass = "";
        java.sql.Connection connection = null;

        try{
            Properties properties = new Properties();
            properties.load(XmlRootElementJaxbProvider.App.class.getClassLoader().getResourceAsStream("app.properties"));

            url = properties.getProperty("host");
            username = properties.getProperty("username");
            pass = properties.getProperty("pass");

            System.out.println(url);
            System.out.println(username);
            System.out.println(pass);


            connection = DriverManager.getConnection(url, username, pass);
            connection.setAutoCommit(false);

            System.out.println(connection);

        } catch (SQLException | FileNotFoundException e) {
            throw new IllegalStateException("Driver failed " + url + ".", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
