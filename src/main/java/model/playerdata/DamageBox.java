/**
 * @author Giulia Rivara
 */
package model.playerdata;

import model.map.Map;

import java.io.*;

/**
 * Class is the damage box of the player board
 */
public class DamageBox implements Serializable{
    private final static int[] maxPointArray = {8,6,4,2,1,1,1,1,1,1,1,1,1,};   //Punti per chi contribuisce al danno quando viene contegguata la plancia
    private final static int[] finalFrenzyArray = {2, 1, 1, 1};    //Punti per chi contribuisce al danno quando viene conteggiata la plancia in frenesia finale
    private Player[] damage;                                    //Array per tener conto dell'ordine dei danni dei giocatori
    private int damageTot;                                      //Totale dei danni subiti
    private boolean pickUp;                                     //Potenziamento per l'aver subito i danni: due mosse prima di raccogliere
    private boolean shootUp;                                    //Potenziamento per aver subito danni: una mossa prima di sparare
    private int maxPointIndex;                                  //Indice del punteggio massimo, a seconda di quante volte un pg è morto si parte da un tot di punti minore da distribuire
    private boolean activeFrenzy;                               //Settato a "false" se la modalità frenesia non si attiva, "true" il contrario
    private int deathCounter;                                   //Contatore delle morti del giocatore
    private boolean dead;                                       //Giocatore morto se "true", "false" altrimenti

    /**
     * Constructor for the damage box
     */
    public DamageBox() {
        this.damage = new Player[]{null, null, null, null, null, null, null, null, null, null, null, null};
        this.damageTot = 0;
        this.pickUp = false;
        this.shootUp = false;
        this.maxPointIndex = 0;
        this.activeFrenzy = false;
        this.deathCounter = 0;
        this.dead = false;
    }

    /**
     * @return the damage
     */
    public Player[] getDamage() {
        return damage;
    }

    /**
     * @return true if the frenzy mode is active
     */
    public boolean isActiveFrenzy() {
        return activeFrenzy;
    }

    /**
     * Set true if the frenzy mode is active
     * @param activeFrenzy
     */
    public void setActiveFrenzy(boolean activeFrenzy) {
        this.activeFrenzy = activeFrenzy;
    }

    /**
     * @return the number of death
     */
    public int getDeathCounter() {
        return deathCounter;
    }

    /**
     * Set the death counter
     * @param deathCounter
     */
    public void setDeathCounter(int deathCounter) {
        this.deathCounter = deathCounter;
    }

    /**
     * @return true if the pickup is active
     */
    public boolean isPickUp() {
        return pickUp;
    }

    /**
     * Set true if the pickup is active
     * @param pickUp
     */
    public void setPickUp(boolean pickUp) {
        this.pickUp = pickUp;
    }

    /**
     * @return  true if shootup is active
     */
    public boolean isShootUp() {
        return shootUp;
    }

    /**
     * Set true if shootup is active
     * @param shootUp
     */
    public void setShootUp(boolean shootUp) {
        this.shootUp = shootUp;
    }

    /**
     * @return the total damage
     */
    public int getDamageTot() {
        return damageTot;
    }

    /**
     * Set the total damage
     * @param damageTot
     */
    public void setDamageTot(int damageTot) {
        this.damageTot = damageTot;
    }

    /**
     * Function increase damage of a player
     * @param damagePoint
     * @param player
     */
    public void increaseDamage(int damagePoint, Player player) {
        for (int i = damageTot; i < damageTot + damagePoint; i++) {
            if (i > 11)
                break;
            damage[i] = player;
        }
        damageTot = damagePoint + damageTot;
        if (damageTot > 2 && damageTot < 6) {
            pickUp = true;
        } else if (damageTot > 5 && damageTot < 11) {
            shootUp = true;
        } else if (damageTot >= 11) {
            dead = true;
        }
        deathCounter++;
    }

    /**
     * @return the max point to bring when the player die
     */
    public int getMaxPointIndex() {
        return maxPointIndex;
    }

    /**
     * Set the index for the max point
     * @param maxPointIndex
     */
    public void setMaxPointIndex(int maxPointIndex) {
        this.maxPointIndex = maxPointIndex;
    }

    /**
     * @return the array with the points
     */
    public int[] getMaxPointArray() {
        return maxPointArray;
    }

    /**
     * @return the array with the points for the final frenzy mode
     */
    public int[] getFinalFrenzyArray() {
        return finalFrenzyArray;
    }

    /**
     * @return true if a player is dead
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set false if a player is dead
     * @param dead
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Player[] deepCloneDamage() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(baos);
        objectOutputStream.writeObject(this.damage);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream objectInputStream = new ObjectInputStream(bais);
        return (Player[]) objectInputStream.readObject();
    }
}
