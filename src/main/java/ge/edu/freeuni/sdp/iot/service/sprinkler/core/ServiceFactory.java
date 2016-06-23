package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.service.*;

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
