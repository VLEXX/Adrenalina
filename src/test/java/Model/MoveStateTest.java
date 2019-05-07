package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveStateTest {

    @Test
    void cellFinder() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(1);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        cps.setPlayerposition(p);
        i.getCurrentPlayerState().add(cps);
        MoveState ms = new MoveState();
        assertEquals(ms.cellFinder(i,"1",Player.BLUE).getCellId(),i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0).getCellId());
        assertEquals(ms.cellFinder(i,"22",Player.BLUE),null);
        assertEquals(ms.cellFinder(i, "aaajkg",Player.BLUE), null);

    }

    @Test
    void setMove() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        cps.setPlayerposition(p);
        i.getCurrentPlayerState().add(cps);
        MoveState ms = new MoveState();
        assertEquals(ms.setMove(i,i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1),Player.BLUE),0);




    }

    @Test
    void cellIsReachable() {
        InitializeAllPlay i = new InitializeAllPlay();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState ps = new CurrentPlayerState(Player.BLUE);
        Position p = new Position();
        p.setCurrentroom(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0));
        p.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(1));
        ps.setPlayerposition(p);
        i.getCurrentPlayerState().add(ps);
        MoveState ms = new MoveState();
        assertTrue(ms.cellIsReachable(i,Player.BLUE,i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0)));
        assertFalse(ms.cellIsReachable(i,Player.BLUE,i.getStateSelectedMap().getSelectedmap().getRoomList().get(4).getCellsList().get(0)));

    }
}