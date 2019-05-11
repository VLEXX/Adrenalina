/**
 * @author Federico Scatà
 */
package Model;

import java.io.Serializable;

//Classe che identifica i vari potenziamenti
public class PowerUp implements Serializable {
    private final Munitions color;
    private final PowerUpId id;

    //costruttore
    public PowerUp(PowerUpId i, Munitions m){
        this.color=m;
        this.id=i;
    }

    public PowerUpId getId() {
        return id;
    }

    public Munitions getColor() {
        return color;
    }

    public int munitionsChecker(Munitions m){
        if(this.color==m)
            return 1;
        else
            return 0;
    }
}

//Coverage nullo per ora. In attesa di classe PlayerBoard (CANCELLARE QUESTO COMMENTO QUANDO OK)