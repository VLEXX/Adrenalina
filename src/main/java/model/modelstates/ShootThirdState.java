package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.weaponscard.Weapon;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class ShootThirdState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;

    public ShootThirdState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap) throws RemoteException {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.SHOOT_THIRD;
    }

    public StatesEnum getNamestate() throws RemoteException {
        return namestate;
    }

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {

        Weapon weapon;
        if(dataPacket.isThirdAttack()==true){
            for (Weapon w : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (dataPacket.getWeapon().getName().equals(w.getName())) {
                    weapon = w;
                    MessageEnum messageEnum = weapon.thirdAttack(dataPacket.getPlayer(), dataPacket.getTargetPlayersThird(), allPlay);
                    if(messageEnum.equals(MessageEnum.OK)){
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                            return messageEnum;
                        }
                        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                            return messageEnum;
                        }
                    }
                    return messageEnum;
                }
            }
            return MessageEnum.WEAPON_NOT_FOUND;
        }
        return MessageEnum.OK;
    }
}
