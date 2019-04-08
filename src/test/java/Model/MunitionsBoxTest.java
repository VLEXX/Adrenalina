package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MunitionsBoxTest {

    @Test
    void setMyMunitionsMap() {
        Player player = Player.BLUE;
        int munitions = 2;
        MunitionsBox munitionsBox = new MunitionsBox();
        munitionsBox.setMyMunitionsMap(player, munitions);
        assertEquals(munitionsBox.getMyMunitionsMap().size(), 1);
        assertEquals(munitionsBox.getMyMunitionsMap().get(player).intValue(), 2);
        munitionsBox.setMyMunitionsMap(player, 2);
        assertEquals(munitionsBox.getMyMunitionsMap().get(player).intValue(), 4);
    }
}