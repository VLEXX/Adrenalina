package model;

import model.gamedata.InitializeAllPlay;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.modelstates.ActionState;
import model.modelstates.State;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MidStateTest {

    @Test
    void doAction() {
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        ActionState midState = new ActionState(initializeAllPlay, hashMap);
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(midState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(midState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(midState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(midState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.ACTION);
        assertEquals(midState.doAction(dataPacket), MessageEnum.ACTION_ERROR);
    }
}