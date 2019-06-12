package view.viewstates;

import model.datapacket.DataPacket;
import model.map.Cell;
import model.map.Room;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import view.ViewDatabase;
import view.ViewState;

import java.util.ArrayList;
import java.util.Scanner;

public class ViewPowerupState implements ViewState {

    @Override
    public DataPacket doAction(Scanner stdin, Player player, ViewDatabase viewDatabase) {
        String s;
        DataPacket dataPacket = new DataPacket();
        dataPacket.setToken(viewDatabase.getClientToken());
        dataPacket.setPlayer(player);
        if(viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList().isEmpty()){
            return dataPacket;
        }
        dataPacket.setPowerupAction(true);
        if(!viewDatabase.isAttackinprogress()){
            int n=0;
            for (PowerUp powerUp : viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()) {
                if (powerUp.getId().equals(PowerUpId.NEWTON) || powerUp.getId().equals(PowerUpId.TELEPORTER)) {
                    n=1;
                    System.out.println("Choose Power-Up to use between:\n");
                    if (powerUp.getId().equals(PowerUpId.NEWTON)) {
                        System.out.println(PowerUpId.NEWTON + " " + powerUp.getColor() + "\n");
                    } else if (powerUp.getId().equals(PowerUpId.TELEPORTER)) {
                        System.out.println(PowerUpId.TELEPORTER + " " + powerUp.getColor() + "\n");
                    }
                }
            }
            if(n==0){
                System.out.println("You don't have Power-Up to use now!\n");
                return dataPacket;
            }
            String power;
            while(true){
                System.out.println("Insert Power-Up name: ");
                power = stdin.nextLine();
                power = power.toUpperCase();
                System.out.print("\n");
                for(PowerUp powerUp: viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()){
                    if(powerUp.getId().toString().equals(power)){
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
            if(dataPacket.getPowerUpId().equals(PowerUpId.NEWTON)){
                System.out.println("Choose the player to move between: \n");
                for(Player player1: viewDatabase.getPositionHashMap().keySet()){
                    if(player!=player1){
                        System.out.println(player1 + " ");
                    }
                }
                String target;
                while(true){
                    target = stdin.nextLine();
                    target = target.toUpperCase();
                    for(Player player1: viewDatabase.getPositionHashMap().keySet()){
                        if(target.equals(player1.toString())){
                            if(player!=player1){
                                dataPacket.setTargetPlayerPowerup(player1);
                                break;
                            }
                        }
                    }
                    if(dataPacket.getTargetPlayerPowerup()!=null){
                        break;
                    }
                    else{
                        System.out.println("WRONG INPUT! Please choose between: \n");
                        for(Player player1: viewDatabase.getPositionHashMap().keySet()){
                            if(player!=player1){
                                System.out.println(player1 + " ");
                            }
                        }
                    }
                }
                ArrayList<Integer> celltomove = createArrayCell(viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayerPowerup()).getCurrentcell());
                System.out.println("Choose a cell to move the target player between: \n");
                for(Integer cell: celltomove){
                    System.out.print(cell + " ");
                }
                System.out.print("\n");
                int cell;
                while(true){
                    cell = stdin.nextInt();
                    for(Integer c: celltomove){
                        if(cell==c){
                            for(Cell found: viewDatabase.getPositionHashMap().get(dataPacket.getTargetPlayerPowerup()).getCurrentcell().getReachable2Cells()) {
                                if(found.getCellId()==cell) {
                                    dataPacket.setCell(found);
                                    stdin.nextLine();
                                    return dataPacket;
                                }
                            }
                        }
                    }
                    if(dataPacket.getCell()==null){
                        System.out.println("WRONG INPUT! Please choose between: \n");
                        for(Integer i: celltomove){
                            System.out.print(i + " ");
                        }
                    }
                }
            }
            if(dataPacket.getPowerUpId().equals(PowerUpId.TELEPORTER)){
                System.out.println("Choose a cell to move the target player between: \n");
                for(Room room: viewDatabase.getViewMapState().getSelectedMap().getRoomList()){
                    for(Cell cell: room.getCellsList()) {
                        System.out.print(cell.getCellId() + " ");
                    }
                }
                System.out.print("\n");
                int cell;
                while(true){
                    cell = stdin.nextInt();
                    for(Room room: viewDatabase.getViewMapState().getSelectedMap().getRoomList()){
                        for(Cell c: room.getCellsList()) {
                            if (cell == c.getCellId()) {
                                dataPacket.setCell(c);
                                stdin.nextLine();
                                return dataPacket;
                            }
                        }
                    }
                    if(dataPacket.getCell()==null){
                        System.out.println("WRONG INPUT! Please choose between: \n");
                        for(Room room: viewDatabase.getViewMapState().getSelectedMap().getRoomList()){
                            for(Cell cell1: room.getCellsList()) {
                                System.out.print(cell1.getCellId() + " ");
                            }
                        }
                        System.out.print("\n");
                    }
                }
            }
        }
        else {
            for (PowerUp powerUp : viewDatabase.getViewCurrentPlayerState().getCurrentPlayerState().getBoard().getPowerupList()) {
                if (powerUp.getId().equals(PowerUpId.TAGBACK_GRENADE)) {
                    System.out.println("Do you want to use PowerUp 'TAGBACK GRANADE'? ( Y | N )\n");
                    s = stdin.nextLine();
                    s.toLowerCase();
                    if (s.equals("y")) {
                        dataPacket.getMarksToAdd().put(player, 1);
                        return dataPacket;
                    }
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> createArrayCell(Cell c){
        ArrayList<Integer> arrayList = new ArrayList<>();
        if(c.getUpCell()!=null){
            arrayList.add(c.getUpCell().getCellId());
            if(c.getUpCell().getUpCell()!=null){
                arrayList.add(c.getUpCell().getUpCell().getCellId());
            }
        }
        if(c.getDownCell()!=null){
            arrayList.add(c.getDownCell().getCellId());
            if(c.getDownCell().getDownCell()!=null){
                arrayList.add(c.getDownCell().getDownCell().getCellId());
            }
        }
        if(c.getRightCell()!=null){
            arrayList.add(c.getRightCell().getCellId());
            if(c.getRightCell().getRightCell()!=null){
                arrayList.add(c.getRightCell().getRightCell().getCellId());
            }
        }
        if(c.getLeftCell()!=null){
            arrayList.add(c.getLeftCell().getCellId());
            if(c.getLeftCell().getLeftCell()!=null){
                arrayList.add(c.getLeftCell().getLeftCell().getCellId());
            }
        }
        return arrayList;
    }
}
