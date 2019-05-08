package Model;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;

    public Message(String string){
        this.message=string;
    }

    public String getMessage() {
        return message;
    }
}
