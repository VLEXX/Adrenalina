package View.viewstates;

import Model.Action;
import Model.DataPacket;

import java.util.Scanner;

/**
 * View state to choice action to do (shoot, move, pick_up or end turn)
 */
public class ViewActionState implements ViewState {
    /**
     * set Action attribute in datapacket
     *
     * @param stdin Scanner "System.in"
     * @return datapacket
     */
    @Override
    public DataPacket doAction(Scanner stdin) {
        System.out.println("Chose your action: SHOOT | MOVE | PICKUP | ENDTURN");
        DataPacket dataPacket;
        while(true) {
            String s = stdin.nextLine();
            dataPacket = setAction(s);
            if(dataPacket!=null){
                break;
            }
            else{
                System.out.println("WRONG INPUT!\n Please choose between: SHOOT | MOVE | PICKUP | ENDTURN");
            }
        }
        return dataPacket;
    }

    protected DataPacket setAction(String s){
        DataPacket dataPacket = new DataPacket();
        if (s.equals("SHOOT") || s.equals("shoot") || s.equals("Shoot")) {
            dataPacket.setAction(Action.SHOOT);
            return dataPacket;
        }
        if (s.equals("PICKUP") || s.equals("pickup") || s.equals("Pickup")) {
            dataPacket.setAction(Action.PICK_UP);
            return dataPacket;
        }
        if (s.equals("MOVE") || s.equals("move") || s.equals("Move")) {
            dataPacket.setAction(Action.MOVE);
            return dataPacket;
        }
        if (s.equals("ENDTURN") || s.equals("endturn") || s.equals("Endturn")) {
            dataPacket.setAction(Action.ENDTURN);
            return dataPacket;
        } else {
            return null;
        }
    }
}
