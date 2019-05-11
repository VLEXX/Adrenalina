package Model;

import org.junit.jupiter.api.Test;

class ShootStateTest {

    @Test
    void doAction() {
        ShootState shootState = new ShootState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        shootState.doAction(dataPacket, initializeAllPlay);
    }
}