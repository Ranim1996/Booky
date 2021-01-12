package service.ControllerPersistance;

import service.model.Chat;
import service.repository.BookyDatabaseException;
import service.repository.JDBCChatRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class DataChatController {

    JDBCChatRepository chatRepository = new JDBCChatRepository();

    /**
     * Add/create a new message.
     * @param text should be inserted into the DB.
     */
    public boolean addMessage(String text) throws BookyDatabaseException, SQLException, URISyntaxException {

        chatRepository.addMessage(text);
        return true;
    }

    public List<Chat> getMessages() throws BookyDatabaseException, SQLException, URISyntaxException {

        List<Chat> chats = chatRepository.getMessages();
        return chats;
    }

}
