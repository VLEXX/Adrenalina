/**
 * @author Federico Scatà
 */
package servercontroller;

import model.datapacket.StatesEnum;
import model.gamedata.ChartScore;
import model.gamedata.InitializeAllPlay;
import model.map.Map;
import model.map.Position;
import model.modelstates.*;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.datapacket.UpdatePacket;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Stack;

/**
 * Create a Updatepacket to send to each client with method updateClient.
 */
public class Updater extends UnicastRemoteObject implements UpdaterInterface {
    private InitializeAllPlay allPlay;

    public Updater(InitializeAllPlay i) throws RemoteException{
        this.allPlay=i;
    }


    /**
     * @param player
     * @return updatePacket
     * @throws IOException
     * @throws RemoteException
     * @throws CloneNotSupportedException
     */
    public synchronized UpdatePacket updateClient(Player player) throws IOException, RemoteException, CloneNotSupportedException, ClassNotFoundException {

        Position position = new Position();
        if (allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell() != null) {
            position.setCurrentcell(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentcell());
            position.setCurrentroom(allPlay.getCurrentPlayerState().get(player).getPlayerposition().getCurrentroom());
        }

        StatesEnum state = allPlay.getHashMapState().get(player).getNamestate();

        Stack<PowerUp> deck = (Stack<PowerUp>) allPlay.getCurrentDeckState().getPowerupdeck().clone();
        ChartScore chartScore = null;
        Map map = null;
        CurrentPlayerState currentPlayerState = null;
        try {
            chartScore = allPlay.getChartScore().deepClone();
            map = allPlay.getStateSelectedMap().getSelectedmap().deepClone();
            currentPlayerState = allPlay.getCurrentPlayerState().get(player).deepClone();
        } catch (ClassNotFoundException e) {}

        boolean endgame = allPlay.isEndgame();

        UpdatePacket updatePacket = new UpdatePacket(chartScore, currentPlayerState, map, position, state, deck, endgame);

        for(CurrentPlayerState currentPlayerState1: allPlay.getCurrentPlayerState().values()){
            if(currentPlayerState1.getPlayerposition().getCurrentcell()==null){
                updatePacket.addInHashMap(currentPlayerState1.getActiveplayer(), null);
            }
            else {
                try {
                    Position position1 = currentPlayerState1.getPlayerposition().deepClone();
                    updatePacket.addInHashMap(currentPlayerState1.getActiveplayer(), position1);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            updatePacket.setDamage(currentPlayerState1.getActiveplayer(), currentPlayerState1.getBoard().getDamageBox().deepCloneDamage());
            updatePacket.setMarks(currentPlayerState1.getActiveplayer(), currentPlayerState1.getBoard().getMarksBox().deepCloneMarks());
        }

        if(allPlay.getCurrentPlayerState().get(player).isAttackinprogress()){
            updatePacket.setAttackinprogress(true);
        }

        updatePacket.setSelectedMode(allPlay.getStateSelectedMode().getSelectedmode());
        updatePacket.setSkullArray(allPlay.getSkullArray());
        updatePacket.setSecondSkullArray(allPlay.getSecondSkullArray());

        return updatePacket;
    }
}
