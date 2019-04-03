//Author: Federico Scatà
package Model;

//Classe che memorizza gli ID dei client che si collegano al server (da 3 a 5 client)
public class IDClientList {
    private int[] ClientList;   //Array che memorizza gli ID

    //Costruttore che inizializza l'array a 0 (gli ID assegnati sono diversi da 0)
    public IDClientList(){
        this.ClientList = new int[]{0,0,0,0,0};
    }

    //Ritorna l'array degli ID Client
    public int[] getClientList() {
        return this.ClientList;
    }

    //Setta nella cella corretta, a seconda dell'indice passato, l'ID da memorizzare
    public void setClientList(int index, int id) {
        this.ClientList[index] = id;
    }
}
