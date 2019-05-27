package view;

import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;
import servercontroller.UpdaterInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class ViewStartGameRMI extends Thread {

    private ViewDatabase viewDatabase;
    private Player player;
    private HashMap<StatesEnum, ViewState> stateHashMap;
    private Scanner stdin;
    private UpdaterInterface updaterInterface;

    public ViewStartGameRMI(Player p, Scanner scanner, ViewDatabase database, HashMap<StatesEnum,ViewState> hash, UpdaterInterface updaterInterface){
        this.viewDatabase = database;
        this.stdin = scanner;
        this.player=p;
        this.stateHashMap = hash;
        this.updaterInterface=updaterInterface;
    }

    public synchronized void run(){
        MessageWriter messageWriter = new MessageWriter();
        ViewUpdater viewUpdater = new ViewUpdater();
        UpdatePacket updatePacket;
        MessageEnum messageEnumOK;


        viewDatabase.getViewState().put(player, stateHashMap.get(StatesEnum.WAIT));
        try {
            updatePacket = updaterInterface.updateClient(player);
            viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
