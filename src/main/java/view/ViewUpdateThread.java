package view;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;
import view.viewstates.ViewState;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

public class ViewUpdateThread extends Thread {

    private ObjectInputStream objectInputStream;
    private ViewUpdater viewUpdater;
    private Player player;
    private ViewDatabase viewDatabase;
    private HashMap<StatesEnum, ViewState> hashMap;


    public ViewUpdateThread(ObjectInputStream oi, ViewUpdater update, Player p, ViewDatabase database, HashMap<StatesEnum, ViewState> hash){
        this.objectInputStream=oi;
        this.viewUpdater=update;
        this.viewDatabase=database;
        this.hashMap=hash;
        this.player=p;
    }

    public synchronized void run(){
        UpdatePacket updatePacket;
        while (true) {
            updatePacket=null;
            if (viewDatabase.isEndgame() == false) {
                if (updatePacket != null) {
                    break;
                }
                try {
                    updatePacket = (UpdatePacket) objectInputStream.readObject();

                    viewUpdater.updateView(updatePacket, viewDatabase, hashMap, player);
                } catch (IOException e) {

                } catch (ClassNotFoundException e) {

                }
            }

        }
    }

}
