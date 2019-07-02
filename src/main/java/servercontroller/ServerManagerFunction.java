/**
 * @author Federico Scatà
 */
package servercontroller;

import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.MessageString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ServerManagerFunction {

    public ServerManagerFunction(){}

    public synchronized Player chooseCharacterManager(ObjectInputStream objectInputStream, InitializeAllPlay allPlay, ObjectOutputStream objectOutputStream, IDClientList IDclientList) throws IOException, ClassNotFoundException {
        objectOutputStream.writeObject(allPlay.getCurrentDeckState());

        Player player;
        Boolean ok;
        while(true) {
            player = (Player) objectInputStream.readObject();
            if (allPlay.getCurrentDeckState().getPlayers().contains(player)) {
                MessageString message = new MessageString("You choose: " + player.toString());
                objectOutputStream.writeObject(message);
                allPlay.getCurrentPlayerState().put(player, new CurrentPlayerState(player));
                allPlay.getCurrentDeckState().getPlayers().remove(player);
                allPlay.getCurrentPlayerState().get(player).setToken(IDclientList.addClient());
                IDclientList.getPlayerArrayList().add(player);
                ok = true;
                objectOutputStream.writeObject(ok);
                allPlay.decreasePlayerCounterTemp();
                break;
            }
            else{
                MessageString message = new MessageString("Character not available");
                objectOutputStream.writeObject(message);
                ok = false;
                objectOutputStream.writeObject(ok);
            }
        }
        return player;
    }

    public synchronized void manageVoteMap(InitializeAllPlay allPlay, PrintWriter printWriter, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException, ClassNotFoundException {
        Integer i = (Integer) objectInputStream.readObject();

        if(i==1){
            allPlay.getVoteMap().setVoteresult(0);
        }
        if(i==2){
            allPlay.getVoteMap().setVoteresult(1);
        }
        if(i==3){
            allPlay.getVoteMap().setVoteresult(2);
        }
        if(i==4){
            allPlay.getVoteMap().setVoteresult(3);
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
}
