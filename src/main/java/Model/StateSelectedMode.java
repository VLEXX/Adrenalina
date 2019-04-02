//Author: Federico Scatà
package Model;

public class StateSelectedMode {
    private Mode SelectedMode;

    public StateSelectedMode(){
        SelectedMode=null;
    }

    public Mode getSelectedMode() {
        return SelectedMode;
    }

    public void setSelectedMode(Mode selectedMode) {
        SelectedMode = selectedMode;
    }
}
