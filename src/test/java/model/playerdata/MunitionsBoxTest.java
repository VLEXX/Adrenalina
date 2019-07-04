/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.munitions.Munitions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MunitionsBoxTest {

    @Test
    void increaseMyMunitionsBox() {
        MunitionsBox m = new MunitionsBox();
        m.increaseMyMunitionsBox(Munitions.RED, 2);
        assertEquals(m.getMyMunitionsMap().get(Munitions.RED), 5);
    }

    @Test
    void decreaseMyMunitionsBox() {
        MunitionsBox m = new MunitionsBox();
        m.decreaseMyMunitionsBox(Munitions.YELLOW, 1);
        assertEquals(m.getMyMunitionsMap().get(Munitions.YELLOW), 2);
    }
}