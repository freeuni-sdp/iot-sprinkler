package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.CameraRecognizer;
import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.HumidityService;
import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.SprinklerSwitch;
import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.WeatherService;

/**
 * Created by misho on 6/22/16.
 */
public class FakeTaskService extends TaskService {

    @Override
    public CameraRecognizer getCameraRecognizer() {
        return new FakeCameraRecognizer();
    }

    @Override
    public HumidityService getHumidityService() {
        return new FakeHumidityService();
    }

    @Override
    public WeatherService getWeatherService() {
        return new FakeWeatherService();
    }

    @Override
    public SprinklerSwitch getSprinklerSwitch() {
        return new FakeSprinklerSwitch();
    }
}
