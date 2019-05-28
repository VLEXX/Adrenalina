package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.TagbackGrenade;
import model.powerups.TargetingScope;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SpawnStateTest {

    @Test
    void doAction() throws RemoteException {

        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        SpawnState spawnState = new SpawnState(initializeAllPlay, stateHashMap, idClientList);

        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        Stack<PowerUp> stack;
        stack = initializeAllPlay.getCurrentDeckState().getPowerupdeck();

        PowerUp powerUp1 = stack.pop();
        PowerUp powerUp2 = stack.pop();

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpSpawn(powerUp1);
        dataPacket.setPowerUpToKeepSpawn(powerUp2);
        stack.push(powerUp2);
        stack.push(powerUp1);
        assertEquals(spawnState.doAction(dataPacket), MessageEnum.OK);

    }

    @Test
    void doAction2() throws RemoteException {

        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        SpawnState spawnState = new SpawnState(initializeAllPlay, stateHashMap, idClientList);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        PowerUp power1 = new TargetingScope(Munitions.RED);
        PowerUp power2 = new TagbackGrenade(Munitions.YELLOW);
        initializeAllPlay.getCurrentDeckState().getPowerupdeck().push(power2);
        initializeAllPlay.getCurrentDeckState().getPowerupdeck().push(power1);
        Stack<PowerUp> stack;
        stack = initializeAllPlay.getCurrentDeckState().getPowerupdeck();

        PowerUp powerUp1 = stack.pop();
        PowerUp powerUp2 = stack.pop();

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpSpawn(powerUp1);
        dataPacket.setPowerUpToKeepSpawn(powerUp2);
        stack.push(powerUp2);
        stack.push(powerUp1);
        assertEquals(spawnState.doAction(dataPacket), MessageEnum.OK);

    }

    @Test
    void doAction3() throws RemoteException {

        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        SpawnState spawnState = new SpawnState(initializeAllPlay, stateHashMap, idClientList);

        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        Stack<PowerUp> stack;
        stack = initializeAllPlay.getCurrentDeckState().getPowerupdeck();

        PowerUp powerUp1 = stack.pop();
        PowerUp powerUp2 = stack.pop();
        PowerUp powerUp3 = stack.pop();

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpSpawn(powerUp1);
        dataPacket.setPowerUpToKeepSpawn(powerUp3);
        stack.push(powerUp3);
        stack.push(powerUp2);
        stack.push(powerUp1);
        assertEquals(spawnState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);
    }

    @Test
    void doAction4() throws RemoteException {

        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();
        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveStatein = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveStatein);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        SpawnState spawnState = new SpawnState(initializeAllPlay, stateHashMap, idClientList);

        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        PowerUp power1 = new TargetingScope(Munitions.RED);
        PowerUp power2 = new TagbackGrenade(Munitions.YELLOW);
        initializeAllPlay.getCurrentDeckState().getPowerupdeck().push(power1);
        initializeAllPlay.getCurrentDeckState().getPowerupdeck().push(power2);
        Stack<PowerUp> stack;
        stack = initializeAllPlay.getCurrentDeckState().getPowerupdeck();

        PowerUp powerUp1 = stack.pop();
        PowerUp powerUp2 = stack.pop();

        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpSpawn(powerUp2);
        dataPacket.setPowerUpToKeepSpawn(powerUp1);
        stack.push(powerUp2);
        stack.push(powerUp1);
        assertEquals(spawnState.doAction(dataPacket), MessageEnum.OK);

    }
}