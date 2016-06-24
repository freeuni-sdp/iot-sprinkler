package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.service.SprinklerSwitch;

/**
 * Created by Koba on 23/06/2016.
 */
public class FakeSprinklerSwitch implements SprinklerSwitch {
    @Override
    public boolean isSprinklerOn(String houseId) {
        return false;
    }

    @Override
    public boolean setSprinklerStatus(String houseId, String newStatus, int duration) {
        return false;
    }
}
