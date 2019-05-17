package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.weaponscard.LockRifle;
import model.weaponscard.MachineGun;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShootThirdStateTest {

    @Test
    void doAction() {

        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState2 = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState();
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState2);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        assertEquals(shootThirdState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setThirdAttack(true);
        Weapon weapon = new MachineGun();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootThirdState.doAction(dataPacket), MessageEnum.OK);

        weapon.setLoaded(true);
        assertEquals(shootThirdState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction2() {

        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState2 = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState();
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState2);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);

        dataPacket.setThirdAttack(true);
        dataPacket.setPlayer(Player.YELLOW);
        Weapon weapon = new MachineGun();
        dataPacket.setWeapon(weapon);
        Weapon weapon1 = new LockRifle();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon1);
        assertEquals(shootThirdState.doAction(dataPacket), MessageEnum.WEAPON_NOT_FOUND);


        dataPacket.getTargetPlayersThird().add(0, Player.FLAG);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        assertEquals(shootThirdState.doAction(dataPacket), MessageEnum.WEAPON_ERROR);

    }
}