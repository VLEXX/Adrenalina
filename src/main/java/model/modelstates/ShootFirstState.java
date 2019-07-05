package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.map.Cell;
import model.map.Room;
import model.map.SpawnPoint;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ShootFirstState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;
    private IDClientList idClientList;

    public ShootFirstState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap, IDClientList clientList) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.SHOOT;
        this.idClientList=clientList;
    }

    public StatesEnum getNamestate() throws RemoteException {
        return namestate;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException{
        if(!(idClientList.getClientlist().contains(dataPacket.getToken()))){
            return MessageEnum.TOKEN_ERROR;
        }
        if(dataPacket.getSpawnPointToAttack()!=null){
            System.out.println("ciao spawn");
            int i = 0;
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    i=1;
                    Weapon weapon1 = w;
                    if(weapon1.getLoaded()){
                        SpawnPoint spawnPoint=null;
                        for(Room room: allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
                            for(Cell cell: room.getCellsList()){
                                if(cell.getSpawnpointzone()!=null){
                                    if(cell.getSpawnpointzone().getSpawnColor().equals(dataPacket.getSpawnPointToAttack().getSpawnColor())){
                                        spawnPoint=cell.getSpawnpointzone();
                                    }
                                }
                            }
                        }
                        MessageEnum messageEnum = weapon1.firstAttack(dataPacket.getPlayer(), spawnPoint, this.allPlay);
                        if(messageEnum.equals(MessageEnum.OK)){
                            if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter()==1){
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                            }
                            if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter()==2){
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                            }
                        }
                        return messageEnum;
                    }
                    else{
                        return MessageEnum.EMPTY_WEAPON;
                    }
                }
            }
            if(i==0){
                return MessageEnum.WEAPON_NOT_FOUND;
            }
        }
        Weapon weapon;
        Cell celltemp=allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell();
        Cell cellnull = null;
        if(dataPacket.isFirstAttack()){
            System.out.println("isFirst");
            if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getDamageBox().isShootUp()){
                if(dataPacket.getCell()!=null){
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
                    if(weapon.getLoaded()){
                        MessageEnum messageEnum = weapon.firstAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersFirst(), dataPacket.getPosition(), this.allPlay);
                        if(messageEnum.equals(MessageEnum.OK)){
                            weapon.setLoaded(false);
                            if(dataPacket.getPowerUpId()!=null){
                                PowerUp powerUp1=null;
                                int k =0;
                                for(PowerUp powerUp: allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()){
                                    if(powerUp.getId().equals(dataPacket.getPowerUpId())){
                                        if(powerUp.getColor().equals(dataPacket.getPowerUpColor())){
                                            if(allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(dataPacket.getMunitions())>=1){
                                                int n = allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().get(dataPacket.getMunitions());
                                                n = n -1;
                                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap().replace(dataPacket.getMunitions(), n);
                                                allPlay.getCurrentPlayerState().get(dataPacket.getTargetPlayerPowerup()).getBoard().getDamageBox().increaseDamage(1,dataPacket.getPlayer());
                                                powerUp1 = powerUp;
                                                k=1;
                                            }
                                        }
                                    }
                                }
                                if(k ==1){
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().remove(powerUp1);
                                }
                            }
                            for(Player player: dataPacket.getTargetPlayersFirst()){
                                if(!player.equals(Player.FLAG)){
                                    allPlay.getCurrentPlayerState().get(player).setAttackinprogress(true);
                                    for (PowerUp powerUp : allPlay.getCurrentPlayerState().get(player).getBoard().getPowerupList()) {
                                        if (powerUp.getId().equals(PowerUpId.TAGBACK_GRENADE)) {
                                            allPlay.getHashMapState().replace(player, stateHashMap.get(StatesEnum.POWERUP));
                                        }
                                    }
                                }
                            }
                            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                                if(weapon.hasSecondAttack()) {
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_SECOND));
                                    return messageEnum;
                                }
                                else{
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                                    return messageEnum;
                                }
                            }
                            else if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                                if(weapon.hasSecondAttack()) {
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_SECOND));
                                    return messageEnum;
                                }
                                else{
                                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                    allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
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
        return MessageEnum.ERROR;
    }
}
