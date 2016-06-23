package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.service.CameraRecognizer;
import ge.edu.freeuni.sdp.iot.service.sprinkler.service.HumidityService;
import ge.edu.freeuni.sdp.iot.service.sprinkler.service.WeatherService;

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
}
