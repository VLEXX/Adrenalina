package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class UpdateThread {
    private InitializeAllPlay allPlay;
    private Player player;
    private ObjectOutputStream objectOutputStream;

    public UpdateThread(InitializeAllPlay i, Player p, ObjectOutputStream o){
        this.allPlay=i;
        this.player=p;
        this.objectOutputStream=o;
    }


    public synchronized void updateClient() throws IOException {

        Position position = new Position();

        if(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell()!=null) {
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
        UpdatePacket updatePacket = new UpdatePacket(allPlay.getChartScore(), allPlay.getCurrentPlayerState().get(player), allPlay.getStateSelectedMap().getSelectedmap(), position, state, allPlay.getCurrentDeckState().getPowerupdeck(), allPlay.isEndgame());
        objectOutputStream.writeObject(updatePacket);
    }
}
