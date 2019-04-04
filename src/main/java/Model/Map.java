//Author: Alex Saletti
package Model;

public class Map {
    private String MapName;     //nome della mappa


    public Map() {                   //costruttore
        MapName = null;
    }       //costruttore

    public String getMapName() {   //restituisce il nome della mappa
        return MapName;
    }       //restituisce il nome della mappa

    public void setMapName(String newname) {       //setta il nome della mappa
        MapName = newname;
    }       //permette di impostare il nome della mappa

}




