package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

/**
 * Created by Koba on 23/06/2016.
 */
public interface SprinklerSwitch {

    /*
        returns true if sprinkler is on.
        returns false is sprinkler is off.
     */
    boolean isSprinklerOn(String houseId);

    /*
        tries to change sprinkler status to the given one.
        returns true if operation is successful, false if not.
        houseId - id of the house where the sprinkler is located
        newStatus - new status of the sprinkler (on/off)
        duration - number of seconds for which sprinkler should be on (not used if the new status is "off")
     */
    boolean setSprinklerStatus(String houseId, String newStatus, int duration);

}
