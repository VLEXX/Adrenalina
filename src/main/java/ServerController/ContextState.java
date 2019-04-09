package ServerController;

public class ContextState {
    private State state;

    public ContextState(){
        state = null;
    }

    public void setState(State state){
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
