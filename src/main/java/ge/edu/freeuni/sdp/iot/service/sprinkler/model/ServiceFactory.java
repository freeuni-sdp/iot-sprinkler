package ge.edu.freeuni.sdp.iot.service.sprinkler.model;

import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.*;

/**
 * Created by misho on 6/22/16.
 */
public class ServiceFactory {

    public static ServiceFactory createServiceFactory() {
        return new ServiceFactory();
    }

    public CameraRecognizer getCameraRecognizer() {
        return new CameraRecognizerIot();
    }

    public HumidityService getHumidityService() {
        return new HumidityServiceIot();
    }

    public WeatherService getWeatherService() {
        return new WeatherServiceIot();
    }
}
