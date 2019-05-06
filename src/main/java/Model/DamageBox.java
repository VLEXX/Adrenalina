//Author: Giulia Rivara
package Model;

import java.util.HashMap;

//Classe che tiene conto del riquadro danni della plancia giocatore
public class DamageBox {
    private Player[] damage;                                    //Array per tener conto dell'ordine dei danni dei giocatori
    private int damageTot;                                      //Totale dei danni subiti
    private boolean pickUp;                                     //Potenziamento per l'aver subito i danni: due mosse prima di raccogliere
    private boolean shootUp;                                    //Potenziamento per aver subito danni: una mossa prima di sparare
    private int maxPointIndex;                                  //Indice del punteggio massimo, a seconda di quante volte un pg è morto si parte da un tot di punti minore da distribuire
    private final static int[] maxPointArray = {8,6,4,2,1,1};   //Punti per chi contribuisce al danno quando viene contegguata la plancia
    private final static int[] finalFrenzyArray = {2,1,1,1};    //Punti per chi contribuisce al danno quando viene conteggiata la plancia in frenesia finale
    private boolean activeFrenzy;                               //Settato a "false" se la modalità frenesia non si attiva, "true" il contrario
    private int deathCounter;                                   //Contatore delle morti del giocatore
    private boolean dead;                                       //Giocatore morto se true

    //Costruttore che imposta la plancia dei danni del giocatore
    public DamageBox(){
        this.damage = new Player[] {null, null, null, null, null, null, null, null, null, null, null, null};
        this.damageTot = 0;
        this.pickUp = false;
        this.shootUp = false;
        this.maxPointIndex = 0;
        this.activeFrenzy = false;
        this.deathCounter = 0;
        this.dead = false;
    }

    //Ritorna i danni giocatore
    public Player[] getDamage() {
        return damage;
    }

    //Imposta la modalità frenesia mettendo "true", "false" altrimenti
    public boolean isActiveFrenzy() {
        return activeFrenzy;
    }

    //Ritorna il numero delle morti
    public int getDeathCounter() {
        return deathCounter;
    }

    //Setta il contatore delle morti
    public void setDeathCounter(int deathCounter) {
        this.deathCounter = deathCounter;
    }

    //Indica se è possibile attivare il potenziamento
    public boolean isPickUp() {
        return pickUp;
    }

    //Indica se è possibile attivare il potenziamento
    public boolean isShootUp() {
        return shootUp;
    }

    //Setta la modalità frenesia "true" se attiva, "false" altrimenti
    public void setActiveFrenzy(boolean activeFrenzy) {
        this.activeFrenzy = activeFrenzy;
    }

    //Setta l'uso del potenziamento
    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    //Setta l'uso del potenziamento
    public void setShootUp(boolean shootUp) {
        this.shootUp = shootUp;
    }

    //Ritorna l'ultimo danno subito
    public int getDamageTot() {
        return damageTot;
    }

    //Setta i danni giocatore subiti
    public void setDamageTot(int damageTot) {
        this.damageTot = damageTot;
    }

    //Funzione che gestisce i danni
    public void increaseDamage(int damagePoint, Player player){
        for(int i = damageTot; i < damageTot + damagePoint; i++)
            damage[damageTot] = player;
        damageTot = damagePoint + damageTot;
        if(damageTot >= 3){
            pickUp = true;
        } else if(damageTot >= 6){
            shootUp = true;
        } else if(damageTot >= 11){
            dead = true;
        }
        deathCounter++;
        //TODO chiedere dove si gestisce le azioni da fare alla morte del giocatore
    }

    //Ritorna il punteggio da cui si parte per assegnare i punti a chi uccide
    public int getMaxPointIndex() {
        return maxPointIndex;
    }

    //Setta l'indice per assegnare il punteggio
    public void setMaxPointIndex(int maxPointIndex) {
        this.maxPointIndex = maxPointIndex;
    }

    //Restituisce il vettore con il valore dei punteggi
    public int[] getMaxPointArray() {
        return maxPointArray;
    }

    //Restituisce il vettore con il valore dei punteggi nella frenesia finale
    public int[] getFinalFrenzyArray() {
        return finalFrenzyArray;
    }

    //TODO test
    public boolean isDead() {
        return dead;
    }

    //TODO test
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
