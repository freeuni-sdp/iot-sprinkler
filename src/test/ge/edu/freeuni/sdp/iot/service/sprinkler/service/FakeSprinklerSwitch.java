package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.SprinklerSwitch;
import org.json.JSONObject;

/**
 * Created by Koba on 23/06/2016.
 */
public class FakeSprinklerSwitch implements SprinklerSwitch {

    @Override
    public JSONObject getSprinklerStatus(String houseId) {
        return null;
    }

    @Override
    public boolean setSprinklerStatus(String houseId, boolean newStatus, int duration) {
        return false;
    }
}
