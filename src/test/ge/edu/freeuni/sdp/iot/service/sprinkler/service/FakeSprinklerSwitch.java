package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.SprinklerSwitch;
import org.json.JSONObject;

/**
 * Created by Koba on 23/06/2016.
 */
public class FakeSprinklerSwitch implements SprinklerSwitch {

    @Override
    public JSONObject getSprinklerStatus(String url, String houseId) {
        if(houseId.compareTo("3") == 0 || houseId.compareTo("4") == 0) {
            return new JSONObject("{ \"status\": \"off\", \"seconds_left\": 0 }");
        }
        return new JSONObject("{ \"status\": \"on\", \"seconds_left\": 50 }");
    }

    @Override
    public boolean setSprinklerStatus(String url, String houseId, boolean newStatus, int duration) {
        if(houseId.compareTo("3") == 0) {
            return false;
        }
        return true;
    }
}
