package servercontroller;

import model.gamedata.IDClientList;
import model.playerdata.Player;

import java.rmi.RemoteException;

public class CheckPlayer extends Thread {

    private IDClientList idClientList;

    public CheckPlayer(IDClientList clientList){
        this.idClientList=clientList;
    }

    @Override
    public void run() {
        while (true){
            try {
                for(Player player: idClientList.getPlayerArrayList()){
                    System.out.print(player + "\n");
                }
                sleep(6*1000);
            } catch (RemoteException | InterruptedException e) {

            }
        }
    }
}
