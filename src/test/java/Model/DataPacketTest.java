package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataPacketTest {

    @Test
    void getCell() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void getPlayer() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getPlayer(), null);
    }

    @Test
    void setCell() {
        DataPacket dataPacket = new DataPacket();
        Cell cell = new Cell(1);
        dataPacket.setCell(cell);
        assertEquals(dataPacket.getCell().getCellId(), 1);
    }

    @Test
    void setPlayer() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(dataPacket.getPlayer(), Player.YELLOW);
    }

    @Test
    void getStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void setStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.END);
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MOVE);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.PICK_UP);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.SHOOT);
        dataPacket.setStatesEnum(StatesEnum.MID);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MID);
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.START);
        dataPacket.setStatesEnum(StatesEnum.WAIT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.WAIT);
    }
}