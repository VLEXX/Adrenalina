package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getMessage() {
        Message message = new Message("ok");
        assertEquals(message.getMessage(), "ok");
    }
}