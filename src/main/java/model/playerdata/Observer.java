/**
 * @author Federico Scatà
 */
package model.playerdata;

import model.playerdata.Player;

public interface Observer {
    void update(Player p);
}