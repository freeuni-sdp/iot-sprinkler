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
        return ServiceFactory.createServiceFactory().getCameraRecognizer();
    }

    public HumidityService getHumidityService() {
        return ServiceFactory.createServiceFactory().getHumidityService();
    }

    public WeatherService getWeatherService() {
        return ServiceFactory.createServiceFactory().getWeatherService();
    }

    public SprinklerSwitch getSprinklerSwitch() {
        return new SprinklerSwitchIot();
    }

    public HouseRegistryService getHouseRegistry() {
        return new HouseRegistryServiceIot();
    }

    @PUT
    @Path("{house_id}/task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TaskResponse newTask(@PathParam("house_id") String houseId, RequestBody req) {
        HouseRegistryService registry = getHouseRegistry();
        String url = registry.getSprinklerIp(houseId);
        if (req.houseId == null || req.status == null) {
            throw new BadRequestException();
        }
        SprinklerSwitch sw = getSprinklerSwitch(); //https://private-8320b-sprinklerswitch.apiary-mock.com
        JSONObject current = sw.getSprinklerStatus(url, houseId);
        if (current == null || !registry.houseExists(houseId) || url == null) {
            throw new NotFoundException();
        }
        if (req.status.compareTo("off") == 0) {
            req.duration = 0;
            if(sw.setSprinklerStatus(url, houseId, false, req.duration)) {
                return new TaskResponse("off", null);
            }
            else {
                return returnOffTaskResponse(current);
            }
        }
        if (req.duration > 60 || req.duration < 0) {
            req.duration = 60;
        }
        if(getWeatherService().isRainLikely(houseId) || getCameraRecognizer().isUnknownObjectPresent(houseId)
                || getHumidityService().isSoilMoist(houseId)) {
            return returnOffTaskResponse(current);
        }
        if(sw.setSprinklerStatus(url, houseId, true, req.duration)) {
            return new TaskResponse("on", req.duration);
        }
        return new TaskResponse(current.getString("status"), current.getInt("seconds_left"));
    }

    private TaskResponse returnOffTaskResponse(JSONObject current) {
        String status = current.getString("status");
        if (status.compareTo("off") == 0) {
            return new TaskResponse(status, null);
        }
        int left = 0;
        if (current.has("seconds_left"))
            left = current.getInt("seconds_left");
        return new TaskResponse(status, left);
    }
}
