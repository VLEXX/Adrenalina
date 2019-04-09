//Author: Giulia Rivara
package Model;

import java.util.HashMap;

//Classe che tiene conto del riquadro danni della plancia giocatore
public class DamageBox {
    private Player[] damage;                                    //Mappa per tenere conto del colore del danno e del numero di danni
    private int damageTot;                                      //Totale dei danni subiti
    private boolean pickUp;                                     //Potenziamento per l'aver subito i danni: due mosse prima di raccogliere
    private boolean shootUp;                                    //Potenziamento per aver subito danni: una mossa prima di sparare
    private int maxPointIndex;                                  //Indice del punteggio massimo, a seconda di quante volte un pg è morto si parte da un tot di punti minore da distribuire
    private final static int[] maxPointArray = {8,6,4,2,1,1};   //Punti per chi contribuisce al danno quando viene contegguata la plancia
    private final static int[] finalFrenzyArray = {2,1,1,1};    //Punti per chi contribuisce al danno quando viene conteggiata la plancia in frenesia finale
    private boolean activeFrenzy;                               //Settato a "false" se la modalità frenesia non si attiva, "true" il contrario
    private int deathCounter;                                   //Contatore delle morti del giocatore
    private int damageIndex;                                    //Indice per avere l'ultimo danno
    private Player firstBlood;                                  //Giocatore che ha colpito per primo un avversario
    private Player mortalDamage;                                //Giocatore che ha ucciso l'avversario
    private Player rage;                                        //Infierire

    //Costruttore che imposta la plancia dei danni del giocatore
    public DamageBox(){
        this.damage = new Player[] {null, null, null, null, null, null, null, null, null, null, null, null};
        this.damageTot = 0;
        this.pickUp = false;
        this.shootUp = false;
        this.maxPointIndex = 0;
        this.activeFrenzy = false;
        this.deathCounter = 0;
        this.damageIndex = 0;
        this.firstBlood = null;
        this.mortalDamage = null;
        this.rage = null;
    }

    //Ritorna i danni giocatore
    public Player[] getDamage() {
        return damage;
    }

    //Setta i danni giocatore
    public void setDamage(Player damage) {
        this.damage[damageIndex] = damage;
        damageIndex++;
    }

    //Ritorna l'indice dei danni
    public int getDamageIndex() {
        return damageIndex;
    }

    //Aggiorna l'indice dei danni
    public void setDamageIndex(int damageIndex) {
        this.damageIndex = damageIndex;
    }

    //Ritorna il primo giocatore che ha colpito
    public Player getFirstBlood() {
        return firstBlood;
    }

    //Setta il primo giocatore che colpisce l'avversario
    public void setFirstBlood(Player firstBlood) {
        this.firstBlood = firstBlood;
    }

    //Ritorna il giocatore che ha ucciso l'avversario
    public Player getMortalDamage() {
        return mortalDamage;
    }

    //Indica chi ha ucciso l'avversario
    public void setMortalDamage(Player mortalDamage) {
        this.mortalDamage = mortalDamage;
    }

    //Ritorna chi ha infierito l'ultimo danno
    public Player getRage() {
        return rage;
    }

    //Imposta chi ha infierito l'ultimo danno
    public void setRage(Player rage) {
        this.rage = rage;
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
}
