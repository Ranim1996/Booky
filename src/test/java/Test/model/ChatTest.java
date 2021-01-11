package Test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import service.model.Book;
import service.model.Chat;

public class ChatTest {

    @Test
    void NewChat(){

        Chat chat = new Chat(1,"hi");

        assertEquals(1, chat.getId());
        assertEquals("hi", chat.getMessage());

    }

    @Rule // this rule is added to throw exceptions when its needed
    ExpectedException thrown = ExpectedException.none();

    @Test // book name null
    void getMessageWithNullValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Message must not be null");

        Chat chat = new Chat();
        chat.setMessage(null);
    }

    @Test // book name string is empty
    void getMessageWithEmptyValue() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Message must not be empty");

        Chat chat = new Chat();
        chat.setMessage("");
    }
}
