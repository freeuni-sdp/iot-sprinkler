package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.HumidityService;

/**
 * Created by misho on 6/22/16.
 */
public class FakeHumidityService implements HumidityService {
    @Override
    public boolean isSoilMoist(String houseId) {
        return false;
    }
}
