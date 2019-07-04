package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.munitions.Ammo;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.Newton;
import model.powerups.PowerUp;
import model.weaponscard.*;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PickUpStateTest {

    @Test
    void doAction() throws RemoteException {
       InitializeAllPlay i = new InitializeAllPlay();
       HashMap<StatesEnum,State> hm = new HashMap();
       i.getStateSelectedMap().setStrategyMap(0);
       i.getStateSelectedMap().setSelectedmap();
       DataPacket d = new DataPacket();
       IDClientList idClientList = new IDClientList();
       d.setToken(idClientList.addClient());
       PickUpState pus= new PickUpState(i,hm,idClientList);
       d.setPlayer(Player.BLUE);
       CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
       cps.getPlayerposition().setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
       cps.getPlayerposition().setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
       d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(3).getCellsList().get(3));
       d.setWeaponlistempty(true);
       assertEquals(pus.doAction(d),MessageEnum.OK);
       d.setWeaponlistempty(false);
       i.getCurrentPlayerState().put(Player.BLUE,cps);
       Weapon rw= new HeatSeeker();
       d.setReplaceWeapon(rw);
       assertEquals(pus.doAction(d),MessageEnum.WEAPON_ERROR_2);
       d.setReplaceWeapon(null);
       PowerUp pw1 = new Newton(Munitions.RED);
       d.getPaymentPowerUp().add(pw1);
       assertEquals(pus.doAction(d),MessageEnum.POWERUP_NOT_FOUND);
       d.getPaymentPowerUp().remove(pw1);
       assertEquals(pus.doAction(d),MessageEnum.UNREACHABLE_CELL);
       cps.getBoard().getDamageBox().getDamage()[2]=Player.YELLOW;
       assertEquals(pus.doAction(d),MessageEnum.UNREACHABLE_CELL);
       d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(2).getCellsList().get(0));
       Weapon w1 = new Whisper();
       d.setWeapon(w1);
       for(int z=0;z<3;z++)
           i.getStateSelectedMap().getSelectedmap().getRoomList().get(2).getCellsList().get(0).getSpawnpointzone().getSpawnWeaponsList()[z]=rw;
       assertEquals(pus.doAction(d),MessageEnum.WEAPON_NOT_FOUND);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(2).getCellsList().get(0).getSpawnpointzone().getSpawnWeaponsList()[2]=w1;
        d.getPaymentPowerUp().add(pw1);
        cps.getBoard().getPowerupList().add(pw1);
        assertEquals(pus.doAction(d),MessageEnum.TOO_MUCH_POWERUPS);
        d.getPaymentPowerUp().remove(pw1);
        cps.getBoard().getPowerupList().remove(pw1);
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.RED,3);
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.YELLOW,3);
        cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.BLUE,3);
        assertEquals(pus.doAction(d),MessageEnum.AMMO_ERROR);
        Weapon w2 = new Thor();
        Weapon w2a = new Thor();
        d.setWeapon(w2a);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(2).getCellsList().get(0).getSpawnpointzone().getSpawnWeaponsList()[2]=w2;
        assertEquals(pus.doAction(d),MessageEnum.AMMO_ERROR);
        d.setReplaceWeapon(rw);
        cps.getBoard().getWeaponsList().add(rw);
        cps.getBoard().getWeaponsList().add(w1);
        Weapon w3 = new PlasmaGun();
        cps.getBoard().getWeaponsList().add(w3);
        cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.RED,3);
        cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.YELLOW,3);
        cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.BLUE,3);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        assertTrue(cps.getBoard().getWeaponsList().contains(w2));
        d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        Ammo a = new Ammo();
        d.setReplaceWeapon(null);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setAmmohere(a);
        for(int k=0;k<3;k++)
            a.getAmmoList()[k]=2;
        a.setPossiblePowerUp(true);
        d.setWeapon(null);
        cps.getBoard().getMunitionsBox().getMyMunitionsMap().replace(Munitions.RED,0);
        cps.getBoard().getMunitionsBox().getMyMunitionsMap().replace(Munitions.YELLOW,0);
        cps.getBoard().getMunitionsBox().getMyMunitionsMap().replace(Munitions.BLUE,0);
        i.getCurrentPlayerState().get(d.getPlayer()).resetActionCounter();
        assertEquals(pus.doAction(d),MessageEnum.OK);
        assertEquals(i.getCurrentPlayerState().get(d.getPlayer()).getPlayerposition().getCurrentcell().getAmmohere(),null);
        d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1).setAmmohere(a);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        assertEquals(i.getCurrentPlayerState().get(d.getPlayer()).getPlayerposition().getCurrentcell().getAmmohere(),null);
        d.setToken(0);
        assertEquals(pus.doAction(d), MessageEnum.TOKEN_ERROR);
    }

    @Test
    void getNamestate() throws RemoteException {
        PickUpState pickUpState = new PickUpState(null, null, null);
        assertEquals(pickUpState.getNamestate(), StatesEnum.PICK_UP);
    }
}