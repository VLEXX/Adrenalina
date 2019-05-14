package model;

import model.gamedata.InitializeAllPlay;
import model.modelstates.MoveState;
import model.modelstates.WaitingState;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaitingStateTest {

    @Test
    void doAction() {
        WaitingState waitingState = new WaitingState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setPlayer(Player.YELLOW);
        initializeAllPlay.getHashMapState().replace(Player.YELLOW, new MoveState());
        assertEquals(waitingState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
    }
}