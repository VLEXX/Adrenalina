package view;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;
import view.viewstates.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

public class ViewStartGame extends Thread {


    private ViewDatabase viewDatabase;
    private Player player;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private HashMap<StatesEnum, ViewState> stateHashMap;
    private Scanner stdin;

    public ViewStartGame(Player p, ObjectInputStream oi, ObjectOutputStream oo, Scanner scanner, ViewDatabase database, HashMap<StatesEnum,ViewState> hash){
        this.viewDatabase = database;
        this.stdin = scanner;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = hash;

    }

    public synchronized void run() {
        MessageWriter messageWriter = new MessageWriter();
        ViewUpdater viewUpdater = new ViewUpdater();
        UpdatePacket updatePacket;
        MessageEnum messageEnumOK;
        viewDatabase.getViewState().put(player, stateHashMap.get(StatesEnum.WAIT));
        while(true) {
            try {
                updatePacket = (UpdatePacket) objectInputStream.readObject();
                viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
                break;
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
        }
        while(true){
            updatePacket = null;
            messageEnumOK = null;
            try {
                    DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                    objectOutputStream.writeObject(dataPacket);

                while (true) {
                    if(messageEnumOK!=null){
                        messageWriter.writeMessage(messageEnumOK);
                        break;
                    }
                    messageEnumOK = (MessageEnum) objectInputStream.readObject();
                }
                if(messageEnumOK.equals(MessageEnum.OK)) {
                    while (true) {
                        if(updatePacket!=null){
                            viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
                            break;
                        }
                        updatePacket = (UpdatePacket) objectInputStream.readObject();
                    }
                }
            } catch (IOException e) {

            } catch (ClassNotFoundException e) {

            }
        }
    }
}
