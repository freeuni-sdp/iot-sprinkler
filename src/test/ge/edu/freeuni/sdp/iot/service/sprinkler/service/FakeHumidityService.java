package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.HumidityService;

/**
 * Created by misho on 6/22/16.
 */
public class FakeHumidityService implements HumidityService {
    @Override
    public boolean isSoilMoist(String houseId) {
        if(houseId.compareTo("2") == 0) {
            return true;
        }
        return false;
    }
}
