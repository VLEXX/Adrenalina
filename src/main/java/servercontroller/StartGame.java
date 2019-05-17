/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.modelstates.*;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class StartGame extends Thread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private HashMap<StatesEnum, model.modelstates.State> stateHashMap;

    public StartGame(InitializeAllPlay i, Player p, ObjectInputStream oi, ObjectOutputStream oo){
        this.allPlay=i;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = new HashMap<>();
        ActionState actionState = new ActionState(allPlay, stateHashMap);
        EndTurnState endTurnState = new EndTurnState(allPlay, stateHashMap);
        MoveState moveState = new MoveState(allPlay, stateHashMap);
        WaitingState waitingState = new WaitingState(allPlay, stateHashMap);
        ShootFirstState shootFirstState = new ShootFirstState(allPlay, stateHashMap);
        ShootSecondState shootSecondState = new ShootSecondState(allPlay, stateHashMap);
        ShootThirdState shootThirdState = new ShootThirdState(allPlay, stateHashMap);
        PickUpState pickUpState = new PickUpState(allPlay, stateHashMap);
        PowerupState powerupState = new PowerupState(allPlay, stateHashMap);
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
    }

    public synchronized void run(){
        while(true){
            try {
                DataPacket dataPacket = (DataPacket) objectInputStream.readObject();
                MessageEnum messageEnum = allPlay.getPlayerState(player).doAction(dataPacket);
                objectOutputStream.writeObject(messageEnum);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<StatesEnum, model.modelstates.State> getStateHashMap() {
        return stateHashMap;
    }
}
