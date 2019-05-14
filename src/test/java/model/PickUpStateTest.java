package model;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.map.SpawnPoint;
import model.modelstates.PickUpState;
import model.munitions.Ammo;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import model.weaponscard.*;
import org.junit.jupiter.api.Test;

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
        Weapon w2 = new Furnace();
        Weapon w3 = new Thor();
        Weapon w4 = new ShotGun();
        d.setReplaceWeapon(w2);
        d.setWeapon(w1);
        Ammo a = new Ammo();
        int[] array = {1,2,1};
        a.setAmmoList(array);
        a.setPossiblePowerUp(true);
        i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).setAmmohere(a);
        SpawnPoint sp= new SpawnPoint();
        sp.addWeapon(w1,0);
        sp.addWeapon(w2,1);
        sp.addWeapon(w2,2);
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        cps.getPlayerposition().setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        cps.setActiveturn(true);
        PlayerBoard pb = new PlayerBoard();
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.RED,1);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW,2);
        pb.getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE,1);
        cps.setBoard(pb);
        i.getCurrentPlayerState().put(Player.BLUE,cps);
        PickUpState pus = new PickUpState();
        assertEquals(pus.doAction(d,i), MessageEnum.OK);
    }
}