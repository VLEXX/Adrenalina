package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.*;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MarksBox;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import model.powerups.Newton;
import model.powerups.PowerUp;
import model.powerups.TagbackGrenade;
import model.powerups.TargetingScope;
import model.weaponscard.HeatSeeker;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShootFirstStateTest {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }

        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();




        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(true);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==2){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp2() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(true);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==5){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp3() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(true);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==10){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==9){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp4() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(true);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==10){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==7){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp5() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(true);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==10){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);
    }

    @Test
    void shootUp6() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();


        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getDamageBox().setShootUp(false);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==10){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        Cell c = null;
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==9){
                    c=cell;
                }
            }
        }
        dataPacket.setCell(c);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.SHOOTUP_ERROR);
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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new HeatSeeker();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction3() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction4() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new HeatSeeker();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction5() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new HeatSeeker();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, Player.FLAG);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.WEAPON_ERROR);
    }

    @Test
    void doAction6() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        weapon.setLoaded(false);

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.EMPTY_WEAPON);
    }

    @Test
    void doAction7() throws RemoteException {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        dataPacket.setPlayer(Player.YELLOW);
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        Weapon weapon1 = new HeatSeeker();

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon1);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.WEAPON_NOT_FOUND);

        dataPacket.setToken(0);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.TOKEN_ERROR);
    }

    @Test
    void getNamestate() throws RemoteException {
        ShootFirstState shootFirstState = new ShootFirstState(null, null, null);
        assertEquals(shootFirstState.getNamestate(), StatesEnum.SHOOT);
    }

    @Test
    void TestFinalOK() throws RemoteException{
        InitializeAllPlay allPlay = new InitializeAllPlay();
        IDClientList idClientList = new IDClientList();

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(allPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(allPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap, idClientList);
        PowerupState powerupState = new PowerupState(allPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        LockRifle lockRifle = new LockRifle();
        Player myPlayer = Player.BLACK;
        ArrayList<Player> playerToAttack = new ArrayList<>();
        playerToAttack.add(Player.PURPLE);
        CurrentPlayerState myCurrentPlayerState = new CurrentPlayerState(myPlayer);
        CurrentPlayerState attackCurrentPlayerState = new CurrentPlayerState(playerToAttack.get(0));
        Position myPosition = new Position();
        Position positionToAttack = new Position();
        PlayerBoard playerBoard = new PlayerBoard();
        MarksBox marksBox = new MarksBox();
        marksBox.setMyMarksMap(myPlayer, 2);
        playerBoard.setMarksBox(marksBox);
        InitializeMap1 initializeMap1 = new InitializeMap1();
        Map map1 = initializeMap1.initializeMap();
        myPosition.setCurrentroom(map1.getRoomList().get(0));
        myPosition.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(0));
        positionToAttack.setCurrentroom(map1.getRoomList().get(0));
        positionToAttack.setCurrentcell(map1.getRoomList().get(0).getCellsList().get(1));
        positionToAttack.getCurrentcell().addInCellPlayer(playerToAttack.get(0));
        myPosition.getCurrentcell().addInCellPlayer(myPlayer);

        myCurrentPlayerState.getBoard().getWeaponsList().add(lockRifle);

        allPlay.getCurrentPlayerState().put(myPlayer, myCurrentPlayerState);
        allPlay.getCurrentPlayerState().put(playerToAttack.get(0), attackCurrentPlayerState);
        allPlay.getCurrentPlayerState().get(myPlayer).setPlayerposition(myPosition);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setPlayerposition(positionToAttack);
        allPlay.getCurrentPlayerState().get(playerToAttack.get(0)).setBoard(playerBoard);

        DataPacket dataPacket = new DataPacket();

        dataPacket.setPlayer(myPlayer);
        dataPacket.getTargetPlayersFirst().add(playerToAttack.get(0));
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setFirstAttack(true);
        dataPacket.setWeapon(lockRifle);

        PowerUp powerUp = new TargetingScope(Munitions.BLUE);
        myCurrentPlayerState.getBoard().getPowerupList().add(powerUp);
        attackCurrentPlayerState.getBoard().getPowerupList().add(new Newton(Munitions.BLUE));
        attackCurrentPlayerState.getBoard().getPowerupList().add(new TagbackGrenade(Munitions.BLUE));

        dataPacket.setTargetPlayerPowerup(playerToAttack.get(0));

        dataPacket.setMunitions(Munitions.BLUE);

        dataPacket.setPowerUpId(powerUp.getId());
        dataPacket.setPowerUpColor(Munitions.BLUE);

        ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap, idClientList);

        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }
}