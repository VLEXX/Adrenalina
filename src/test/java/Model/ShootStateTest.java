package Model;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static org.junit.jupiter.api.Assertions.*;

class ShootStateTest {

    @Test
    void doAction() {
        ShootState shootState = new ShootState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        shootState.doAction(dataPacket, initializeAllPlay);
    }
}