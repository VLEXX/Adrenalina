package view;

import model.datapacket.MessageEnum;
import org.junit.jupiter.api.Test;

class MessageWriterTest {

    @Test
    void writeMessage() {
        MessageWriter messageWriter = new MessageWriter();
        for(MessageEnum messageEnum: MessageEnum.values()){
            messageWriter.writeMessage(messageEnum);
        }
    }
}