package model;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.gamedata.InitializeAllPlay;
import model.modelstates.ShootFirstState;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ShootFirstStateTest {

    @Test
    void doAction() {

        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
        /*
        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).setPlayerposition(null);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLACK);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
        */
        //TODO
    }
}