package Test.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.ControllerPersistance.DataChatController;
import service.model.Chat;
import service.repository.BookyDatabaseException;
import service.repository.JDBCChatRepository;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChatDataTest {

    @InjectMocks
    DataChatController chatController;

    @Mock
    JDBCChatRepository chatRepository;

    @Test
    void AddMessage() throws BookyDatabaseException, SQLException, URISyntaxException {
        when(chatRepository.addMessage("hi")).thenReturn(true);

        boolean expectedMessage = chatController.addMessage("hi");

        assertEquals(true, expectedMessage);

    }

    @Test
    void GetMessages() throws BookyDatabaseException, SQLException, URISyntaxException {
        List<Chat> chatList = new ArrayList<>();
        
        Chat chat1 = new Chat(1,"hi");
        Chat chat2 = new Chat(2, "hello");

        chatList.add(chat1);
        chatList.add(chat2);

        when(chatRepository.getMessages()).thenReturn(chatList);

        List<Chat> chats = chatController.getMessages();

        assertEquals(chatList.size(), chats.size());
    }
}
