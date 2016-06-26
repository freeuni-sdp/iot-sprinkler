package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.WeatherService;

/**
 * Created by misho on 6/22/16.
 */
public class FakeWeatherService implements WeatherService {
    @Override
    public boolean isRainLikely(String houseId) {
        return false;
    }
}
