package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

/**
 * Created by misho on 6/22/16.
 */
public interface WeatherService {

    /*
        returns true if rain is forecast, false if not.
     */
    boolean isRainLikely(String houseId);

}
