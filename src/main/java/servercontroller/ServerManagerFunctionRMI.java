/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.MessageEnum;
import model.datapacket.MessageString;
import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ServerManagerFunctionRMI extends UnicastRemoteObject implements Remote, ServerManagerFunctionInterfaceRMI {

    private InitializeAllPlay allPlay;
    private IDClientList idClientList;

    public ServerManagerFunctionRMI(InitializeAllPlay initializeAllPlay, IDClientList clientList) throws RemoteException{
        this.allPlay=initializeAllPlay;
        this.idClientList=clientList;
    }

    public synchronized MessageEnum chooseCharacterManager(Player player) throws IOException, RemoteException {
        while(true) {
            if (allPlay.getCurrentDeckState().getPlayers().contains(player)) {
                allPlay.getCurrentPlayerState().put(player, new CurrentPlayerState(player));
                allPlay.getCurrentDeckState().getPlayers().remove(player);
                idClientList.getPlayerArrayList().add(player);
                allPlay.decreasePlayerCounterTemp();
                break;
            }
            else{
                return MessageEnum.PLAYER_NOT_FOUND;
            }
        }
        return MessageEnum.OK;
    }

    public synchronized MessageEnum manageVoteMap(int i) throws IOException, RemoteException{

        if(i==1){
            allPlay.getVoteMap().setVoteresult(0);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
                refillMap(allPlay);
            }
            return MessageEnum.OK;
        }
        if(i==2){
            allPlay.getVoteMap().setVoteresult(1);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
                refillMap(allPlay);
            }
            return MessageEnum.OK;
        }
        if(i==3){
            allPlay.getVoteMap().setVoteresult(2);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
                refillMap(allPlay);
            }
            return MessageEnum.OK;
        }
        if(i==4){
            allPlay.getVoteMap().setVoteresult(3);
            allPlay.getVoteMap().decreasePlayerCounter();
            if(allPlay.getVoteMap().getPlayerCounter()==0){
                allPlay.getVoteMap().setInitmap();
                allPlay.getVoteMap().setFinalresult();
                allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
                allPlay.getStateSelectedMap().setSelectedmap();
                refillMap(allPlay);
            }
            return MessageEnum.OK;
        }
        else{
            return MessageEnum.ERROR;
        }
    }

    public void refillMap( InitializeAllPlay i) throws RemoteException {
        for(Room r : i.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell c : r.getCellsList()){
                if(c.getSpawnpointzone()==null){
                    if(c.getAmmohere()==null)
                        c.setAmmohere(i.getCurrentDeckState().getAmmodeck().pop());
                }
                else{
                    for(int j=0;j<3;j++){
                        if(c.getSpawnpointzone().getSpawnWeaponsList()[j]==null) {
                            if(!i.getCurrentDeckState().getWeaponsdeck().empty())
                                c.getSpawnpointzone().getSpawnWeaponsList()[j] = i.getCurrentDeckState().getWeaponsdeck().pop();
                        }
                    }
                }
            }
        }
    }

    public void manageNickname(String nickname) throws RemoteException{
        idClientList.getNicknameList().add(nickname);
    }

    public void manageNickPlayer(String nickname, Player player)throws RemoteException{
        idClientList.getNickPlayer().put(nickname, player);
    }

    public boolean isInArrayNick(String nick) throws RemoteException{
        if(idClientList.getNicknameList().contains(nick)){
            return true;
        }
        else{
            return false;
        }
    }
}
