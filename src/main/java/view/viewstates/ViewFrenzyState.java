package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewFrenzyState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        dataPacket.setPlayer(player);

        System.out.println("Do you want Final Frenzy? ( Y | N )\n");
        String s;
        while(true){
            s=stdin.nextLine();
            s.toLowerCase();
            if(s.equals("y")){
                dataPacket.setFrenzy(true);
                break;
            }
            else if(s.equals("n")){
                dataPacket.setFrenzy(false);
                break;
            }
            else{
                System.out.println("WRONG INPUT! Please insert 'y' or 'n'\n");
                System.out.println("Do you want Final Frenzy? ( Y | N )\n");
            }
        }
        return dataPacket;
    }
}
