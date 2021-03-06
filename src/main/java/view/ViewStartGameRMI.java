/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.IDClientListInterface;
import model.gamedata.InitializeAllPlayInterface;
import model.gamedata.ManageEndTurnInterface;
import model.playerdata.Player;
import servercontroller.StateBoxInterface;
import servercontroller.UpdaterInterface;
import view.viewstates.ViewFrenzyState;
import view.viewstates.ViewPowerupState;
import view.viewstates.ViewWaitingState;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that manage the view game for RMI client
 */
public class ViewStartGameRMI extends Thread {

    private ViewDatabase viewDatabase;
    private Player player;
    private HashMap<StatesEnum, ViewState> stateHashMap;
    private Scanner stdin;
    private UpdaterInterface updaterInterface;
    private InitializeAllPlayInterface allPlay;
    private ManageEndTurnInterface manageEndTurn;
    private StateBoxInterface statebox;

    public ViewStartGameRMI(Player p, Scanner scanner, ViewDatabase database, HashMap<StatesEnum,ViewState> hash, UpdaterInterface updaterInterface, InitializeAllPlayInterface allPlayInterface, ManageEndTurnInterface manageEndTurn, StateBoxInterface stateBoxInterface, IDClientListInterface clientList){
        this.viewDatabase = database;
        this.stdin = scanner;
        this.player=p;
        this.stateHashMap = hash;
        this.updaterInterface=updaterInterface;
        this.allPlay=allPlayInterface;
        this.manageEndTurn=manageEndTurn;
        this.statebox= stateBoxInterface;
    }

    public synchronized void run(){
        ViewUpdater viewUpdater = new ViewUpdater();
        UpdatePacket updatePacket;
        MessageEnum messageEnumOK;
        PlayerInformer playerInformer = new PlayerInformer(viewDatabase);

        try {
            updatePacket = updaterInterface.updateClient(player);
            viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
        } catch (IOException | CloneNotSupportedException | ClassNotFoundException e) {}

        int n =1;
        int k = 1;

        while(true){
            try {
                if (viewDatabase.getViewState().get(player) instanceof ViewWaitingState) {
                    if (n == 1) {
                        DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                        messageEnumOK = allPlay.getPlayerState(player).doAction(dataPacket);
                        System.out.println(messageEnumOK);
                        n--;
                    }
                } else if (viewDatabase.getViewState().get(player) instanceof ViewFrenzyState) {
                    if (k == 1) {
                        DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                        messageEnumOK = allPlay.getPlayerState(player).doAction(dataPacket);
                        System.out.println(messageEnumOK);
                        k--;
                    }
                } else {
                    DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                    messageEnumOK = allPlay.getPlayerState(player).doAction(dataPacket);
                    System.out.println(messageEnumOK);
                }

                if (allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.END)) {
                    manageEndTurn.manageEndTurn(player, statebox.getHashMap());
                }

                updatePacket = updaterInterface.updateClient(player);
                viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
                playerInformer.informer();
                if (!(viewDatabase.getViewState().get(player) instanceof ViewWaitingState)) {
                    ((ViewWaitingState) stateHashMap.get(StatesEnum.WAIT)).resetI();
                    n = 1;
                }
                if (!(viewDatabase.getViewState().get(player) instanceof ViewFrenzyState)) {
                    k = 1;
                }
            }
            catch (RemoteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
