package view;

import model.gamedata.CurrentDeckState;
import model.map.Cell;
import model.map.Position;
import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ViewDatabaseTest {

    @Test
    void setViewCurrentPlayerState() {
        ViewDatabase viewDatabase = new ViewDatabase();
        ViewCurrentPlayerState viewCurrentPlayerState = new ViewCurrentPlayerState();
        viewDatabase.setViewCurrentPlayerState(viewCurrentPlayerState);
        assertEquals(viewDatabase.getViewCurrentPlayerState(), viewCurrentPlayerState);
    }

    @Test
    void setViewChartScore() {
        ViewDatabase viewDatabase = new ViewDatabase();
        ViewChartScore viewChartScore = new ViewChartScore();
        viewDatabase.setViewChartScore(viewChartScore);
        assertEquals(viewDatabase.getViewChartScore(), viewChartScore);
    }

    @Test
    void setViewMapState() {
        ViewDatabase viewDatabase = new ViewDatabase();
        ViewMapState viewMapState = new ViewMapState();
        viewDatabase.setViewMapState(viewMapState);
        assertEquals(viewDatabase.getViewMapState(), viewMapState);
    }

    @Test
    void setViewPlayerPosition() {
        ViewDatabase viewDatabase = new ViewDatabase();
        Position position = new Position();
        viewDatabase.setViewPlayerPosition(position);
        assertEquals(viewDatabase.getViewPlayerPosition(), position);
    }

    @Test
    void setThisplayer() {
        ViewDatabase viewDatabase = new ViewDatabase();
        viewDatabase.setThisplayer(Player.YELLOW);
        assertEquals(viewDatabase.getThisplayer(), Player.YELLOW);
    }

    @Test
    void getViewState() {
        ViewDatabase viewDatabase = new ViewDatabase();
        assertEquals(viewDatabase.getViewState().isEmpty(), true);
    }

    @Test
    void setCurrentDeckState() {
        ViewDatabase viewDatabase = new ViewDatabase();
        CurrentDeckState currentDeckState = new CurrentDeckState();
        viewDatabase.setCurrentDeckState(currentDeckState.getPowerupdeck());
        assertEquals(viewDatabase.getCurrentDeckState(), currentDeckState.getPowerupdeck());
    }

    @Test
    void setClientToken() {
        ViewDatabase viewDatabase = new ViewDatabase();
        viewDatabase.setClientToken(12);
        assertEquals(viewDatabase.getClientToken(), 12);
    }

    @Test
    void setPositionHashMap() {
        ViewDatabase viewDatabase = new ViewDatabase();
        HashMap<Player, Position> hashMap = new HashMap<>();
        Position position = new Position();
        Cell cell = new Cell(1);
        position.setCurrentcell(cell);
        hashMap.put(Player.YELLOW, position);
        viewDatabase.setPositionHashMap(hashMap);
        assertEquals(viewDatabase.getPositionHashMap().get(Player.YELLOW).getCurrentcell().getCellId(), 1);
    }
}