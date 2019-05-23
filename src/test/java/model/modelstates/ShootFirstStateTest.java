package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.weaponscard.HeatSeeker;
import model.weaponscard.LockRifle;
import model.weaponscard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ShootFirstStateTest {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();




        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp2() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp3() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp4() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void shootUp5() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);
    }

    @Test
    void shootUp6() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);

        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
        dataPacket.getTargetPlayersFirst().add(0, null);
        weapon.setLoaded(true);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.SHOOTUP_ERROR);
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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new HeatSeeker();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction3() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction4() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new HeatSeeker();
        weapon.setLoaded(true);
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).decreaseActionCounter();
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doAction5() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);
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
    void doAction6() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

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
    void doAction7() {

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
        ShootSecondState shootSecondState = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
        ShootFirstState shootFirstState = new ShootFirstState(initializeAllPlay, stateHashMap);

        dataPacket.setFirstAttack(true);
        Weapon weapon = new LockRifle();
        Weapon weapon1 = new HeatSeeker();

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getWeaponsList().add(weapon1);
        dataPacket.setWeapon(weapon);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.getTargetPlayersFirst().add(0, null);
        assertEquals(shootFirstState.doAction(dataPacket), MessageEnum.WEAPON_NOT_FOUND);
    }
}