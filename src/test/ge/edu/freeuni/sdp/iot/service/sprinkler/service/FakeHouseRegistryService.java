package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.HouseRegistryService;

/**
 * Created by misho on 7/11/16.
 */
public class FakeHouseRegistryService implements HouseRegistryService{
    @Override
    public boolean houseExists(String houseId) {
        return houseId.equals("1") ||
                houseId.equals("2") ||
                houseId.equals("3") ||
                houseId.equals("4") ||
                houseId.equals("5");
    }

    @Override
    public String getSprinklerIp(String houseId) {
        return "0.0.0.0";
    }
}
