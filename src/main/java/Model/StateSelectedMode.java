//Author: Federico Scatà
package Model;

//Classe che memorizza la modalità corrente selezionata del gioco
public class StateSelectedMode {
    private Mode SelectedMode;  //modalità selezionata del gioco

    //Costruttore che inizializza la modalità a "null"
    public StateSelectedMode(){
        SelectedMode=null;
    }

    //Ritorna la mappa selezionata
    public Mode getSelectedMode() {
        return SelectedMode;
    }

    //Setta la modalità selezionata
    public void setSelectedMode(Mode selectedMode) {
        SelectedMode = selectedMode;
    }
}
