package model;

import model.gamedata.InitializeAllPlay;
import model.modelstates.StartState;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StartStateTest {

    @Test
    void doAction() {
        StartState startState = new StartState();
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(startState.doAction(dataPacket, initializeAllPlay), MessageEnum.ACTION_ERROR);

    }
}