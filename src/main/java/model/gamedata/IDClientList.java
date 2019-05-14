/**
 * @author Federico Scatà
 */
package model.gamedata;

//Classe che memorizza gli ID dei client che si collegano al server (da 3 a 5 client)
public class IDClientList {
    private int[] clientlist;   //Array che memorizza gli ID

    //Costruttore che inizializza l'array a 0 (gli ID assegnati sono diversi da 0)
    public IDClientList() {
        this.clientlist = new int[]{0, 0, 0, 0, 0};
    }

    //Ritorna l'array degli ID Client
    public int[] getClientlist() {
        return this.clientlist;
    }

    //Setta nella cella corretta, a seconda dell'indice passato, l'ID da memorizzare
    public void setClientList(int index, int id) {
        this.clientlist[index] = id;
    }
}
