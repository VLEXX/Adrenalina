package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.powerups.PowerUp;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;

public class SpawnState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public SpawnState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        PowerUp pop1 = allPlay.getCurrentDeckState().getPowerupdeck().pop();
        PowerUp pop2 = allPlay.getCurrentDeckState().getPowerupdeck().pop();
        if((pop1.getId().equals(dataPacket.getPowerUpToKeepSpawn().getId())) &&(pop1.getColor().equals(dataPacket.getPowerUpToKeepSpawn().getColor()))){
            if((pop2.getId().equals(dataPacket.getPowerUpSpawn().getId())) && (pop2.getColor().equals(dataPacket.getPowerUpSpawn().getColor()))) {
                for (Room room : allPlay.getStateSelectedMap().getSelectedmap().getRoomList()) {
                    for (Cell cell : room.getCellsList()) {
                        if(cell.getSpawnpointzone()!=null) {
                            if (cell.getSpawnpointzone().getSpawnColor().equals(pop2.getColor())) {
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                cell.addInCellPlayer(dataPacket.getPlayer());
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().add(pop1);
                                allPlay.getCurrentDeckState().getPowerupdeck().push(pop2);
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                                return MessageEnum.OK;
                            }
                        }
                    }
                }
            }
        }

        if((pop2.getId().equals(dataPacket.getPowerUpToKeepSpawn().getId()))&&(pop2.getColor().equals(dataPacket.getPowerUpToKeepSpawn().getColor()))){
            if((pop1.getId().equals(dataPacket.getPowerUpSpawn().getId()))&&(pop1.getColor().equals(dataPacket.getPowerUpSpawn().getColor()))){
                for (Room room : allPlay.getStateSelectedMap().getSelectedmap().getRoomList()) {
                    for (Cell cell : room.getCellsList()) {
                        if(cell.getSpawnpointzone()!=null) {
                            if (cell.getSpawnpointzone().getSpawnColor().equals(pop1.getColor())) {
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                cell.addInCellPlayer(dataPacket.getPlayer());
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().add(pop2);
                                allPlay.getCurrentDeckState().getPowerupdeck().push(pop1);
                                Collections.shuffle(allPlay.getCurrentDeckState().getPowerupdeck());
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                                return MessageEnum.OK;
                            }
                        }
                    }
                }
            }
        }
        return MessageEnum.POWERUP_NOT_FOUND;
    }
}
