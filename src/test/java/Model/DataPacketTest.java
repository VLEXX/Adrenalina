package Model;

import Model.Powerups.TagbackGrenade;
import Model.WeaponsCard.Thor;
import Model.WeaponsCard.Weapon;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DataPacketTest {

    @Test
    void getCell() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void getPlayer() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getPlayer(), null);
    }

    @Test
    void setCell() {
        DataPacket dataPacket = new DataPacket();
        Cell cell = new Cell(1);
        dataPacket.setCell(cell);
        assertEquals(dataPacket.getCell().getCellId(), 1);
    }

    @Test
    void setPlayer() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPlayer(Player.YELLOW);
        assertEquals(dataPacket.getPlayer(), Player.YELLOW);
    }

    @Test
    void getStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getCell(), null);
    }

    @Test
    void setStatesEnum() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setStatesEnum(StatesEnum.END);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.END);
        dataPacket.setStatesEnum(StatesEnum.MOVE);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MOVE);
        dataPacket.setStatesEnum(StatesEnum.PICK_UP);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.PICK_UP);
        dataPacket.setStatesEnum(StatesEnum.SHOOT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.SHOOT);
        dataPacket.setStatesEnum(StatesEnum.MID);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.MID);
        dataPacket.setStatesEnum(StatesEnum.START);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.START);
        dataPacket.setStatesEnum(StatesEnum.WAIT);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.WAIT);
    }


    @Test
    void setWeapon() {
        DataPacket dataPacket = new DataPacket();
        Weapon weapon = new Thor();
        dataPacket.setWeapon(weapon);
        assertEquals(dataPacket.getWeapon(), weapon);
    }

    @Test
    void getWeapon() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getWeapon(), null);
    }

    @Test
    void setReplaceWeapon() {
        DataPacket dataPacket = new DataPacket();
        Weapon weapon = new Thor();
        dataPacket.setReplaceWeapon(weapon);
        assertEquals(dataPacket.getReplaceWeapon(), weapon);
    }

    @Test
    void getReplaceWeapon() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getReplaceWeapon(), null);
    }

    @Test
    void setPaymentPowerUp() {
        DataPacket dataPacket = new DataPacket();
        ArrayList<PowerUp> arrayList = new ArrayList<>();
        PowerUp powerUp = new TagbackGrenade(Munitions.RED);
        arrayList.add(powerUp);
        dataPacket.setPaymentPowerUp(arrayList);
        assertEquals(dataPacket.getPaymentPowerUp().contains(powerUp), true);
    }

    @Test
    void getPaymentPowerUp() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getPaymentPowerUp().isEmpty(), true);
    }

    @Test
    void getTargetPlayers() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getTargetPlayers().isEmpty(), true);
    }

    @Test
    void setTargetPlayers() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.getTargetPlayers().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayers().contains(Player.BLUE), true);
    }

    @Test
    void setSecondAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setSecondAttack(true);
        assertEquals(dataPacket.isSecondAttack(), true);
    }

    @Test
    void setThirdAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setThirdAttack(true);
        assertEquals(dataPacket.isThirdAttack(), true);
    }

    @Test
    void isSecondAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isSecondAttack(), false);
    }

    @Test
    void isThirdAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isThirdAttack(), false);
    }

    @Test
    void setAction() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setAction(Action.SHOOT);
        assertEquals(dataPacket.getAction(), Action.SHOOT);
    }

    @Test
    void getAction() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getAction(), null);
    }
}