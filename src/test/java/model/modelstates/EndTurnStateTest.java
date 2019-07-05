package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.gamedata.Mode;
import model.map.Cell;
import model.map.Position;
import model.map.Room;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.Newton;
import model.powerups.PowerUp;
import model.powerups.TargetingScope;
import model.powerups.Teleporter;
import model.weaponscard.HeatSeeker;
import model.weaponscard.Weapon;
import model.weaponscard.Whisper;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EndTurnStateTest {

    @Test
    void doAction() throws RemoteException {
        //inizializza tutti i parametri necessari
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMode().setSelectedmode(Mode.BASE);
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> testhm = new HashMap<>();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        EndTurnState eds = new EndTurnState(i, testhm, idClientList);
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        DataPacket d = new DataPacket();
        d.setToken(idClientList.addClient());
        i.getCurrentPlayerState().put(Player.BLUE, cps);
        d.setPlayer(Player.BLUE);
        //verifica la refillmap
        assertEquals(MessageEnum.OK, eds.doAction(d));
        for (Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()) {
            for (Cell c : r.getCellsList()) {
                if (c.getSpawnpointzone() == null)
                    assertNotNull(c.getAmmohere());
                else
                    assertEquals(c.getAmmohere(), null);
            }
        }
        //verifica che un player non abbia abbastanza munizioni
        Weapon w1 = new HeatSeeker();
        Weapon w2 = new Whisper();
        PowerUp p1 = new Newton(Munitions.RED);
        PowerUp p2 = new Newton(Munitions.YELLOW);
        PowerUp p3 = new Newton(Munitions.BLUE);
        PowerUp p4 = new Teleporter(Munitions.BLUE);
        PowerUp p5 = new TargetingScope(Munitions.BLUE);
        Weapon w1d = new HeatSeeker();
        Weapon w2d = new Whisper();
        PowerUp p1d = new Newton(Munitions.RED);
        PowerUp p2d = new Newton(Munitions.YELLOW);
        PowerUp p3d = new Newton(Munitions.BLUE);
        PowerUp p4d = new Teleporter(Munitions.BLUE);
        PowerUp p5d = new TargetingScope(Munitions.BLUE);
        d.getWeaponsToBeRecharged().add(w1d);
        d.getWeaponsToBeRecharged().add(w2d);
        d.getPaymentPowerUp().add(p1d);
        d.getPaymentPowerUp().add(p2d);
        d.getPaymentPowerUp().add(p3d);
        d.getPaymentPowerUp().add(p4d);
        d.getPaymentPowerUp().add(p5d);
        cps.getBoard().getWeaponsList().add(w1);
        cps.getBoard().getWeaponsList().add(w2);
        cps.getBoard().getPowerupList().add(p1);
        cps.getBoard().getPowerupList().add(p2);
        cps.getBoard().getPowerupList().add(p3);
        cps.getBoard().getPowerupList().add(p4);
        cps.getBoard().getPowerupList().add(p5);
        assertEquals(eds.doAction(d), MessageEnum.TOO_MUCH_POWERUPS);
        d.getPaymentPowerUp().remove(p4d);
        d.getPaymentPowerUp().remove(p5d);
        i.getStateSelectedMode().setSelectedmode(Mode.DOMINATION);
        i.setLastTurnPlayer(Player.BLUE);
        Position pp = new Position();
        Position pp2 = new Position();
        Position pp3 = new Position();
        Position pp4 = new Position();
        pp.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(2));
        pp.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        pp2.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        pp2.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        pp3.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        pp3.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        pp4.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        pp4.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        cps.setPlayerposition(pp);
        pp.getCurrentcell().addInCellPlayer(Player.BLUE);
        i.setFinalfrenzy(true);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLUE);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.YELLOW);
        pp.getCurrentcell().getSpawnpointzone().getSPDamage().add(Player.BLACK);
        i.getSkullArray()[7]=Player.BLUE;
        assertEquals(eds.doAction(d), MessageEnum.OK);
        i.getStateSelectedMode().setSelectedmode(Mode.BASE);
        i.setFinalfrenzy(false);
        d.getPaymentPowerUp().clear();
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.RED, 2);
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.YELLOW, 2);
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.BLUE, 2);
        assertEquals(eds.doAction(d), MessageEnum.AMMO_ERROR);
        d.getWeaponsToBeRecharged().clear();
        //verifica della classifica
        CurrentPlayerState cps2 = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState cps3 = new CurrentPlayerState(Player.GREEN);
        CurrentPlayerState cps4 = new CurrentPlayerState(Player.BLACK);
        cps2.getBoard().getDamageBox().setDead(true);
        cps2.getBoard().getDamageBox().setDead(true);
        cps2.getBoard().getDamageBox().increaseDamage(12, Player.BLUE);
        cps3.getBoard().getDamageBox().increaseDamage(3, Player.YELLOW);
        cps3.getBoard().getDamageBox().increaseDamage(3, Player.BLACK);
        cps3.getBoard().getDamageBox().increaseDamage(5, Player.BLUE);
        cps4.getBoard().getDamageBox().increaseDamage(2,Player.YELLOW);
        cps4.getBoard().getDamageBox().increaseDamage(4,Player.BLUE);
        cps4.getBoard().getDamageBox().increaseDamage(3,Player.GREEN);
        cps2.setPlayerposition(pp2);
        cps3.setPlayerposition(pp3);
        cps4.setPlayerposition(pp4);
        pp2.getCurrentcell().getInCellPlayer().add(Player.YELLOW);
        pp3.getCurrentcell().getInCellPlayer().add(Player.GREEN);
        pp4.getCurrentcell().getInCellPlayer().add(Player.BLACK);
        i.getCurrentPlayerState().put(Player.YELLOW, cps2);
        i.getCurrentPlayerState().put(Player.GREEN, cps3);
        i.getCurrentPlayerState().put(Player.BLACK, cps4);
        cps.getControlMarks().put(Player.YELLOW, 2);
        cps.getControlMarks().put(Player.GREEN, 3);
        for(int h=0;h<i.getSkullArray().length;h++){
            if(h<3)
                i.getSkullArray()[h]=Player.BLUE;
            else if(h>2&&h<5)
                i.getSkullArray()[h]=Player.YELLOW;
            else
                i.getSkullArray()[h]=Player.GREEN;
        }
        i.getSecondSkullArray()[6]=Player.GREEN;
        assertEquals(eds.doAction(d), MessageEnum.OK);
        d.setToken(0);
        assertEquals(eds.doAction(d), MessageEnum.TOKEN_ERROR);
    }

    @Test
    void refillMap() throws RemoteException {
        InitializeAllPlay i = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum,State> testhm = new HashMap<>();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        EndTurnState eds = new EndTurnState(i,testhm, idClientList);
        eds.refillMap(i);
        for(Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell c : r.getCellsList()){
                if(c.getSpawnpointzone()==null)
                    assertNotNull(c.getAmmohere());
                else
                    assertEquals(c.getAmmohere(), null);
            }
        }
    }

    @Test
    void getNamestate() throws RemoteException {
        EndTurnState endTurnState = new EndTurnState(null, null, null);
        assertEquals(endTurnState.getNamestate(), StatesEnum.END);
    }
}