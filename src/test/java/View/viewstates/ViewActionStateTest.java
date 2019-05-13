package View.viewstates;

import Model.Action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewActionStateTest {

    @Test
    void setAction(){
        ViewActionState viewActionState = new ViewActionState();
        assertEquals(viewActionState.setAction("wrong"), null);
        assertEquals(viewActionState.setAction("shoot").getAction(), Action.SHOOT);
        assertEquals(viewActionState.setAction("pickup").getAction(), Action.PICK_UP);
        assertEquals(viewActionState.setAction("move").getAction(), Action.MOVE);
        assertEquals(viewActionState.setAction("endturn").getAction(), Action.ENDTURN);
    }
}