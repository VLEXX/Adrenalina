package Model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DataPacketTest {

    @Test
    void getPlayerstatemap() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        assertEquals(d.getPlayerstatemap().get(Player.YELLOW), null);
        assertEquals(d.getPlayerstatemap().get(Player.BLACK), null);
        assertEquals(d.getPlayerstatemap().get(Player.BLUE), null);
        DataPacket d1 = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE, Player.GREEN);
        assertEquals(d1.getPlayerstatemap().get(Player.YELLOW), null);
        assertEquals(d1.getPlayerstatemap().get(Player.BLACK), null);
        assertEquals(d1.getPlayerstatemap().get(Player.BLUE), null);
        assertEquals(d1.getPlayerstatemap().get(Player.GREEN), null);
        DataPacket d2 = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE, Player.GREEN, Player.PURPLE);
        assertEquals(d2.getPlayerstatemap().get(Player.YELLOW), null);
        assertEquals(d2.getPlayerstatemap().get(Player.BLACK), null);
        assertEquals(d2.getPlayerstatemap().get(Player.BLUE), null);
        assertEquals(d2.getPlayerstatemap().get(Player.GREEN), null);
        assertEquals(d2.getPlayerstatemap().get(Player.PURPLE), null);
    }

    @Test
    void getDatamap() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        assertEquals(d.getDatamap(), null);
    }

    @Test
    void getDatachart() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        assertEquals(d.getDatachart(), null);
    }

    @Test
    void updatePlayerstate() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        CurrentPlayerState c = new CurrentPlayerState();
        c.setActiveplayer(Player.YELLOW);
        d.updatePlayerstate(Player.YELLOW,c);
        assertEquals(d.getPlayerstatemap().get(Player.YELLOW).getActiveplayer(), Player.YELLOW);
    }

    @Test
    void setDatamap() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        Map m = new Map();
        d.setDatamap(m);
        assertEquals(d.getDatamap(), m);
    }

    @Test
    void setDatachart() {
        DataPacket d = new DataPacket(Player.YELLOW, Player.BLACK, Player.BLUE);
        ChartScore c = new ChartScore();
        d.setDatachart(c);
        assertEquals(d.getDatachart(), c);
    }
}