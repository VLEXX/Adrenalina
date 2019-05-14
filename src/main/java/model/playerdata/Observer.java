/**
 * @author Federico Scatà
 */
package model.playerdata;

import model.playerdata.Player;

public interface Observer {
    public void update(Player p);
}