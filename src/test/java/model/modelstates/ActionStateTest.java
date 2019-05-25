package model.modelstates;

import model.gamedata.InitializeAllPlay;
import model.modelstates.ActionState;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.modelstates.State;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionStateTest {

    @Test
    void doAction() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, hashMap);
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(actionState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(actionState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(actionState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(actionState.doAction(dataPacket), MessageEnum.OK);
        dataPacket.setStatesEnum(StatesEnum.ACTION);
        assertEquals(actionState.doAction(dataPacket), MessageEnum.ACTION_ERROR);

    }
}