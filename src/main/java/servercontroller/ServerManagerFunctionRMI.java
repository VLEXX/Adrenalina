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
            return MessageEnum.OK;
        }
        if(i==2){
            allPlay.getVoteMap().setVoteresult(1);
            return MessageEnum.OK;
        }
        if(i==3){
            allPlay.getVoteMap().setVoteresult(2);
            return MessageEnum.OK;
        }
        if(i==4){
            allPlay.getVoteMap().setVoteresult(3);
            return MessageEnum.OK;
        }
        else{
            return MessageEnum.ERROR;
        }
    }

    public synchronized void finalizeMapMode() throws IOException, RemoteException{
        allPlay.getVoteMap().setInitmap();
        allPlay.getVoteMap().setFinalresult();
        allPlay.getStateSelectedMap().setStrategyMap(allPlay.getVoteMap().getFinalresult());
        allPlay.getStateSelectedMap().setSelectedmap();
        refillMap();
        allPlay.getVoteMode().setFinalResult();
        allPlay.getStateSelectedMode().setSelectedmode(allPlay.getVoteMode().getFinalResult());
    }




        public void refillMap() throws RemoteException {
        for(Room r : allPlay.getStateSelectedMap().getSelectedmap().getRoomList()){
            for(Cell c : r.getCellsList()){
                if(c.getSpawnpointzone()==null){
                    if(c.getAmmohere()==null)
                        c.setAmmohere(allPlay.getCurrentDeckState().getAmmodeck().pop());
                }
                else{
                    for(int j=0;j<3;j++){
                        if(c.getSpawnpointzone().getSpawnWeaponsList()[j]==null) {
                            if(!allPlay.getCurrentDeckState().getWeaponsdeck().empty())
                                c.getSpawnpointzone().getSpawnWeaponsList()[j] = allPlay.getCurrentDeckState().getWeaponsdeck().pop();
                        }
                    }
                }
            }
        }
    }

    public boolean manageNickname(String nickname) throws RemoteException{
        if(idClientList.getNicknameList().contains(nickname)){
            return false;
        }
        else {
            idClientList.getNicknameList().add(nickname);
            return true;
        }
    }

    public void manageNickPlayer(String nickname, Player player)throws RemoteException{
        idClientList.addNickPlayer(nickname, player);
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
