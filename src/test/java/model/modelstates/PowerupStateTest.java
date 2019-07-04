package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.*;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PowerupStateTest {

    @Test
    void doActionTagbackGrenade() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPowerupAction(true);
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);

        dataPacket.setPowerUpId(PowerUpId.TAGBACK_GRENADE);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);

        dataPacket.setPlayer(Player.BLUE);
        dataPacket.getMarksToAdd().put(Player.YELLOW, 1);

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);

        PowerUp powerUp = new TagbackGrenade(Munitions.RED);
        PowerUp powerUp1 = new TargetingScope(Munitions.RED);
        initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getBoard().getPowerupList().add(powerUp1);
        initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getBoard().getPowerupList().add(powerUp);
        initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).setHit(Player.YELLOW);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doActionNewton() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPowerupAction(true);
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        dataPacket.setPowerUpId(PowerUpId.NEWTON);
        dataPacket.setPowerUpColor(Munitions.RED);
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);

        initializeAllPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().add(new TagbackGrenade(Munitions.RED));
        initializeAllPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().add(new Newton(Munitions.RED));
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();
        dataPacket.setTargetPlayerPowerup(Player.BLUE);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==11){
                    dataPacket.setCell(cell);
                }
            }
        }

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.PLAYER_NOT_VALID);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==2){
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentcell(cell);
                    cell.addInCellPlayer(Player.BLUE);
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==1){
                    dataPacket.setCell(cell);
                }
            }
        }



        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==11){
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentcell(cell);
                    cell.addInCellPlayer(Player.BLUE);
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==5){
                    dataPacket.setCell(cell);
                }
            }
        }

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==3){
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentcell(cell);
                    cell.addInCellPlayer(Player.BLUE);
                    initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getPlayerposition().setCurrentroom(room);
                }
            }
        }
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.UNREACHABLE_CELL);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==4){
                    dataPacket.setCell(cell);
                }
            }
        }
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doActionTeleporter() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        dataPacket.setPowerupAction(true);
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap, idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpId(PowerUpId.TELEPORTER);
        dataPacket.setPowerUpColor(Munitions.RED);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getPowerupList().add(new TagbackGrenade(Munitions.RED));
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getPowerupList().add(new Teleporter(Munitions.RED));
        initializeAllPlay.getStateSelectedMap().setStrategyMap(1);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==3){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    cell.addInCellPlayer(Player.YELLOW);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }

        Cell c = new Cell(13);
        dataPacket.setCell(c);

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.INEXISTENT_CELL);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==5){
                    dataPacket.setCell(cell);
                }
            }
        }

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doActionOK() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);
        initializeAllPlay.getStateSelectedMap().setStrategyMap(0);
        initializeAllPlay.getStateSelectedMap().setSelectedmap();

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap,idClientList);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setPowerUpColor(Munitions.BLUE);
        dataPacket.setPowerUpId(PowerUpId.TELEPORTER);
        PowerUp powerUp = new PowerUp(PowerUpId.TELEPORTER, Munitions.BLUE);
        currentPlayerState.getBoard().getPowerupList().add(powerUp);

        for(Room room: initializeAllPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell cell: room.getCellsList()){
                if(cell.getCellId()==3){
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentcell(cell);
                    cell.addInCellPlayer(Player.YELLOW);
                    initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getPlayerposition().setCurrentroom(room);
                }
            }
        }

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
    }

    @Test
    void doActionTergetingScope() throws RemoteException {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPowerupAction(true);
        IDClientList idClientList = new IDClientList();
        dataPacket.setToken(idClientList.addClient());
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap,idClientList );
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap, idClientList);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap, idClientList);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap, idClientList);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap, idClientList);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap, idClientList);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap, idClientList);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap, idClientList);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap, idClientList);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap, idClientList);

        dataPacket.setPowerUpId(PowerUpId.TARGETING_SCOPE);
        dataPacket.setPlayer(Player.YELLOW);
        dataPacket.setTargetPlayerPowerup(Player.BLUE);
        dataPacket.setMunitions(Munitions.BLUE);

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.POWERUP_NOT_FOUND);

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getPowerupList().add(new TagbackGrenade(Munitions.RED));
        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getPowerupList().add(new TargetingScope(Munitions.RED));

        assertEquals(powerupState.doAction(dataPacket), MessageEnum.OK);
        assertEquals(initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE), 2);
        assertEquals(initializeAllPlay.getCurrentPlayerState().get(Player.BLUE).getBoard().getDamageBox().getDamage()[0], Player.YELLOW);

        initializeAllPlay.getCurrentPlayerState().get(Player.YELLOW).getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.BLUE, 2);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.ENOUGH_AMMO);

        dataPacket.setToken(0);
        assertEquals(powerupState.doAction(dataPacket), MessageEnum.TOKEN_ERROR);
    }

    @Test
    void getNamestate() throws RemoteException {
        PowerupState powerupState = new PowerupState(null, null, null);
        assertEquals(powerupState.getNamestate(), StatesEnum.POWERUP);
    }
}