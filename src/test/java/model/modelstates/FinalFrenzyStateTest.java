package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FinalFrenzyStateTest {

    @Test
    void doAction() throws RemoteException {
            InitializeAllPlay i = new InitializeAllPlay();
        HashMap<StatesEnum,State> mn = new HashMap<>();
        IDClientList asd = new IDClientList();
        FinalFrenzyState ffs = new FinalFrenzyState(i,mn,asd);
        DataPacket d = new DataPacket();
        assertEquals(ffs.doAction(d), MessageEnum.TOKEN_ERROR);
        d.setToken(5);
        asd.getClientlist().add(5);
        asd.getPlayerArrayList().add(Player.BLUE);
        asd.getPlayerArrayList().add(Player.YELLOW);
        asd.getPlayerArrayList().add(Player.BLACK);
        assertEquals(ffs.doAction(d),MessageEnum.OK);
        assertEquals(i.getVoteFrenzy().getNum(),3);
        d.setFrenzy(true);
        assertEquals(ffs.doAction(d),MessageEnum.OK);
        d.setFrenzy(false);
        assertEquals(ffs.doAction(d),MessageEnum.OK);
        d.setFrenzy(true);
        PickUpState ps = new PickUpState(i,mn,asd);
        i.getHashMapState().put(Player.BLACK, ps);
        i.getHashMapState().put(Player.BLUE,ps);
        i.getHashMapState().put(Player.YELLOW,ps);
        i.getPlayerStateTempFrenzy().put(Player.YELLOW,ps);
        i.getPlayerStateTempFrenzy().put(Player.BLUE,ps);
        i.getPlayerStateTempFrenzy().put(Player.BLACK,ps);
        assertEquals(ffs.doAction(d),MessageEnum.OK);
        asd.getPlayerArrayList().add(Player.BLUE);
        asd.getPlayerArrayList().add(Player.YELLOW);
        asd.getPlayerArrayList().add(Player.BLACK);
        d.setFrenzy(false);
        ffs.doAction(d);
        d.setFrenzy(false);
        ffs.doAction(d);
        d.setFrenzy(false);
        assertEquals(ffs.doAction(d),MessageEnum.OK);



    }

    @Test
    void getNamestate() throws RemoteException {
        InitializeAllPlay i = new InitializeAllPlay();
        HashMap<StatesEnum,State> mn = new HashMap<>();
        IDClientList asd = new IDClientList();
        FinalFrenzyState ffs = new FinalFrenzyState(i,mn,asd);
        assertEquals(ffs.getNamestate(),StatesEnum.FRENZY);
    }
}