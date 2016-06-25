package ge.edu.freeuni.sdp.iot.service.sprinkler.model;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.*;

/**
 * Created by misho on 6/22/16.
 */
public class ServiceFactory {

    public static CameraRecognizer getCameraRecognizer() {
        return new CameraRecognizerIot();
    }

    public static HumidityService getHumidityService() {
        return new HumidityServiceIot();
    }

    public static WeatherService getWeatherService() {
        return new WeatherServiceIot();
    }
}
