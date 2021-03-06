/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.DataPacket;
import model.playerdata.Player;

import java.util.Scanner;

/**
 * Interface for view State Pattern
 */
public interface ViewState {

    /**
     * @return
     */
    DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase);
}
