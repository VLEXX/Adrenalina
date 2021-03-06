/**
 * @author Federico Scatà
 */
package view;

import model.datapacket.StatesEnum;
import model.datapacket.UpdatePacket;
import model.playerdata.Player;

import java.util.HashMap;

/**
 * Class that update viewdatabase with an updatePacket received by the server
 */
public class ViewUpdater {

    public synchronized void updateView(UpdatePacket updatePacket, ViewDatabase viewDatabase, HashMap<StatesEnum, ViewState> hashMap, Player player){

        viewDatabase.setPositionHashMap(updatePacket.getPositionHashMap());
        viewDatabase.setAttackinprogress(updatePacket.isAttackinprogress());
        viewDatabase.getViewCurrentPlayerState().setCurrentPlayerState(updatePacket.getCurrentPlayerState());
        viewDatabase.setSelectedMode(updatePacket.getSelectedMode());
        viewDatabase.getViewMapState().setSelectedMap(updatePacket.getMap());
        viewDatabase.getViewChartScore().setChartScore(updatePacket.getChart());
        viewDatabase.setPlayersdamage(updatePacket.getDamage());
        viewDatabase.setPlayersmarks(updatePacket.getMarks());
        if(updatePacket.getStatesEnum()==null){
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
            return;
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.ACTION)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.ACTION));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.POWERUP)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.POWERUP));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SPAWN)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SPAWN));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT_SECOND)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT_SECOND));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.SHOOT_THIRD)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.SHOOT_THIRD));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.PICK_UP)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.PICK_UP));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.MOVE)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.MOVE));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.END)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.END));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.WAIT)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.WAIT));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
        if(updatePacket.getStatesEnum().equals(StatesEnum.FRENZY)){
            viewDatabase.getViewState().replace(player, hashMap.get(StatesEnum.FRENZY));
            if(updatePacket.getPosition()!=null) {
                viewDatabase.setViewPlayerPosition(updatePacket.getPosition());
            }
            viewDatabase.setCurrentDeckState(updatePacket.getPowerUpDeck());
            viewDatabase.setEndgame(updatePacket.isEndgame());
        }
    }
}
