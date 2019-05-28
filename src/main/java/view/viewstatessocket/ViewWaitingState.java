package view.viewstatessocket;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewWaitingState implements ViewState {

    private int i;

    public ViewWaitingState(){

        i=1;

    }


    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        if(this.i==1) {
            System.out.println("OPPONENT TURN.\n Wait your turn please...\n");
            i--;
        }
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        return dataPacket;
    }

    public void resetI() {
        this.i = 1;
    }
}
