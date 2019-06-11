package view.viewstates;

import model.datapacket.DataPacket;
import model.datapacket.WeaponsMessage;
import model.map.Cell;
import model.playerdata.Player;
import model.weaponscard.Weapon;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewShootFirstState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        System.out.println("Choose a weapon to use between: \n");
        for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
            System.out.println(weapon.getName() + " | ");
        }
        String s;
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        Weapon w;
        while(true){
            s = stdin.nextLine();
            s = s.toLowerCase();
            for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                if(s.equals(weapon.getName())){
                    w = weapon;
                    viewDatabase.getViewCurrentPlayerState().setWeaponMultAttacks(w);
                    dataPacket.setFirstAttack(true);
                    dataPacket.setPlayer(player);
                    dataPacket.setWeapon(w);
                    break;
                }
            }
            if(dataPacket.getPlayer() != null){
                break;
            }
            System.out.println("WRONG INPUT! Please choose a weapon between: \n");
            for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
                System.out.println(weapon.getName() + " | ");
            }
        }

        if(dataPacket.getWeapon().getWeaponsMessage().get(0).equals(WeaponsMessage.MAX_ONE_PLAYER)) {
            System.out.println("Choose a player to attack between:");
            for(Player player1: viewDatabase.getPositionHashMap().keySet()){
                if(!player1.equals(player)){
                    if(!player1.equals(Player.FLAG)){
                        System.out.print(player1 + " ");
                    }
                }
            }
            System.out.print("\n");
            while (true) {
                s = stdin.nextLine();
                s = s.toUpperCase();
                for (Player player1 : viewDatabase.getPositionHashMap().keySet()){
                    if (s.equals(player1.toString())) {
                        if (!(s.equals(player.toString()))) {
                            if (!(s.equals(Player.FLAG.toString()))) {
                                dataPacket.getTargetPlayersFirst().add(player1);
                                break;
                            }
                            else{
                                System.out.println("ERROR! Choose another player...\n");
                                break;
                            }
                        }
                        else{
                            System.out.println("ERROR! Choose another player...\n");
                            break;
                        }
                    }
                    System.out.println("ciao exit");
                }
                if(!dataPacket.getTargetPlayersFirst().isEmpty()){
                    break;
                }
            }
            if((dataPacket.getWeapon().getName().equals("grenadelauncher"))||(dataPacket.getWeapon().getName().equals("rocketlauncher"))||(dataPacket.getWeapon().getName().equals("shotgun"))||(dataPacket.getWeapon().getName().equals("vortexcannon"))){
                System.out.println("Where do you want to move the target player? \nChoose a cell ID between:\n ");
                if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell()!=null) {
                    System.out.print(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell().getCellId());
                }
                if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell()!=null) {
                    System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell().getCellId());
                }
                if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell()!=null) {
                    System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell().getCellId());
                }
                if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell()!=null) {
                    System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell().getCellId());
                }
                while(true){
                    int id = stdin.nextInt();
                    if(id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell().getCellId())){
                        dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell());
                        break;
                    }
                    else if(id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell().getCellId())){
                        dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell());
                        break;
                    }
                    else if(id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell().getCellId())){
                        dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell());
                        break;
                    }
                    else if(id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell().getCellId())){
                        dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell());
                        break;
                    }
                    else{
                        System.out.println("WRONG INPUT! Please choose a cell between:\n");
                        if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell()!=null) {
                            System.out.print(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell().getCellId());
                        }
                        if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell()!=null) {
                            System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell().getCellId());
                        }
                        if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell()!=null) {
                            System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell().getCellId());
                        }
                        if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell()!=null) {
                            System.out.print( " | " + viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell().getCellId());
                        }
                    }
                }
            }
            else if((dataPacket.getWeapon().getName().equals("tractorbeam"))){
                System.out.println("Where do you want to move the target player? \nChoose a cell ID between:\n ");
                for(Cell cell: viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells()){
                    System.out.print(cell.getCellId());
                    if(!cell.equals(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells().get(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells().size()-1))){
                        System.out.print(" | ");
                    }
                }
                while(true){
                    int id = stdin.nextInt();
                    for(Cell cell: viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells()){
                        if(id == (cell.getCellId())){
                            dataPacket.setCell(cell);
                            break;
                        }
                    }
                    if(dataPacket.getCell()!=null){
                        break;
                    }
                    else{
                        System.out.println("WRONG INPUT! Please choose a cell between:\n");
                        for(Cell cell: viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells()){
                            System.out.print(cell.getCellId());
                            if(!cell.equals(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells().get(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getReachable2Cells().size()-1))){
                                System.out.print(" | ");
                            }
                        }
                    }
                }
            }
        }
        else if(dataPacket.getWeapon().getWeaponsMessage().get(0).equals(WeaponsMessage.MAX_TWO_PLAYER)) {
            System.out.println("How many people do you want to shoot? ( 1 | 2 )\n");
            int n;
            while(true){
                n = stdin.nextInt();
                if(n>0 && n<=2){
                    break;
                }
                else{
                    System.out.println("Choose 1 or 2 players...");
                }
            }
            System.out.println("Choose " + n + " player to attack between: \n");
            for(Player player1: Player.values()){
                if(!player1.equals(player)){
                    if(!player1.equals(Player.FLAG)){
                        System.out.print(player1 + " | ");
                    }
                }
            }
            System.out.print("\n");
            while(true) {
                while (true) {
                    s = stdin.nextLine();
                    s.toUpperCase();
                    for (Player player1 : Player.values()) {
                        if (s.equals(player1)) {
                            if (!s.equals(player)){
                                if (!s.equals(Player.FLAG)) {
                                    if(!dataPacket.getTargetPlayersFirst().contains(player1)) {
                                        dataPacket.getTargetPlayersFirst().add(player1);
                                        break;
                                    }
                                    else{
                                        System.out.println("Player already chosen!\n");
                                    }
                                }
                                else {
                                    System.out.println("ERROR! Choose another player...\n");
                                }
                            }
                            else{
                                System.out.println("ERROR! Choose another player...\n");
                            }
                        }
                    }
                    break;
                }
                if(dataPacket.getTargetPlayersFirst().size()==n){
                    break;
                }
            }
        }

        else if(dataPacket.getWeapon().getWeaponsMessage().get(0).equals(WeaponsMessage.MAX_THREE_PLAYER)) {
            System.out.println("How many people do you want to shoot? ( 1 | 2 | 3 )\n");
            int n;
            while(true){
                n = stdin.nextInt();
                if(n>0 && n<=3){
                    break;
                }
                else{
                    System.out.println("Choose 1, 2 or 3 players...");
                }
            }
            System.out.println("Choose " + n + " player to attack between: \n");
            for(Player player1: Player.values()){
                if(!player1.equals(player)){
                    if(!player1.equals(Player.FLAG)){
                        System.out.print(player1 + " | ");
                    }
                }
            }
            System.out.print("\n");
            while(true) {
                while (true) {
                    s = stdin.nextLine();
                    s.toUpperCase();
                    for (Player player1 : Player.values()) {
                        if (s.equals(player1)) {
                            if (!s.equals(player)){
                                if (!s.equals(Player.FLAG)) {
                                    if(!dataPacket.getTargetPlayersFirst().contains(player1)) {
                                        dataPacket.getTargetPlayersFirst().add(player1);
                                        break;
                                    }
                                    else{
                                        System.out.println("Player already chosen!\n");
                                    }
                                }
                                else {
                                    System.out.println("ERROR! Choose another player...\n");
                                }
                            }
                            else{
                                System.out.println("ERROR! Choose another player...\n");
                            }
                        }
                    }
                    break;
                }
                if(dataPacket.getTargetPlayersFirst().size()==n){
                    break;
                }
            }
        }
        return dataPacket;
    }
}
