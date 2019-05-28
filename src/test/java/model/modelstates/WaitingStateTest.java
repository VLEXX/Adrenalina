package model.modelstates;

import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.modelstates.MoveState;
import model.modelstates.State;
import model.modelstates.WaitingState;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaitingStateTest {

    @Test
    void doAction() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        WaitingState waitingState = new WaitingState(initializeAllPlay, hashMap, idClientList);
        dataPacket.setPlayer(Player.YELLOW);
        initializeAllPlay.getHashMapState().replace(Player.YELLOW, new MoveState(initializeAllPlay, hashMap, idClientList));
        assertEquals(waitingState.doAction(dataPacket), MessageEnum.OK);
    }
}