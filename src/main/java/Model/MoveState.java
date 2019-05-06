//Author: Alex Saletti
package Model;

public class MoveState implements State {

    @Override
    public void doAction(InitializeAllPlay i, Cell c, Player p) {
        i.getCurrentPlayerState().forEach(ps -> {
            if(ps.isActiveturn() && ps.getActiveplayer()==p){
                if (ps.getPlayerposition().getCurrentcell().getReachableCells().contains(c)){
                    ps.getPlayerposition().setCurrentcell(c);
                    i.getStateSelectedMap().getSelectedmap().getRoomList().forEach(room -> { if (room.getCellsList().contains(c)){ps.getPlayerposition().setCurrentroom(room);}});
                }
                else {
                    //TODO System.out.printf("Cella troppo distante, impossibile raggiungerla!");
                }
            if(!ps.isActiveturn() && ps.getActiveplayer()==p){

            }
                //TODO System.out.printf("Non è il tuo turno!");

            }
        });

    }



    }

