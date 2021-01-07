package service.model;

import java.io.Serializable;

//implements Serializable
public class Chat
{
//    private static final long serialVersionUID = -5998367898242256562L;

    private int id;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chat(int id, String message) {
        this.id = id;
        this.message = message;
    }
}
