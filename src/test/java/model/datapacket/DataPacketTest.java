package model.datapacket;

import model.map.Cell;
import model.map.Position;
import model.map.SpawnPoint;
import model.munitions.Munitions;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.powerups.PowerUpId;
import model.powerups.TagbackGrenade;
import model.weaponscard.Thor;
import model.weaponscard.Weapon;
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
        dataPacket.setStatesEnum(StatesEnum.SHOOT_SECOND);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.SHOOT_SECOND);
        dataPacket.setStatesEnum(StatesEnum.SHOOT_THIRD);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.SHOOT_THIRD);
        dataPacket.setStatesEnum(StatesEnum.ACTION);
        assertEquals(dataPacket.getStatesEnum(), StatesEnum.ACTION);
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
    void setTargetPlayers() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.getTargetPlayersFirst().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersFirst().contains(Player.BLUE), true);
        dataPacket.getTargetPlayersSecond().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersSecond().contains(Player.BLUE), true);
        dataPacket.getTargetPlayersThird().add(Player.BLUE);
        assertEquals(dataPacket.getTargetPlayersThird().contains(Player.BLUE), true);
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
    void setFirstAttack() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setFirstAttack(true);
        assertEquals(dataPacket.isFirstAttack(), true);
    }

    @Test
    void isFirstAttack() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.isFirstAttack(), false);
    }

    @Test
    void getWeaponsToBeRecharged() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getWeaponsToBeRecharged().isEmpty(), true);
    }

    @Test
    void getMarksToAdd() {
        DataPacket dataPacket = new DataPacket();
        assertEquals(dataPacket.getMarksToAdd().isEmpty(), true);
    }

    @Test
    void setPosition() {
        DataPacket dataPacket = new DataPacket();
        Position position = new Position();
        dataPacket.setPosition(position);
        assertEquals(dataPacket.getPosition(), position);
    }

    @Test
    void setPowerUpId() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPowerUpId(PowerUpId.TAGBACK_GRENADE);
        assertEquals(dataPacket.getPowerUpId(), PowerUpId.TAGBACK_GRENADE);
    }

    @Test
    void setTargetPlayerPowerup() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setTargetPlayerPowerup(Player.YELLOW);
        assertEquals(dataPacket.getTargetPlayerPowerup(), Player.YELLOW);
    }

    @Test
    void setPowerupAction() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPowerupAction(true);
        assertEquals(dataPacket.isPowerupAction(), true);
    }

    @Test
    void setMunitions() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setMunitions(Munitions.BLUE);
        assertEquals(dataPacket.getMunitions(), Munitions.BLUE);
    }

    @Test
    void setPowerUpToKeepSpawn() {
        DataPacket dataPacket = new DataPacket();
        PowerUp powerUp = new TagbackGrenade(Munitions.RED);
        dataPacket.setPowerUpToKeepSpawn(powerUp);
        assertEquals(dataPacket.getPowerUpToKeepSpawn().getId(), powerUp.getId());
    }

    @Test
    void setPowerUpSpawn() {
        DataPacket dataPacket = new DataPacket();
        PowerUp powerUp = new TagbackGrenade(Munitions.RED);
        dataPacket.setPowerUpSpawn(powerUp);
        assertEquals(dataPacket.getPowerUpSpawn().getId(), powerUp.getId());
    }

    @Test
    void setWeaponlistempty() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setWeaponlistempty(true);
        assertEquals(dataPacket.isWeaponlistempty(), true);
    }

    @Test
    void setFrenzy() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setFrenzy(true);
        assertEquals(dataPacket.isFrenzy(), true);
    }

    @Test
    void setPowerUpColor() {
        DataPacket dataPacket = new DataPacket();
        dataPacket.setPowerUpColor(Munitions.BLUE);
        assertEquals(dataPacket.getPowerUpColor(), Munitions.BLUE);
    }

    @Test
    void setSpawnPointToAttack() {
        DataPacket dataPacket = new DataPacket();
        SpawnPoint spawnPoint = new SpawnPoint(Munitions.BLUE);
        dataPacket.setSpawnPointToAttack(spawnPoint);
        assertEquals(dataPacket.getSpawnPointToAttack().getSpawnColor(), Munitions.BLUE);
    }
}