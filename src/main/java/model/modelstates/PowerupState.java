package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class PowerupState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    /**
     * Class constructor
     */
    public PowerupState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if(dataPacket.isPowerupAction()==true) {
            if (dataPacket.getPowerUpId().equals(PowerUpId.TAGBACK_GRENADE)) {
                return doTagbackGrenade(dataPacket);
            }
            if (dataPacket.getPowerUpId().equals(PowerUpId.NEWTON)) {
                return doNewton(dataPacket);
            }
            if (dataPacket.getPowerUpId().equals(PowerUpId.TARGETING_SCOPE)) {
                return doTargetingScope(dataPacket);
            }
            if (dataPacket.getPowerUpId().equals(PowerUpId.TELEPORTER)) {
                return doTeleporter(dataPacket);
            }
        }
        return MessageEnum.OK;
    }

    private MessageEnum doTagbackGrenade(DataPacket dataPacket){
        if(dataPacket.getMarksToAdd().isEmpty()==false) {
            for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
                if (powerUp.getId().equals(PowerUpId.TAGBACK_GRENADE)) {
                    if(allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().containsKey(dataPacket.getPlayer())) {
                        Integer before = allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().get(dataPacket.getPlayer());
                        allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().replace(dataPacket.getPlayer(),before+1);
                        return MessageEnum.OK;
                    }
                    else{
                        allPlay.getCurrentPlayerState().get(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getHit()).getControlMarks().put(dataPacket.getPlayer(),1);
                        return MessageEnum.OK;
                    }
                }
            }
            return MessageEnum.POWERUP_NOT_FOUND;
        }
        return MessageEnum.OK;
    }

    private ArrayList<Integer> createArrayCell(Cell c){
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(c.getUpCell()!=null){
            arrayList.add(c.getUpCell().getCellId());
            if(c.getUpCell().getUpCell()!=null){
                arrayList.add(c.getUpCell().getUpCell().getCellId());
            }
        }
        if(c.getDownCell()!=null){
            arrayList.add(c.getDownCell().getCellId());
            if(c.getDownCell().getDownCell()!=null){
                arrayList.add(c.getDownCell().getDownCell().getCellId());
            }
        }
        if(c.getRightCell()!=null){
            arrayList.add(c.getRightCell().getCellId());
            if(c.getRightCell().getRightCell()!=null){
                arrayList.add(c.getRightCell().getRightCell().getCellId());
            }
        }
        if(c.getLeftCell()!=null){
            arrayList.add(c.getLeftCell().getCellId());
            if(c.getLeftCell().getLeftCell()!=null){
                arrayList.add(c.getLeftCell().getLeftCell().getCellId());
            }
        }
        return arrayList;
    }

    private MessageEnum doNewton(DataPacket dataPacket){

        for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
            if (powerUp.getId().equals(PowerUpId.NEWTON)) {
                Cell c = allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getPlayerposition().getCurrentcell();
                ArrayList<Integer> arrayList = createArrayCell(c);
                if(arrayList.contains(dataPacket.getCell().getCellId())){
                    for(Room room: allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
                        for(Cell cell: room.getCellsList()){
                            if(dataPacket.getCell().getCellId()==cell.getCellId()){
                                allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getPlayerposition().getCurrentcell().removeInCellPlayer(dataPacket.getTargetPlayerPowerup());
                                allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getPlayerposition().setCurrentcell(cell);
                                allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getPlayerposition().setCurrentroom(room);
                                cell.addInCellPlayer(dataPacket.getTargetPlayerPowerup());
                                return MessageEnum.OK;
                            }
                        }
                    }
                }
                return MessageEnum.UNREACHABLE_CELL;
            }
        }
        return MessageEnum.POWERUP_NOT_FOUND;
    }

    private MessageEnum doTargetingScope(DataPacket dataPacket){

        for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
            if (powerUp.getId().equals(PowerUpId.TARGETING_SCOPE)) {
                int i = allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(dataPacket.getMunitions()) - 1;
                if(i>=0){
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().replace(dataPacket.getMunitions(), i);
                    allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getBoard().getDamageBox().increaseDamage(1, dataPacket.getPlayer());
                    return MessageEnum.OK;
                }
                else{
                    return MessageEnum.ENOUGH_AMMO;
                }
            }
        }
        return MessageEnum.POWERUP_NOT_FOUND;
    }

    private MessageEnum doTeleporter(DataPacket dataPacket){

        for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
            if (powerUp.getId().equals(PowerUpId.TELEPORTER)) {
                for(Room room: allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
                    for(Cell cell: room.getCellsList()){
                        if(dataPacket.getCell().getCellId()==cell.getCellId()){
                            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell().removeInCellPlayer(dataPacket.getPlayer());
                            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(cell);
                            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
                            cell.addInCellPlayer(dataPacket.getPlayer());
                            return MessageEnum.OK;
                        }
                    }
                }
                return MessageEnum.INEXISTENT_CELL;
            }
        }
        return MessageEnum.POWERUP_NOT_FOUND;
    }
}
