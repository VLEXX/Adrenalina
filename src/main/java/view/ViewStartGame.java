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

    public ViewStartGame(Player p, ObjectInputStream oi, ObjectOutputStream oo, Scanner scanner, ViewDatabase database){
        this.viewDatabase = database;
        this.stdin = scanner;
        this.player=p;
        this.objectInputStream=oi;
        this.objectOutputStream=oo;
        this.stateHashMap = new HashMap<>();
        ViewActionState actionState = new ViewActionState();
        ViewEndturnState endTurnState = new ViewEndturnState();
        ViewMoveState moveState = new ViewMoveState();
        ViewWaitingState waitingState = new ViewWaitingState();
        ViewShootFirstState shootFirstState = new ViewShootFirstState();
        ViewShootSecondState shootSecondState = new ViewShootSecondState();
        ViewShootThirdState shootThirdState = new ViewShootThirdState();
        ViewPickupState pickUpState = new ViewPickupState();
        ViewPowerupState powerupState = new ViewPowerupState();
        stateHashMap.put(StatesEnum.ACTION, actionState);
        stateHashMap.put(StatesEnum.END, endTurnState);
        stateHashMap.put(StatesEnum.MOVE, moveState);
        stateHashMap.put(StatesEnum.WAIT, waitingState);
        stateHashMap.put(StatesEnum.SHOOT, shootFirstState);
        stateHashMap.put(StatesEnum.SHOOT_SECOND, shootSecondState);
        stateHashMap.put(StatesEnum.SHOOT_THIRD, shootThirdState);
        stateHashMap.put(StatesEnum.PICK_UP, pickUpState);
        stateHashMap.put(StatesEnum.POWERUP, powerupState);
    }

    public synchronized void run() {
        MessageWriter messageWriter = new MessageWriter();
        ViewUpdater viewUpdater = new ViewUpdater();
        UpdatePacket updatePacket = null;
        try {
            updatePacket = (UpdatePacket) objectInputStream.readObject();
            viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        while(true){
            try {
                MessageEnum messageEnum = (MessageEnum) objectInputStream.readObject();
                messageWriter.writeMessage(messageEnum);
                if(messageEnum.equals(MessageEnum.OK)) {
                    DataPacket dataPacket = viewDatabase.getViewState().get(player).doAction(stdin, player, viewDatabase);
                    objectOutputStream.writeObject(dataPacket);
                }
                MessageEnum messageEnumOK = (MessageEnum) objectInputStream.readObject();
                if (messageEnumOK.equals(MessageEnum.OK)){
                    updatePacket = (UpdatePacket) objectInputStream.readObject();
                    viewUpdater.updateView(updatePacket, viewDatabase, stateHashMap, player);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
