package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StartStateTest {

    @Test
    void doAction() {
        StartState startState = new StartState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay).getMessage(), "WRONG INPUT! Please, choose the right action...\n");

    }
}