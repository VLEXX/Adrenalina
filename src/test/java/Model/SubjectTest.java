package Model;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.Current;

import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    @Test
    void addObserver() {
        CurrentPlayerState c = new CurrentPlayerState();
        CurrentTurnState s = new CurrentTurnState();
        s.addObserver(c);
        assertEquals(s.getObservers().get(0), c);
    }

    @Test
    void removeObserver() {
        CurrentPlayerState c = new CurrentPlayerState();
        CurrentPlayerState t = new CurrentPlayerState();
        CurrentTurnState s = new CurrentTurnState();
        s.addObserver(c);
        s.addObserver(t);
        s.removeObserver(c);
        assertEquals(s.getObservers().get(0), t);
    }

    @Test
    void notifyObserver() {
    }

    @Test
    void getObservers(){
        CurrentTurnState s = new CurrentTurnState();
        CurrentPlayerState c = new CurrentPlayerState();
        s.addObserver(c);
        assertEquals(s.getObservers().get(0), c);
    }
}