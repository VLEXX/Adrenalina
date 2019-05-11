/**
 * @author Alex Saletti & Federico Scatà
 */
package Model;

public class MoveState implements State {

    //metodo che gestisce lo spostamento del player p
    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        /**
         printWriter.println("Choose a cell...");
         String s = new String();
         Cell c;
         while(true){                    //Server attende ID della cella in cui spostarsi dal client.
         if(s.isEmpty()==true) {
         s = scanner.nextLine();
         }
         else{
         c = this.cellFinder(i,s,p);
         if(c!=null){
         break;
         }
         else{
         printWriter.println("Invalid cell! Please, insert another cell...\n");
         s=null;
         }
         }

         }
         */
        int out = setMove(allPlay, dataPacket.getCell(), dataPacket.getPlayer());
        if (out != 0) {
            return MessageEnum.UNREACHABLE_CELL;
        } else {
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                allPlay.getHashMapState().replace(dataPacket.getPlayer(), new MidState());
                allPlay.notifyObserver();
            }
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 0) {
                allPlay.getHashMapState().replace(dataPacket.getPlayer(), new EndTurnState());
                allPlay.notifyObserver();
            }
        }
        return MessageEnum.OK;
    }

    public int setMove(InitializeAllPlay i, Cell c, Player p) {
        for (CurrentPlayerState ps : i.getCurrentPlayerState().values()) {
            if (ps.getActiveplayer() == p) {
                ps.getPlayerposition().setCurrentcell(c);
                i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> {
                    if (room.getCellsList().contains(c)) {
                        ps.getPlayerposition().setCurrentroom(room);
                    }
                });
                return 0;
            }
        }
        return -1;
    }

    //metodo che restituisce la cella avente un dato id
    public Cell cellFinder(InitializeAllPlay i, int id, Player player) {

        for (Room room : i.getStateSelectedMap().getSelectedmap().getRoomList()) {
            for (Cell cell : room.getCellsList()) {
                if (cell.getCellId() == id) {
                    if (cellIsReachable(i, player, cell)) {
                        return cell;
                    } else
                        return null;
                }
            }
        }
        return null;
    }

    public boolean cellIsReachable(InitializeAllPlay i, Player p, Cell c) {
        for (CurrentPlayerState cps : i.getCurrentPlayerState().values()) {
            if (cps.getActiveplayer() == p && cps.getPlayerposition().getCurrentcell().getReachableCells().contains(c))
                return true;
        }
        return false;
    }
}

