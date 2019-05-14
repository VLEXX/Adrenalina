package View.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import View.ViewDatabase;

import java.util.Scanner;

/**
 * Interface for View State Pattern
 */
public interface ViewState {

    /**
     * @return
     */
    DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase);
}
