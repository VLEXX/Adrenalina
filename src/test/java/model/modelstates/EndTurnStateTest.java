package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Position;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.playerdata.PlayerBoard;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EndTurnStateTest {

    @Test
    void doAction() {
        InitializeAllPlay i = new InitializeAllPlay();
        HashMap<StatesEnum, State> statesHM = new HashMap<>();
        i.getStateSelectedMap().setStrategyMap(0);
        i.getStateSelectedMap().setSelectedmap();
        CurrentPlayerState cps1 = new CurrentPlayerState(Player.YELLOW);
        CurrentPlayerState cps2 = new CurrentPlayerState(Player.BLUE);
        Position p1 = new Position();
        Position p2 = new Position();
        p1.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        p2.setCurrentcell(i.getStateSelectedMap().getSelectedmap().getRoomList().get(0).getCellsList().get(0));
        PlayerBoard pb1= new PlayerBoard();
        PlayerBoard pb2 = new PlayerBoard();
        pb2.getDamageBox().setDead(true);
        pb2.getDamageBox().increaseDamage(12,Player.YELLOW);
        DataPacket d = new DataPacket();
        d.setPlayer(Player.YELLOW);
        EndTurnState eds = new EndTurnState(i,statesHM);
        cps1.setPlayerposition(p1);
        cps2.setPlayerposition(p2);
        cps1.setActiveturn(true);
        cps1.setBoard(pb1);
        cps2.setBoard(pb2);
        i.getCurrentPlayerState().put(Player.YELLOW,cps1);
        i.getCurrentPlayerState().put(Player.BLUE,cps2);

        assertEquals(eds.doAction(d),MessageEnum.OK);

    }
}