//Author: Federico Scatà
package View;

import Model.Map;

//Classe che memorizza lo stato corrente della mappa
public class ViewMapState {
    private Map CurrentMap; //memorizza lo stato della mappa corrente

    //costruttore
    public ViewMapState(){
        this.CurrentMap=null;
    }

    //ritorna lo stato della mappa
    public Map getSelectedMap() {
        return this.CurrentMap;
    }

    //setta lo stato della mappa
    public void setSelectedMap(Map selectedMap) {
        this.CurrentMap = selectedMap;
    }
}
