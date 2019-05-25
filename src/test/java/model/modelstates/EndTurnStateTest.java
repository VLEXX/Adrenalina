package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Position;
import model.map.Room;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
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
        HashMap<StatesEnum, State> testhm = new HashMap<>();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        EndTurnState eds = new EndTurnState(i, testhm);
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        DataPacket d = new DataPacket();
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
        assertEquals(eds.doAction(d), MessageEnum.OK);
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
        i.getCurrentPlayerState().put(Player.YELLOW, cps2);
        i.getCurrentPlayerState().put(Player.GREEN, cps3);
        i.getCurrentPlayerState().put(Player.BLACK, cps4);
        cps.getControlMarks().put(Player.YELLOW, 2);
        cps.getControlMarks().put(Player.GREEN, 3);
        assertEquals(eds.doAction(d), MessageEnum.OK);


    }

    @Test
    void refillMap() throws RemoteException {
        InitializeAllPlay i = new InitializeAllPlay();
        HashMap<StatesEnum,State> testhm = new HashMap<>();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        EndTurnState eds = new EndTurnState(i,testhm);
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

}