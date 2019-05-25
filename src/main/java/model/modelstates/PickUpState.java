/**
 * @author Alex Saletti
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.map.Room;
import model.munitions.Ammo;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.MunitionsBox;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Class that manages the PickUp Action
 */
public class PickUpState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    /**
     * Class constructor
     */
    public PickUpState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap) {
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }


    /**
     * @param dataPacket
     * @return MessageEnum
     */
    @Override
    public MessageEnum doAction(DataPacket dataPacket) throws RemoteException {
        Weapon ww = null;
        int pwcheck = 0;
        if (dataPacket.isWeaponlistempty()) {
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
            return MessageEnum.OK;
        }
        //controlla se il rimpiazzo dell'arma è possibile e necessario
        if (dataPacket.getReplaceWeapon() != null) {
            for (Weapon wp : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList()) {
                if (wp.getName().equals(dataPacket.getReplaceWeapon().getName()))
                    ww = wp;
            }
            if (ww == null || allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList().size() < 3)
                return MessageEnum.WEAPON_ERROR_2;
        }
        //controlla se i powerup siano effettivamente posseduti
        for (PowerUp pw : dataPacket.getPaymentPowerUp()) {
            for (PowerUp pw3 : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
                if (pw.getId() == pw3.getId() && pw.getColor() == pw3.getColor())
                    pwcheck++;
            }
        }
        if (pwcheck != dataPacket.getPaymentPowerUp().size())
            return MessageEnum.POWERUP_NOT_FOUND;
        Cell cc = null;
        //controlla se la cella scelta in caso di movimento sia raggiungibile
        if (dataPacket.getCell() != null) {
            for (Room room : allPlay.getStateSelectedMap().getSelectedmap().getRoomList()) {
                for (Cell cell : room.getCellsList()) {
                    if (cell.getCellId() == dataPacket.getCell().getCellId())
                        cc = cell;
                }
            }
        }
        Cell ccv = allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell();
        if (cc != null) {
            boolean a;
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getDamageBox().getDamage()[2] == null)
                a = (cc == ccv.getDownCell() || cc == ccv.getLeftCell() || cc == ccv.getUpCell() || cc == ccv.getRightCell());
            else
                a = ccv.getReachable2Cells().contains(cc);
            if (!a)
                return MessageEnum.UNREACHABLE_CELL;
        }
        //imposta la cella da verificare per controllare le armi presenti
        if (cc != null)
            ccv = cc;
        //verifica la presenza di armi o munizioni, in caso di armi la possibilità di pagarle
        HashMap<Munitions, Integer> cost = new HashMap<>();
        cost.put(Munitions.RED, 0);
        cost.put(Munitions.YELLOW, 0);
        cost.put(Munitions.BLUE, 0);
        ArrayList<PowerUp> toRemove2 = new ArrayList<>();
        Weapon www = dataPacket.getWeapon();
        if (ccv.getSpawnpointzone() != null) {
            boolean b = false;
            for (Weapon w2 : ccv.getSpawnpointzone().getSpawnWeaponsList()) {
                if (w2.getName().equals(www.getName())) {
                    b = true;
                    www = w2;
                }
            }
            if (!b)
                return MessageEnum.WEAPON_NOT_FOUND;
            if (www.getFirstPrice().containsKey(Munitions.RED))
                cost.put(Munitions.RED, www.getFirstPrice().get(Munitions.RED) - www.munitionsChecker(Munitions.RED));
            if (www.getFirstPrice().containsKey(Munitions.YELLOW))
                cost.put(Munitions.YELLOW, www.getFirstPrice().get(Munitions.YELLOW) - www.munitionsChecker(Munitions.YELLOW));
            if (www.getFirstPrice().containsKey(Munitions.BLUE))
                cost.put(Munitions.BLUE, www.getFirstPrice().get(Munitions.BLUE) - www.munitionsChecker(Munitions.BLUE));
            dataPacket.getPaymentPowerUp().forEach(powerUp -> {
                for (PowerUp pw2 : allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList()) {
                    if (powerUp.getColor() == pw2.getColor() && powerUp.getId() == pw2.getId())
                        toRemove2.add(pw2);
                }
                cost.put(Munitions.RED, cost.get(Munitions.RED) - powerUp.munitionsChecker(Munitions.RED));
                cost.put(Munitions.YELLOW, cost.get(Munitions.YELLOW) - powerUp.munitionsChecker(Munitions.YELLOW));
                cost.put(Munitions.BLUE, cost.get(Munitions.BLUE) - powerUp.munitionsChecker(Munitions.BLUE));
            });
            if (cost.get(Munitions.RED) < 0 || cost.get(Munitions.YELLOW) < 0 || cost.get(Munitions.BLUE) < 0)
                return MessageEnum.TOO_MUCH_POWERUPS;
            HashMap<Munitions, Integer> mb = allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().getMyMunitionsMap();
            if (mb.get(Munitions.RED) - cost.get(Munitions.RED) < 0 || mb.get(Munitions.YELLOW) - cost.get(Munitions.YELLOW) < 0 || mb.get(Munitions.BLUE) - cost.get(Munitions.BLUE) < 0)
                return MessageEnum.AMMO_ERROR;
        }


        //fine controlli e inizio modifica del model
        //settaggio posizione
        Cell ccv2 = ccv;
        allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentcell(ccv);
        allPlay.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> {
            if (room.getCellsList().contains(ccv2)) {
                allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().setCurrentroom(room);
            }
        });
        if (ccv.getSpawnpointzone() == null) {
            CurrentPlayerState cps = allPlay.getCurrentPlayerState().get(dataPacket.getPlayer());
            Ammo a = cps.getPlayerposition().getCurrentcell().getAmmohere();
            if (a.getAmmoList()[0] + cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) < 4)
                cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.RED, a.getAmmoList()[0]);
            else
                cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.RED, 3);
            if (a.getAmmoList()[1] + cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) < 4)
                cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.YELLOW, a.getAmmoList()[1]);
            else
                cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW, 3);
            if (a.getAmmoList()[2] + cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) < 4)
                cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.BLUE, a.getAmmoList()[2]);
            else
                cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE, 3);
            if (a.getPossiblePowerUp() && cps.getBoard().getPowerupList().size() < 3)
                cps.getBoard().getPowerupList().add(allPlay.getCurrentDeckState().getPowerupdeck().pop());
            allPlay.getCurrentDeckState().getAmmodeck().push(a);
            Collections.shuffle(allPlay.getCurrentDeckState().getAmmodeck());
            cps.getPlayerposition().getCurrentcell().setAmmohere(null);
        } else {
            for (int j = 0; j < 3; j++) {
                if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()[j] == www) {
                    www.setLoaded(true);
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList().add(www);
                    if (ww != null)
                        allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getWeaponsList().remove(ww);
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.RED, cost.get(Munitions.RED));
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.YELLOW, cost.get(Munitions.YELLOW));
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.BLUE, cost.get(Munitions.BLUE));
                    allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()[j] = ww;
                }
            }
            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getBoard().getPowerupList().removeAll(toRemove2);
            allPlay.getCurrentDeckState().getPowerupdeck().addAll(toRemove2);
            Collections.shuffle(allPlay.getCurrentDeckState().getPowerupdeck());
        }
        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getActioncounter() == 2) {
            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.ACTION));
        } else {
            allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).decreaseActionCounter();
            allPlay.getHashMapState().replace(dataPacket.getPlayer(), stateHashMap.get(StatesEnum.END));

        }
        return MessageEnum.OK;
    }
}
        










