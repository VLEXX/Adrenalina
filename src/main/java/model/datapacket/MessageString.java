package model.datapacket;

import java.io.Serializable;

public class MessageString implements Serializable {
    private String message;

    public MessageString(String s) {
        this.message = s;
    }

    public String getMessage() {
        return message;
    }
}
