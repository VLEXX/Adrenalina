package view.viewstates;

import model.datapacket.DataPacket;
import model.playerdata.Player;
import model.powerups.PowerUp;
import view.ViewDatabase;

import java.util.Scanner;

public class ViewSpawnState implements ViewState {
    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(player);
        System.out.println("Now two Power-Up Cards will be picked up...\n");
        PowerUp pop1 = viewDatabase.getCurrentDeckState().pop();
        PowerUp pop2 = viewDatabase.getCurrentDeckState().pop();
        System.out.println("Power-Up 1: " + pop1.getId() + "  Card Color 1: " + pop1.getColor() + "\n");
        System.out.println("Power-Up 2: " + pop2.getId() + "  Card Color 2: " + pop2.getColor() + "\n");
        System.out.println("Which card do you want to keep? ( '1' | '2' )...\n");
        String s;
        while(true) {
            s = stdin.nextLine();
            if (s.equals("1")) {
                dataPacket.setPowerUpToKeepSpawn(pop1);
                dataPacket.setPowerUpSpawn(pop2);
                break;
            }
            if (s.equals("2")) {
                dataPacket.setPowerUpToKeepSpawn(pop1);
                dataPacket.setPowerUpSpawn(pop2);
                break;
            }
            else{
                System.out.println("WRONG INPUT! Please write: '1' if you want to keep Card 1 or '2' if you want to keep Card 2\n");
            }
        }
        return dataPacket;
    }
}
