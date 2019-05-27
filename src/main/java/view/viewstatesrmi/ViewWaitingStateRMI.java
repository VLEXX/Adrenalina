package view.viewstatesrmi;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewWaitingStateRMI implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("OPPONENT TURN.\n Wait your turn please...\n");
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        return dataPacket;
    }
}
