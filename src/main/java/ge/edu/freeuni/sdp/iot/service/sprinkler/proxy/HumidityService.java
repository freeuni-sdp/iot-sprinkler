package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

/**
 * Created by misho on 6/22/16.
 */
public interface HumidityService {

    /*
        returns true if soil is already moist enough
            and doesn't require watering.
        returns false if soil is dry.
     */
    boolean isSoilMoist(String houseId);

}
