package view.viewstates;

import model.datapacket.DataPacket;
import model.datapacket.WeaponsMessage;
import model.gamedata.Mode;
import model.map.Cell;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import model.weaponscard.Weapon;
import view.ViewDatabase;
import view.ViewState;

import java.util.Scanner;

public class ViewShootFirstState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());

        System.out.println("Choose a weapon to use between: \n");
        for(Weapon weapon: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getWeaponsList()){
            System.out.println(weapon.getName() + " | ");
        }
        String s;
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

        if(viewDatabase.getSelectedMode().equals(Mode.DOMINATION)){
            if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getPlayerposition().getCurrentcell().getSpawnpointzone()!=null){
                System.out.println("Do you want shoot to Spawn Point " + viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getPlayerposition().getCurrentcell().getSpawnpointzone().getSpawnColor() + "? (Y | N )\n");
                String ans;
                while(true){
                    ans=stdin.nextLine();
                    ans.toLowerCase();
                    if(ans.equals("y")){
                        dataPacket.setSpawnPointToAttack(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getPlayerposition().getCurrentcell().getSpawnpointzone());
                        return dataPacket;
                    }
                    else if(ans.equals("n")){
                        break;
                    }
                }
            }
        }

        if(dataPacket.getWeapon().getWeaponsMessage().get(0).equals(WeaponsMessage.MAX_ONE_PLAYER)){
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
                System.out.println("\n");
                while(true){
                    int id = stdin.nextInt();
                    stdin.nextLine();
                    if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell()!=null) {
                        if (id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell().getCellId())) {
                            dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getUpCell());
                            break;
                        }
                    }
                    if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell()!=null) {
                        if (id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell().getCellId())) {
                            dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getDownCell());
                            break;
                        }
                    }
                    if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell()!=null) {
                        if (id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell().getCellId())) {
                            dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getRightCell());
                            break;
                        }
                    }
                    if(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell()!=null) {
                        if (id == (viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell().getCellId())) {
                            dataPacket.setCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayersFirst().get(0)).getCurrentcell().getLeftCell());
                            break;
                        }
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
            for(Player player1: viewDatabase.getPositionHashMap().keySet()){
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
            for(Player player1: viewDatabase.getPositionHashMap().keySet()){
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
        for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
            if(powerUp.getId().equals(PowerUpId.TARGETING_SCOPE)){
                System.out.println("Do you want to use Power-Up 'Targeting Scope'? ( Y | N )\n");
                String risp;
                while(true){
                    risp = stdin.nextLine();
                    risp = risp.toLowerCase();
                    if(risp.equals("y")){
                        int k=0;
                        for(PowerUp powerUp1: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                            if(powerUp1.getId().equals(PowerUpId.TARGETING_SCOPE)){
                                k++;
                            }
                        }
                        if(k>1){
                            String power;
                            while(true){
                                System.out.println("Insert Power-Up name: ");
                                power = stdin.nextLine();
                                power = power.toUpperCase();
                                System.out.print("\n");
                                for(PowerUp powerUp3: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                                    if(powerUp3.getId().toString().equals(power)){
                                        String color;
                                        System.out.println("Insert Power-Up color: ");
                                        color = stdin.nextLine();
                                        color = color.toUpperCase();
                                        System.out.println("\n");
                                        for(PowerUp powerUp1: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                                            if(powerUp1.getId().toString().equals(power) && powerUp1.getColor().toString().equals(color)){
                                                dataPacket.setPowerUpId(powerUp1.getId());
                                                dataPacket.setPowerUpColor(powerUp1.getColor());
                                                break;
                                            }
                                        }
                                        if(dataPacket.getPowerUpId()!=null && dataPacket.getPowerUpColor()!=null){
                                            break;
                                        }
                                    }
                                }
                                if(dataPacket.getPowerUpId()!=null && dataPacket.getPowerUpColor()!=null){
                                    break;
                                }
                            }
                        }
                        else if(k==1){
                            dataPacket.setPowerUpId(powerUp.getId());
                            dataPacket.setPowerUpColor(powerUp.getColor());
                        }
                    }
                    else if(risp.equals("n")){
                        break;
                    }
                    else{
                        System.out.println("WRONG INPUT! Please insert 'y' or 'n'\n");
                    }
                }
            }
            break;
        }
        return dataPacket;
    }
}
