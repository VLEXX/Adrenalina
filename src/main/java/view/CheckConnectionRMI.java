/**
 * @author Federico Scatà
 */
package view;

import model.gamedata.IDClientListInterface;
import model.gamedata.InitializeAllPlayInterface;
import model.playerdata.Player;

import java.rmi.RemoteException;

/**
 * Thread that notify client is connected to server
 */
public class CheckConnectionRMI extends Thread {

    private IDClientListInterface idClientList;
    private InitializeAllPlayInterface allPlay;
    private Player player;

    public CheckConnectionRMI(IDClientListInterface clientListInterface, InitializeAllPlayInterface initializeAllPlayInterface, Player player){
        this.idClientList=clientListInterface;
        this.allPlay=initializeAllPlayInterface;
        this.player=player;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if(allPlay.isEndgame()) {
                   break;
                }
                else{
                    idClientList.replaceConnection(player, true);
                    sleep(50);
                }
            }
        }
        catch (RemoteException | InterruptedException e) {

        }
    }
}
