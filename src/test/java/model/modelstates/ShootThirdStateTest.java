package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.weaponscard.LockRifle;
import model.weaponscard.MachineGun;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ShootThirdStateTest {

    @Test
    void doAction() throws RemoteException {

        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState2 = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState2);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
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
    void doAction2() throws RemoteException {

        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState2 = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState2);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);

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