//Author: Federico Scatà
package View;

import Model.Map;

//Classe che memorizza lo stato corrente della mappa
public class ViewMapState {
    private Map currentmap; //memorizza lo stato della mappa corrente

    //costruttore
    public ViewMapState(){
        this.currentmap =null;
    }

    //ritorna lo stato della mappa
    public Map getSelectedMap() {
        return this.currentmap;
    }

    //setta lo stato della mappa
    public void setSelectedMap(Map selectedMap) {
        this.currentmap = selectedMap;
    }
}
