package model.datapacket;

import model.datapacket.MessageString;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageStringTest {

    @Test
    void getMessage() {
        MessageString message = new MessageString("ok");
        assertEquals(message.getMessage(), "ok");
    }

}