package view;

import model.playerdata.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientManagerTest {

    @Test
    void chooseCharacter() {
        ClientManager clientManager = new ClientManager();
        for(Player player: Player.values()){
            String s = player.toString();
            assertEquals(clientManager.chooseCharacter(s), player);
        }
    }
}