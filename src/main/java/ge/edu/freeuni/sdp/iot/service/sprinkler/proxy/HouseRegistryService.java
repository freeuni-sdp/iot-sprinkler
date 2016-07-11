package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

/**
 * Created by misho on 7/11/16.
 */
public interface HouseRegistryService {
    boolean houseExists(String houseId);

    String getSprinklerIp(String houseId);
}
