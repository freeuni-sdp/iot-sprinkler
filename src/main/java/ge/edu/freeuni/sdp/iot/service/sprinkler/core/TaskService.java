package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.service.CameraRecognizer;
import ge.edu.freeuni.sdp.iot.service.sprinkler.service.HumidityService;
import ge.edu.freeuni.sdp.iot.service.sprinkler.service.WeatherService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by misho on 6/22/16.
 */
@Path("houses")
public class TaskService {

    public CameraRecognizer getCameraRecognizer() {
        return ServiceFactory.getCameraRecognizer();
    }

    public HumidityService getHumidityService() {
        return ServiceFactory.getHumidityService();
    }

    public WeatherService getWeatherService() {
        return ServiceFactory.getWeatherService();
    }

    @PUT
    @Path("{house_id}/task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse newTask(@PathParam("house_id") String houseId, RequestBody req){
        System.out.println(req.duration);
        System.out.println(req.houseId);
        System.out.println(req.status);
        return new TaskResponse("off", null);
    }
}
