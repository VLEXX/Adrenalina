package ServerController;

import Model.Action;

public class ControllerAction {

    public boolean controlActionReceived(Action action){
        if(action.equals(Action.values()) ){
            return true;
        }
        else{
            return false;
        }
    }
}
