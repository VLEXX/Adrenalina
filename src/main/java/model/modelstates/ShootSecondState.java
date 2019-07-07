package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ShootSecondState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;
    private IDClientList idClientList;

    public ShootSecondState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap, IDClientList clientList) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.SHOOT_SECOND;
        this.idClientList= clientList;
    }

    public StatesEnum getNamestate() throws RemoteException{
        return namestate;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        if(!(idClientList.getClientlist().contains(dataPacket.getToken()))){
            return MessageEnum.TOKEN_ERROR;
        }
        Weapon weapon;
        if(dataPacket.isSecondAttack()){
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())){
                    weapon = w;
                    MessageEnum messageEnum = weapon.secondAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersSecond(), dataPacket.getPosition(), allPlay);
                    if(messageEnum.equals(MessageEnum.OK)){
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                            if(weapon.hasThirdAttack()){
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.SHOOT_THIRD));
                                return messageEnum;
                            }
                            else{
                                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                                return messageEnum;
                            }
                        }
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                            if(weapon.hasThirdAttack()) {
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
            }
            return MessageEnum.WEAPON_NOT_FOUND;
        }
        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
        }
        else if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
        }
        return MessageEnum.OK;
    }
}
