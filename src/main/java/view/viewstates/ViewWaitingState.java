package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewWaitingState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("OPPONENT TURN.\n Wait your turn please...\n");
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        return dataPacket;
    }
}
