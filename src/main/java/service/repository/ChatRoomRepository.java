package service.repository;

import org.glassfish.grizzly.websockets.WebSocket;
import org.glassfish.grizzly.websockets.WebSocketApplication;
import service.ControllerPersistance.DataChatController;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChatRoomRepository extends WebSocketApplication {

    DataChatController chatController = new DataChatController();
    private static final Set<WebSocket> sockets = Collections.synchronizedSet(new HashSet<>());

    @Override
    public void onConnect(WebSocket socket) {
        sockets.add(socket);
        System.out.println("onConnect");
        super.onConnect(socket);
    }

    @Override
    public void onMessage(WebSocket current, String text) {

        System.out.println("onMessage");

        synchronized (sockets) {
            sockets.forEach(socket -> {
                if (socket.isConnected()) {
                    socket.send(text);
                }
            });
        }

        try {
            chatController.addMessage(text);
        } catch (BookyDatabaseException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(WebSocket socket, byte[] bytes) {
        socket.send(bytes);
    }
}
