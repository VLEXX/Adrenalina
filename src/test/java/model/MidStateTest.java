package model;

import model.gamedata.InitializeAllPlay;
import model.modelstates.MidState;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MidStateTest {

    @Test
    void doAction() {
        MidState midState = new MidState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(midState.doAction(dataPacket, initializeAllPlay), MessageEnum.ACTION_ERROR);
    }
}