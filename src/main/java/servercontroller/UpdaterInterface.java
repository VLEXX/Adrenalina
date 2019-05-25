package servercontroller;

import model.datapacket.UpdatePacket;
import model.playerdata.Player;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UpdaterInterface extends Remote {

    UpdatePacket updateClient(Player player) throws IOException, RemoteException;

}
