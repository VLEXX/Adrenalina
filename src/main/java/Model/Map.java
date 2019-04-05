//Author: Alex Saletti
package Model;

public class Map {
    private String mapname;     //nome della mappa


    public Map() {                   //costruttore
        mapname = null;
    }       //costruttore

    public String getMapname() {   //restituisce il nome della mappa
        return mapname;
    }       //restituisce il nome della mappa

    public void setMapname(String newname) {       //setta il nome della mappa
        mapname = newname;
    }       //permette di impostare il nome della mappa

}




