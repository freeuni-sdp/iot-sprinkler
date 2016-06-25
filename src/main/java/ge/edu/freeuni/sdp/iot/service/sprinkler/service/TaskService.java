package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import ge.edu.freeuni.sdp.iot.service.sprinkler.model.RequestBody;
import ge.edu.freeuni.sdp.iot.service.sprinkler.model.ServiceFactory;
import ge.edu.freeuni.sdp.iot.service.sprinkler.model.TaskResponse;
import ge.edu.freeuni.sdp.iot.service.sprinkler.proxy.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

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
        if (req.houseId == null || req.status == null)
            throw new BadRequestException();
        SprinklerSwitch sw = new SprinklerSwitchIot();
        JSONObject current = sw.getSprinklerStatus(houseId);
        if (current == null) {
            throw new NotFoundException();
        }
        if (req.status.compareTo("off") == 0) {
            req.duration = 0;
            if(sw.setSprinklerStatus(houseId, false, req.duration)) {
                return new TaskResponse("off", null);
            }
            else {
                String status = current.getString("status");
                int left = 0;
                if (current.has("seconds_left"))
                    left = current.getInt("seconds_left");
                return new TaskResponse(status, left);
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
