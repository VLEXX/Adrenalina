package model.modelstates;

import model.datapacket.DataPacket;
import model.gamedata.InitializeAllPlay;
import model.datapacket.MessageEnum;
import model.map.*;
import model.munitions.Munitions;
import model.powerups.PowerUp;
import model.weaponscard.Weapon;

import java.util.HashMap;

public class EndTurnState implements State {

    @Override
    public MessageEnum doAction(DataPacket dataPacket, InitializeAllPlay i) {
        //TODO
        return MessageEnum.OK;
    }
}
