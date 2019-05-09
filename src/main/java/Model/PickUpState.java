package Model;

import Model.WeaponsCard.Weapon;

public class PickUpState implements State {

    @Override
    public Message doAction(DataPacket dataPacket, InitializeAllPlay allPlay) {
        //TODO
        return new Message("ok");
    }


    //metodo che permette al player di spostarsi nella cella vicina
    public  void moveOne(InitializeAllPlay i, Player p, Cell c){
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
            if (cps.getActiveplayer()==p && cps.isActiveturn()){
                if(cps.getPlayerposition().getCurrentcell().getUpCell()==c || cps.getPlayerposition().getCurrentcell().getDownCell()==c || cps.getPlayerposition().getCurrentcell().getLeftCell()==c || cps.getPlayerposition().getCurrentcell().getRightCell()==c){
                    cps.getPlayerposition().setCurrentcell(c);
                    i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> { if (room.getCellsList().contains(c)){cps.getPlayerposition().setCurrentroom(room);}});
                    //return 0 posizione di p aggiornata con successo
                }
                //return -1 cella non raggiungibile
            }
        //return -2 non è il turno del player
    }

    public void pickUpAmmo(InitializeAllPlay i, Player p){
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
            if(cps.isActiveturn() && cps.getActiveplayer()==p && cps.getPlayerposition().getCurrentcell().getSpawnpointzone()==null){
                Ammo a = cps.getPlayerposition().getCurrentcell().getAmmohere();
                if (a.getAmmoList()[0]+cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.RED)<4)
                    cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.RED,a.getAmmoList()[0]);
                else
                    cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.RED,3);
                if(a.getAmmoList()[1]+cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.YELLOW)<4)
                    cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.YELLOW,a.getAmmoList()[1]);
                else
                    cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.YELLOW,3);
                if(a.getAmmoList()[2]+cps.getBoard().getMunitionsBox().getMyMunitionsMap().get(Munitions.BLUE)<4)
                    cps.getBoard().getMunitionsBox().increaseMyMunitionsBox(Munitions.BLUE,a.getAmmoList()[2]);
                else
                    cps.getBoard().getMunitionsBox().getMyMunitionsMap().put(Munitions.BLUE,3);
                if(a.getPossiblePowerUp() && cps.getBoard().getPowerupList().size()<3)
                    cps.getBoard().getPowerupList().add(i.getCurrentDeckState().getPowerupdeck().pop());
                cps.getPlayerposition().getCurrentcell().setAmmohere(null);
                //return 0 munizioni e potenziamento raccolti correttamente
            }
        //return -1 non è il tuo turno o in spawnpoint
    }

    //raccoglie l'arma
    public void pickUpWeapon(InitializeAllPlay i, Player p, Weapon w){
        CurrentPlayerState cps = i.getCurrentPlayerState().get(p);
        if (cps.getActiveplayer()==p && cps.isActiveturn()){
            if(cps.getPlayerposition().getCurrentcell().getSpawnpointzone()!=null)
        }

    }
}
