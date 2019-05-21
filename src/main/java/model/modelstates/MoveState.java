/**
 * @author Alex Saletti & Federico Scatà
 */
package model.modelstates;

import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;

import java.util.HashMap;

/**
 * Class who manages a move during a player's turn
 */
public class MoveState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public MoveState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }


    /**
     * @param dataPacket contains data sent from player
     * @return an ok message if the required action is successful
     */
    @Override
    public MessageEnum doAction(DataPacket dataPacket) {

        int out = setMove(allPlay, dataPacket.getCell(), dataPacket.getPlayer());
        if (out != 0) {
            return MessageEnum.UNREACHABLE_CELL;
        } else {
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 1) {
                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));
                allPlay.notifyObserver();
            }
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
                allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
                allPlay.notifyObserver();
            }
        }
        return MessageEnum.OK;
    }

    /**
     * updates to c the player p's position, if the cell is unreachable returns error
     * @param i class containing information about the current match
     * @param c cell that the player p wants to reach
     * @param p player who wants to move
     * @return 0 if the required action is successful, -1 in other cases
     */
    public int setMove(InitializeAllPlay i, Cell c, Player p) {
        CurrentPlayerState ps = i.getCurrentPlayerState().get(p);
            if (i.getCurrentPlayerState().get(p).isActiveturn() && ps.getPlayerposition().getCurrentcell().getReachable3Cells().contains(c)) {
                ps.getPlayerposition().setCurrentcell(c);
                for(Room room :i.getStateSelectedMap().getSelectedmap().getRoomList()) {
                    if (room.getCellsList().contains(c)) {
                        ps.getPlayerposition().setCurrentroom(room);
                    }
                };
                return 0;
            }
        return -1;
    }


    /**
     * returns a cell that is reachable from p and has Id=id, null if the required cell doesn't exist
     * @param i class containing information about the current match
     * @param id the id of the required cell
     * @param player player who wants to move
     * @return cell that has Id=id if is reachable by the player p, null in other cases
     */
    public Cell cellFinder(InitializeAllPlay i, int id, Player player) {

        for (Room room : i.getStateSelectedMap().getSelectedmap().getRoomList()) {
            for (Cell cell : room.getCellsList()) {
                if (cell.getCellId() == id) {
                    if (i.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell().getReachable3Cells().contains(cell)) {
                        return cell;
                    } else
                        return null;
                }
            }
        }
        return null;
    }

}

