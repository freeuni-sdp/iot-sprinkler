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
        SprinklerSwitch sw = new SprinklerSwitchIot();
        JSONObject current = sw.getSprinklerStatus(houseId);
        if (req.status.compareTo("off") == 0) {
            req.duration = 0;
            if(sw.setSprinklerStatus(houseId, false, req.duration)) {
                return new TaskResponse("off", null);
            }
            else {
                return new TaskResponse(current.getString("status"), current.getInt("seconds_left"));
            }
        }
        if (req.duration > 60) {
            req.duration = 60;
        }
        if(getWeatherService().isRainLikely() || getCameraRecognizer().isUnknownObjectPresent(houseId)
                || getHumidityService().isSoilMoist(houseId)) {
            return new TaskResponse(current.getString("status"), current.getInt("seconds_left"));
        }
        if(sw.setSprinklerStatus(houseId, true, req.duration)) {
            return new TaskResponse("on", req.duration);
        }
        return new TaskResponse(current.getString("status"), current.getInt("seconds_left"));
    }
}
