/**
 * @author Federico Scatà
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * it manages the final frenzy mode activation
 */
public class FinalFrenzyState extends UnicastRemoteObject implements State, Serializable {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;
    private StatesEnum namestate;
    private IDClientList idClientList;

    public FinalFrenzyState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap, IDClientList clientList) throws RemoteException{
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
        this.namestate=StatesEnum.FRENZY;
        this.idClientList=clientList;
    }


    /**
     * it manages the vote to enable frenzy mode (it will be enabled only if player voting yes > player voting no)
     * @param dataPacket it contains client data that are necessary for a state
     * @return a message that depends on the operation result
     * @throws RemoteException
     */
    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        if(!(idClientList.getClientlist().contains(dataPacket.getToken()))){
            return MessageEnum.TOKEN_ERROR;
        }
        if(allPlay.getVoteFrenzy().getNum()==0){
            allPlay.getVoteFrenzy().setNum(idClientList.getPlayerArrayList().size());
        }
        else{
            if(dataPacket.isFrenzy()){
                allPlay.getVoteFrenzy().addYes();
                allPlay.getVoteFrenzy().decreaseNum();
            }
            else{
                allPlay.getVoteFrenzy().addNo();
                allPlay.getVoteFrenzy().decreaseNum();
            }
            if(allPlay.getVoteFrenzy().getNum()==0){
                if(allPlay.getVoteFrenzy().getResult()){
                    for(Player player: idClientList.getPlayerArrayList()) {
                        allPlay.getHashMapState().replace(player, allPlay.getPlayerStateTempFrenzy().get(player));
                    }
                    allPlay.setFinalfrenzy(true);
                }
            }
        }
        return MessageEnum.OK;
    }



    /**
    It returns the state StatesEnum.FRENZY (set by constructor)
     */
    @Override
    public StatesEnum getNamestate() throws RemoteException {
        return this.namestate;
    }
}
