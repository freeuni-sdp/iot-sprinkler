package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import org.json.JSONObject;

/**
 * Created by Koba on 23/06/2016.
 */
public interface SprinklerSwitch {

    JSONObject getSprinklerStatus(String houseId);

    /*
        tries to change sprinkler status to the given one.
        returns true if operation is successful, false if not.
        houseId - id of the house where the sprinkler is located
        newStatus - new status of the sprinkler (true/false = on/off)
        duration - number of seconds for which sprinkler should be on (not used if the new status is "off")
     */
    boolean setSprinklerStatus(String houseId, boolean newStatus, int duration);

}
