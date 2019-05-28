package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.map.Cell;
import model.map.Room;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ShootFirstState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;

    public ShootFirstState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.SHOOT;
    }

    public StatesEnum getNamestate() throws RemoteException {
        return namestate;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {

        Weapon weapon;
        Cell celltemp=allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell();
        Cell cellnull = null;
        if(dataPacket.isFirstAttack()==true){
            if(dataPacket.getCell()!=null){
                if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getDamageBox().isShootUp()==true){
                    for(Room room: allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
                        for(Cell cell: room.getCellsList()){
                            if(dataPacket.getCell().getCellId()==cell.getCellId()) {
                                if (celltemp.getUpCell() == cell) {
                                    celltemp.removeInCellPlayer(dataPacket.getPlayer());
                                    cell.addInCellPlayer(dataPacket.getPlayer());
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                    cellnull=cell;
                                    break;
                                }
                                else if (celltemp.getDownCell() == cell) {
                                    celltemp.removeInCellPlayer(dataPacket.getPlayer());
                                    cell.addInCellPlayer(dataPacket.getPlayer());
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                    cellnull=cell;
                                    break;
                                }
                                else if(celltemp.getRightCell()==cell){
                                    celltemp.removeInCellPlayer(dataPacket.getPlayer());
                                    cell.addInCellPlayer(dataPacket.getPlayer());
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                    cellnull=cell;
                                    break;
                                }
                                else if(celltemp.getLeftCell()==cell){
                                    celltemp.removeInCellPlayer(dataPacket.getPlayer());
                                    cell.addInCellPlayer(dataPacket.getPlayer());
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                                    cellnull=cell;
                                    break;
                                }
                            }
                        }
                        if(cellnull!=null){
                            break;
                        }
                    }
                    if(cellnull==null){
                        return MessageEnum.UNREACHABLE_CELL;
                    }
                }
                else{
                    return MessageEnum.SHOOTUP_ERROR;
                }
            }
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    if(weapon.getLoaded()==true){
                        MessageEnum messageEnum = weapon.firstAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersFirst(), dataPacket.getPosition(), allPlay);
                        if(messageEnum.equals(MessageEnum.OK)){
                            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                                if(weapon.hasSecondAttack()==true) {
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_SECOND));
                                    return messageEnum;
                                }
                                else{
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                                    return messageEnum;
                                }
                            }
                            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                                if(weapon.hasSecondAttack()==true) {
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_SECOND));
                                    return messageEnum;
                                }
                                else{
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                                    return messageEnum;
                                }
                            }
                        }
                        return messageEnum;
                    }
                    else{
                        return MessageEnum.EMPTY_WEAPON;
                    }
                }
            }
            return MessageEnum.WEAPON_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
