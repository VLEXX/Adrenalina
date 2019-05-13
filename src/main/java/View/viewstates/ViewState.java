package View.viewstates;

import Model.DataPacket;

import java.util.Scanner;

/**
 * Interface for View State Pattern
 */
public interface ViewState {

    /**
     * @return
     */
    DataPacket doAction(Scanner stdin);
}
