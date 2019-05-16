package model;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.map.SpawnPoint;
import model.modelstates.PickUpState;
import model.modelstates.State;
import model.munitions.Ammo;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import model.weaponscard.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PickUpStateTest {

    @Test
    void doAction() {
        DataPacket d = new DataPacket();
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        d.setPlayer(Player.BLUE);
        d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        Weapon w1 = new Cyberblade();
        Weapon w1b = new MachineGun();
        Weapon w2 = new Furnace();
        Weapon w3 = new Thor();
        Weapon w4 = new ShotGun();
        PowerUp p1 = new PowerUp(PowerUpId.NEWTON,Munitions.RED);
        PowerUp p2 = new PowerUp(PowerUpId.NEWTON,Munitions.YELLOW);
        PowerUp p3 = new PowerUp(PowerUpId.NEWTON,Munitions.BLUE);
        PowerUp p4 = new PowerUp(PowerUpId.TAGBACK_GRENADE,Munitions.BLUE);
        d.getPaymentPowerUp().add(p1);
        d.getPaymentPowerUp().add(p2);
        d.setReplaceWeapon(w2);
        d.setWeapon(w1);
        Ammo a = new Ammo();
        int[] array = {1,1,1};
        a.setAmmoList(array);
        a.setPossiblePowerUp(true);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setAmmohere(a);
        SpawnPoint sp= new SpawnPoint();
        sp.addWeapon(w1,0);
        sp.addWeapon(w1b,1);
        sp.addWeapon(w2,2);
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        cps.getPlayerposition().setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        cps.setActiveturn(false);
        PlayerBoard pb = new PlayerBoard();
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.RED,0);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW,0);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE,0);
        cps.setBoard(pb);
        i.getCurrentPlayerState().put(Player.BLUE,cps);
        HashMap<StatesEnum, State> hashMap = new HashMap<>();
        PickUpState pus = new PickUpState(i, hashMap);
        assertEquals(pus.doAction(d),MessageEnum.NOT_YOUR_TURN);
        cps.setActiveturn(true);
        pb.getWeaponsList().add(w2);
        d.getPaymentPowerUp().add(p1);
        d.getPaymentPowerUp().add(p2);
        assertEquals(pus.doAction(d),MessageEnum.WEAPON_ERROR_2);
        pb.getWeaponsList().add(w3);
        pb.getWeaponsList().add(w4);
        d.getPaymentPowerUp().add(p4);
        assertEquals(pus.doAction(d),MessageEnum.POWERUP_NOT_FOUND);
        d.getPaymentPowerUp().remove(p4);
        d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        array[0]=3;
        array[1]=3;
        array[2]=3;
        a.setAmmoList(array);
        a.setPossiblePowerUp(true);
        pb.getPowerupList().add(p1);
        pb.getPowerupList().add(p2);

        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setAmmohere(a);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        pb.getPowerupList().add(p3);
        d.getPaymentPowerUp().add(p3);
        d.setCell(null);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setAmmohere(a);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setSpawnpointzone(sp);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        d.setWeapon(w1b);
        d.getPaymentPowerUp().add(p3);
        d.setReplaceWeapon(null);
        assertEquals(pus.doAction(d),MessageEnum.OK);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.RED,0);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW,0);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE,0);
        d.getPaymentPowerUp().clear();
        assertEquals(pus.doAction(d),MessageEnum.WEAPON_ERROR);
        d.setCell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(4).getCellsList().get(0));

        assertEquals(pus.doAction(d),MessageEnum.UNREACHABLE_CELL);
    }
}