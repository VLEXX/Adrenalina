package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;
import model.powerups.PowerUp;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Stack;

public class Updater extends UnicastRemoteObject implements UpdaterInterface {
    private InitializeAllPlay allPlay;

    public Updater(InitializeAllPlay i) throws RemoteException{
        this.allPlay=i;
    }


    public synchronized UpdatePacket updateClient(Player player) throws IOException, RemoteException {

        Position position = new Position();
        if (allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell() != null) {
            position.setCurrentcell(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell());
            position.setCurrentroom(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentroom());
        }

        StatesEnum state = null;

        if(allPlay.getHashMapState().get(player) instanceof ActionState){
            state = StatesEnum.ACTION;
        }
        if(allPlay.getHashMapState().get(player) instanceof ShootFirstState){
            state = StatesEnum.SHOOT;
        }
        if(allPlay.getHashMapState().get(player) instanceof ShootSecondState){
            state = StatesEnum.SHOOT_SECOND;
        }
        if(allPlay.getHashMapState().get(player) instanceof ShootThirdState){
            state = StatesEnum.SHOOT_THIRD;
        }
        if(allPlay.getHashMapState().get(player) instanceof PickUpState){
            state = StatesEnum.PICK_UP;
        }
        if(allPlay.getHashMapState().get(player) instanceof MoveState){
            state = StatesEnum.MOVE;
        }
        if(allPlay.getHashMapState().get(player) instanceof EndTurnState){
            state = StatesEnum.END;
        }
        if(allPlay.getHashMapState().get(player) instanceof WaitingState){
            state = StatesEnum.WAIT;
        }
        if(allPlay.getHashMapState().get(player) instanceof PowerupState){
            state = StatesEnum.POWERUP;
        }
        if(allPlay.getHashMapState().get(player) instanceof SpawnState){
            state = StatesEnum.SPAWN;
        }

        Stack<PowerUp> deck = (Stack<PowerUp>) allPlay.getCurrentDeckState().getPowerupdeck().clone();
        UpdatePacket updatePacket = new UpdatePacket(allPlay.getChartScore(), allPlay.getCurrentPlayerState().get(player), allPlay.getStateSelectedMap().getSelectedmap(), position, state, deck, allPlay.isEndgame());
        return updatePacket;
    }
}
