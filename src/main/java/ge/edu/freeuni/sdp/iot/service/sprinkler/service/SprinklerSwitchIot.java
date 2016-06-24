package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

/**
 * Created by Koba on 23/06/2016.
 */
public class SprinklerSwitchIot implements SprinklerSwitch {
    @Override
    public boolean isSprinklerOn(String houseId) {
        return false;
    }

    @Override
    public boolean setSprinklerStatus(String houseId, String newStatus, int duration) {
        return false;
    }
}
