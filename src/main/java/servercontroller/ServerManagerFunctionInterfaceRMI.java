package servercontroller;

import model.datapacket.MessageEnum;
import model.playerdata.Player;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerManagerFunctionInterfaceRMI extends Remote {

    MessageEnum chooseCharacterManager(Player player) throws IOException, RemoteException;

    MessageEnum manageVoteMap(int i) throws IOException, RemoteException;

    void manageNickname(String nickname)throws RemoteException;

    void manageNickPlayer(String nickname, Player player)throws RemoteException;

}