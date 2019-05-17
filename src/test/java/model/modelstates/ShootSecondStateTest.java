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

class ShootSecondStateTest {

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
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setSecondAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);


        weapon.setLoaded(true);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).resetActionCounter();
        dataPacket.getTargetPlayersSecond().clear();
        dataPacket.getTargetPlayersSecond().add(0, Player.FLAG);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.WEAPON_ERROR);

        Weapon weapon1 = new MachineGun();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).resetActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().clear();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon1);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.WEAPON_NOT_FOUND);


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
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setSecondAttack(true);
        Weapon weapon = new MachineGun();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);

        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        assertEquals(shootSecondState.doAction(dataPacket), MessageEnum.OK);

    }
}