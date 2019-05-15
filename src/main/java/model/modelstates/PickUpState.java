/**
 * @author Alex Saletti
 */
package model.modelstates;

import model.datapacket.DataPacket;
import model.datapacket.MessageEnum;
import model.datapacket.StatesEnum;
import model.gamedata.InitializeAllPlay;
import model.map.Cell;
import model.munitions.Ammo;
import model.munitions.Munitions;
import model.playerdata.CurrentPlayerState;
import model.playerdata.Player;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Class that manages the PickUp Action
 */
public class PickUpState implements State {

    private InitializeAllPlay allPlay;
    private HashMap<StatesEnum, State> stateHashMap;

    public PickUpState(InitializeAllPlay initializeAllPlay, HashMap<StatesEnum, State> hashMap){
        this.allPlay = initializeAllPlay;
        this.stateHashMap = hashMap;
    }

    /**
     * Class constructor
     */
    public PickUpState(){}
     /**
     * @param dataPacket
     * @return MessageEnum
     */

    @Override
    public MessageEnum doAction(DataPacket dataPacket) {
        if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).isActiveturn()) {
            if (dataPacket.getCell() != null) {
                int a = this.moveOne(allPlay, dataPacket.getPlayer(), dataPacket.getCell());
                if (a == -1) {
                    return MessageEnum.UNREACHABLE_CELL;
                }
            }
            if (allPlay.getCurrentPlayerState().get(dataPacket.getPlayer()).getPlayerposition().getCurrentcell().getSpawnpointzone() == null) {
                if (this.pickUpAmmo(allPlay, dataPacket.getPlayer()) == 0)
                    return MessageEnum.OK;
                else
                    return MessageEnum.AMMO_ERROR;
            } else {
                if (this.pickUpWeapon(allPlay, dataPacket.getPlayer(), dataPacket.getWeapon(), dataPacket.getPaymentPowerUp(), dataPacket.getReplaceWeapon()) == 0)
                    return MessageEnum.OK;
                else
                    return MessageEnum.WEAPON_ERROR;
            }
        }
        return MessageEnum.NOT_YOUR_TURN;

    }


    //metodo che permette al player di spostarsi nella cella vicina
    private int moveOne(InitializeAllPlay i, Player p, Cell c) {
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
        if (cps.getPlayerposition().getCurrentcell().getUpCell() == c || cps.getPlayerposition().getCurrentcell().getDownCell() == c || cps.getPlayerposition().getCurrentcell().getLeftCell() == c || cps.getPlayerposition().getCurrentcell().getRightCell() == c) {
            cps.getPlayerposition().setCurrentcell(c);
            i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> {
                if (room.getCellsList().contains(c)) {
                    cps.getPlayerposition().setCurrentroom(room);
                }
            });
            return 0; //posizione di p aggiornata con successo
        }
        return -1; //cella non raggiungibile
    }

    private int pickUpAmmo(InitializeAllPlay i, Player p) {
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
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
            cps.getBoard().getPowerupList().add(i.getCurrentDeckState().getPowerupdeck().pop());
        cps.getPlayerposition().getCurrentcell().setAmmohere(null);
        return 0; //munizioni e potenziamento raccolti correttamente
    }

    //raccogliere l'arma e lasciarne un'altra in caso il player ne abbia più di 3
    private int pickUpWeapon(InitializeAllPlay i, Player p, Weapon w, ArrayList<PowerUp> u, Weapon replaceweapon) {
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
        HashMap<Munitions, Integer> cost = new HashMap<>();
        cost.put(Munitions.RED, 0);
        cost.put(Munitions.YELLOW, 0);
        cost.put(Munitions.BLUE, 0);
        if (w.getFirstPrice().containsKey(Munitions.RED))
            cost.put(Munitions.RED, w.getFirstPrice().get(Munitions.RED) - w.munitionsChecker(Munitions.RED));
        if (w.getFirstPrice().containsKey(Munitions.YELLOW))
            cost.put(Munitions.YELLOW, w.getFirstPrice().get(Munitions.YELLOW) - w.munitionsChecker(Munitions.YELLOW));
        if (w.getFirstPrice().containsKey(Munitions.BLUE))
            cost.put(Munitions.BLUE, w.getFirstPrice().get(Munitions.BLUE) - w.munitionsChecker(Munitions.BLUE));
        u.forEach(powerUp -> {
            cost.put(Munitions.RED, cost.get(Munitions.RED) - powerUp.munitionsChecker(Munitions.RED));
            cost.put(Munitions.YELLOW, cost.get(Munitions.YELLOW) - powerUp.munitionsChecker(Munitions.YELLOW));
            cost.put(Munitions.BLUE, cost.get(Munitions.BLUE) - powerUp.munitionsChecker(Munitions.BLUE));
        });
        cost.forEach((m, n) -> {
            if (n < 0) n = 0;
        });
        if (cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED) - cost.get(Munitions.RED) > -1 && cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW) - cost.get(Munitions.YELLOW) > -1 && cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE) - cost.get(Munitions.BLUE) > -1) {
            cps.getBoard().getWeaponsList().add(w);
            cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.RED, cost.get(Munitions.RED));
            cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.YELLOW, cost.get(Munitions.YELLOW));
            cps.getBoard().getMunitionsBox().decreaseMyMunitionsBox(Munitions.BLUE, cost.get(Munitions.BLUE));
            for (int j = 0; j < 3; j++) {
                if (cps.getPlayerposition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()[j] == w) {
                    cps.getPlayerposition().getCurrentcell().getSpawnpointzone().getSpawnWeaponsList()[j] = replaceweapon;
                    break;
                }
            }
            return 0;
        } else
            return -1;
    }
}
