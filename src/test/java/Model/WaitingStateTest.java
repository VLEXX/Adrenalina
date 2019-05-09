package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaitingStateTest {

    @Test
    void doAction() {
        WaitingState waitingState = new WaitingState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setPlayer(Player.YELLOW);
        initializeAllPlay.getHashMapState().replace(Player.YELLOW, new MoveState());
        assertEquals(waitingState.doAction(dataPacket, initializeAllPlay).getMessage(), "ok");
    }
}