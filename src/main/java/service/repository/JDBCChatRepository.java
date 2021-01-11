package service.repository;

import service.model.*;

import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCChatRepository extends JDBCRepository{

    public boolean addMessage(String text) throws BookyDatabaseException, SQLException, URISyntaxException {

        MD5Hash md = new MD5Hash();

        Connection connection = this.getDataBaseConneection();

        String sql = "INSERT INTO chat ( message) VALUES (?) ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, text);

            preparedStatement.executeUpdate();
            connection.commit();
            connection.close();

            return true;

        } catch (SQLException throwable) {
            throw  new BookyDatabaseException("Cannot add.", throwable);
        }finally {
            connection.close();
        }

    }

    //get all messages from data base
    public List<Chat> getMessages() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Chat> chats = new ArrayList<>();

        Connection connection = this.getDataBaseConneection();

        String sql = "SELECT * FROM chat";

        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String text = resultSet.getString("message");

                Chat chat = new Chat(id,text);
                chats.add(chat);
            }

            connection.close();

        } catch (SQLException throwable) {
            throw new BookyDatabaseException("Cannot read messages from the database.",throwable);
        }finally {
            connection.close();
        }
        return chats;
    }
}
