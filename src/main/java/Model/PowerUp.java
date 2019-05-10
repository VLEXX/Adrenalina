/**
 * @author Federico Scatà
 */
package Model;

import java.io.Serializable;

//Classe che identifica i vari potenziamenti
public class PowerUp implements Serializable {
    private final PowerUpId Id;
    private final Munitions Color;

    //costruttore
    public PowerUp(PowerUpId Id,Munitions m){
        this.Id=Id;
        this.Color=m;
    }

    public PowerUpId getId() {
        return Id;
    }

    public Munitions getColor() {
        return Color;
    }

    public int munitionsChecker(Munitions m){
        if(this.Color==m)
            return 1;
        else
            return 0;
    }
}

//Coverage nullo per ora. In attesa di classe PlayerBoard (CANCELLARE QUESTO COMMENTO QUANDO OK)