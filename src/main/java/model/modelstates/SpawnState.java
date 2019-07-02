package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.powerups.PowerUp;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.HashMap;

public class SpawnState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;
    private IDClientList idClientList;

    public SpawnState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap, IDClientList clientList) throws RemoteException{
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.SPAWN;
        this.idClientList=clientList;
    }

    public StatesEnum getNamestate() throws  RemoteException{
        return namestate;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        if(!(idClientList.getClientlist().contains(dataPacket.getToken()))){
            for(int i: idClientList.getClientlist()){
                System.out.print(i + " ");
            }
            System.out.println("\n" + dataPacket.getToken());
            return MessageEnum.TOKEN_ERROR;
        }
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
