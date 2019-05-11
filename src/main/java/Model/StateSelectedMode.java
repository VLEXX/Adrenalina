/**
 * @author Federico Scatà
 */
package Model;

//Classe che memorizza la modalità corrente selezionata del gioco
public class StateSelectedMode {
    private Mode selectedmode;  //modalità selezionata del gioco

    //Costruttore che inizializza la modalità a "null"
    public StateSelectedMode() {
        selectedmode = null;
    }

    //Ritorna la mappa selezionata
    public Mode getSelectedmode() {
        return selectedmode;
    }

    //Setta la modalità selezionata
    public void setSelectedmode(Mode selectedmode) {
        this.selectedmode = selectedmode;
    }
}
