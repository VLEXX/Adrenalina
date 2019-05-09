package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MidStateTest {

    @Test
    void doAction() {
        MidState midState = new MidState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay).getMessage(), "WRONG INPUT! Please, choose the right action...\n");
    }
}