package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewPickupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        return null;
    }
}
