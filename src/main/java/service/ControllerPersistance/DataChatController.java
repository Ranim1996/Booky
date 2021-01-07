package service.ControllerPersistance;

import service.model.Chat;
import service.repository.BookyDatabaseException;
import service.repository.JDBCChatRepository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataChatController {

    JDBCChatRepository chatRepository = new JDBCChatRepository();

    /**
     * Add/create a new message.
     * @param text should be inserted into the DB.
     */
    public boolean addMessage(String text) {

        try {
            if(chatRepository.addMessage(text)) {

                return true;
            }
            else
            {
                return false;
            }
        } catch (SQLException | BookyDatabaseException e) {
            return false;
        }
    }

    public List<Chat> getMessages(){

        try {
            List<Chat> chats = chatRepository.getMessages();
            return chats;
        }
        catch (BookyDatabaseException | SQLException e) {
            return Collections.emptyList();
        }
    }

}
