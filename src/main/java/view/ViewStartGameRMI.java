package view;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.gamedata.InitializeAllPlayInterface;
import model.gamedata.ManageEndTurn;
import model.gamedata.ManageEndTurnInterface;
import model.modelstates.EndTurnState;
import model.playerdata.Player;
import servercontroller.StateBoxInterface;
import servercontroller.UpdaterInterface;
import view.viewstatessocket.ViewWaitingState;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

public class ViewStartGameRMI extends Thread {

    private ViewDatabase viewDatabase;
    private Player player;
    private HashMap<StatesEnum, ViewState> stateHashMap;
    private Scanner stdin;
    private UpdaterInterface updaterInterface;
    private InitializeAllPlayInterface allPlay;
    private ManageEndTurnInterface manageEndTurn;
    private StateBoxInterface statebox;

    public ViewStartGameRMI(Player p, Scanner scanner, ViewDatabase database, HashMap<StatesEnum,ViewState> hash, UpdaterInterface updaterInterface, InitializeAllPlayInterface allPlayInterface, ManageEndTurnInterface manageEndTurn, StateBoxInterface stateBoxInterface){
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
        MessageWriter messageWriter = new MessageWriter();
        ViewUpdater viewUpdater = new ViewUpdater();
        UpdatePacket updatePacket;
        MessageEnum messageEnumOK;

        try {
            updatePacket = updaterInterface.updateClient(player);
            viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
        } catch (IOException e) {}


        while(true){
            try {
                if (viewDatabase.isEndgame() == false) {
                    DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                    messageEnumOK = allPlay.getPlayerState(player).doAction(dataPacket);
                    messageWriter.writeMessage(messageEnumOK);

                    if(allPlay.getHashMapState().get(player).getNamestate().equals(StatesEnum.END)){
                        manageEndTurn.manageEndTurn(player,statebox.getHashMap());
                    }

                    updatePacket = updaterInterface.updateClient(player);
                    viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);

                    if(!(viewDatabase.getViewState().get(player)instanceof ViewWaitingState)){
                        ((ViewWaitingState)stateHashMap.get(StatesEnum.WAIT)).resetI();
                    }

                }
                else {
                    break;
                }
            }
            catch (RemoteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
