package service.resources;

import service.ControllerPersistance.DataChatController;
import service.ControllerPersistance.DataLanguageController;
import service.model.Book;
import service.model.BookType;
import service.model.Chat;
import service.model.Language;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("chat")
public class ChatResources {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response getChat() {

        DataChatController chatController = new DataChatController();

        List<Chat> chats;
        chats = chatController.getMessages();

        GenericEntity<List<Chat>> entity = new GenericEntity<>(chats) {  };
        return Response.ok(entity).build();
    }


}
