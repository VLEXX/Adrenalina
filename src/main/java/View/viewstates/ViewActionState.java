package View.viewstates;

import model.datapacket.Action;
import model.datapacket.DataPacket;
import model.playerdata.Player;
import View.ViewDatabase;

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
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("Chose your action: SHOOT | MOVE | PICKUP | ENDTURN");
        DataPacket dataPacket;
        while(true) {
            String s = stdin.nextLine();
            dataPacket = setActionPacket(s);
            if(dataPacket!=null){
                break;
            }
            else{
                System.out.println("WRONG INPUT!\n Please choose between: SHOOT | MOVE | PICKUP | ENDTURN");
            }
        }
        dataPacket.setPlayer(player);
        return dataPacket;
    }

    protected DataPacket setActionPacket(String s){
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
