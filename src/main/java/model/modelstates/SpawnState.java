package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.powerups.PowerUp;

import java.util.HashMap;

public class SpawnState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public SpawnState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        PowerUp pop1 = allPlay.getCurrentDeckState().getPowerupdeck().pop();
        PowerUp pop2 = allPlay.getCurrentDeckState().getPowerupdeck().pop();
        if(pop1.getId().equals(dataPacket.getPowerUpToKeepSpawn().getId())){
            if(pop2.getId().equals(dataPacket.getPowerUpSpawn().getId())) {
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

        if(pop2.getId().equals(dataPacket.getPowerUpToKeepSpawn().getId())){
            if(pop1.getId().equals(dataPacket.getPowerUpSpawn().getId())) {
                for (Room room : allPlay.getStateSelectedMap().getSelectedmap().getRoomList()) {
                    for (Cell cell : room.getCellsList()) {
                        if(cell.getSpawnpointzone()!=null) {
                            if (cell.getSpawnpointzone().getSpawnColor().equals(pop1.getColor())) {
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                cell.addInCellPlayer(dataPacket.getPlayer());
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().add(pop2);
                                allPlay.getCurrentDeckState().getPowerupdeck().push(pop1);
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
