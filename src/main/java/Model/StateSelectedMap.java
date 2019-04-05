//Author: Federico Scatà
package Model;

//Classe che memorizza la mappa corrente selezionata del gioco
public class StateSelectedMap {
    private Map selectedmap;    //mappa selezionata

    //Costruttore che inizializza la mappa selezionata a "null"
    public StateSelectedMap(){
        selectedmap =null;
    }

    //Ritorna la mappa selezionata
    public Map getSelectedmap() {
        return selectedmap;
    }

    //Setta la mappa selezionata
    public void setSelectedmap(Map selectedmap) {
        this.selectedmap = selectedmap;
    }
}

