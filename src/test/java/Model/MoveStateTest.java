package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveStateTest {

    @Test
    void doAction() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(1);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState ps = new CurrentPlayerState(Player.BLUE);
        ps.setActiveturn(true);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        ps.setPlayerposition(p);
        i.getCurrentPlayerState().add(ps);
        MoveState ms = new MoveState();
        //ms.doAction(i,i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1),Player.BLUE);
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentcell(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentroom(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        //ms.doAction(i,i.getStateSelectedMap().getSelectedmap().getRoomList().get(4).getCellsList().get(0),Player.BLUE);
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentcell(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentroom(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        i.getCurrentPlayerState().get(0).setActiveturn(false);
        //ms.doAction(i,i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0),Player.BLUE);
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentcell(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        assertEquals(i.getCurrentPlayerState().get(0).getPlayerposition().getCurrentroom(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));

    }

    @Test
    void cellFinder() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(1);
        i.getStateSelectedMap().setSelectedmap();
        MoveState ms = new MoveState();
        assertEquals(ms.cellFinder(i,"1").getCellId(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).getCellId());
        assertEquals(ms.cellFinder(i,"22"),null);
        assertEquals(ms.cellFinder(i, "aaajkg"), null);

    }
}