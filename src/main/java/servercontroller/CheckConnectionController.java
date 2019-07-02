package servercontroller;

import model.gamedata.IDClientList;
import model.gamedata.InitializeAllPlay;
import model.playerdata.Player;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class CheckConnectionController extends Thread {

    private IDClientList idClientList;
    private InitializeAllPlay allPlay;

    public CheckConnectionController(IDClientList clientList, InitializeAllPlay allPlay){
        this.idClientList=clientList;
        this.allPlay=allPlay;
    }

    @Override
    public void run() {
        try{
            ArrayList<Player> players = new ArrayList<>();
            while(true){
                if(allPlay.isEndgame()){
                    break;
                }
                else{
                    for(Player player: idClientList.getPlayerRMI()){
                        if(idClientList.getSleepPlayer()!=null){
                            if(!player.equals(idClientList.getSleepPlayer())){
                                if (idClientList.getConnection().get(player).equals(true)) {
                                    idClientList.getConnection().replace(player, false);
                                } else {
                                    idClientList.getConnection().remove(player);
                                    players.add(player);
                                    idClientList.getPlayerArrayList().remove(player);
                                    idClientList.getClientlist().remove(idClientList.getPlayerNick(player));
                                }
                            }
                        }
                        else{
                            if (idClientList.getConnection().get(player).equals(true)) {
                                idClientList.getConnection().replace(player, false);
                            } else {
                                idClientList.getConnection().remove(player);
                                players.add(player);
                                idClientList.getPlayerArrayList().remove(player);
                                idClientList.getClientlist().remove(idClientList.getPlayerNick(player));
                            }
                        }
                    }
                    if(players.size()!=0){
                        for(Player player: players){
                            idClientList.getPlayerRMI().remove(player);
                        }
                    }
                    players.clear();
                }
                sleep(600);
            }
        } catch (RemoteException | InterruptedException e) {

        }
    }
}
