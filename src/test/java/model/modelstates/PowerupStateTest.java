package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.TagbackGrenade;
import model.powerups.TargetingScope;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class PowerupStateTest {

    @Test
    void doAction() {
        DataPacket dataPacket = new DataPacket();
        InitializeAllPlay initializeAllPlay = new InitializeAllPlay();
        CurrentPlayerState currentPlayerState = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState currentPlayerState2 = new CurrentPlayerState(Player.BLUE);
        initializeAllPlay.getCurrentPlayerState().put(Player.YELLOW, currentPlayerState);
        initializeAllPlay.getCurrentPlayerState().put(Player.BLUE, currentPlayerState2);

        HashMap<StatesEnum, State> stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(initializeAllPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(initializeAllPlay, stateHashMap);
        MoveState moveState = new MoveState(initializeAllPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(initializeAllPlay, stateHashMap);
        ShootFirstState shootFirstState2 = new ShootFirstState(initializeAllPlay, stateHashMap);
        ShootSecondState shootSecondState2 = new ShootSecondState(initializeAllPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(initializeAllPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(initializeAllPlay, stateHashMap);
        PowerupState powerupState1 = new PowerupState(initializeAllPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState2);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState2);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState1);

        PowerupState powerupState = new PowerupState(initializeAllPlay, stateHashMap);
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
}