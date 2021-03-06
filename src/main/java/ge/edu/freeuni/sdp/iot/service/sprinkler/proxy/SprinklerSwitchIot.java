package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Created by Koba on 23/06/2016.
 */
public class SprinklerSwitchIot implements SprinklerSwitch {

    @Override
    public JSONObject getSprinklerStatus(String url, String houseId) {
        try {
            url = url + "/webapi/houses/{house_id}";
            HttpResponse<String> postResponse = Unirest.get(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .routeParam("house_id", houseId)
                    .asString();
            String str = postResponse.getBody();
            JSONObject res = new JSONObject(str);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setSprinklerStatus(String url, String houseId, boolean newStatus, int duration) {
        JSONObject body = new JSONObject();
        String status = "on";
        if (!newStatus) {
            status = "off";
            body.put("set_status", status);
        }
        else {
            body.put("set_status", status);
            if (duration > 0) {
                if(duration > 60) {
                    duration = 60;
                }
                body.put("timeout", duration);
            }
            else {
                return false;
            }
        }
        try {
            url = url + "/webapi/houses/{house_id}";
            HttpResponse<String> postResponse = Unirest.put(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .routeParam("house_id", houseId)
                    .body(body)
                    .asString();
            JSONObject res = new JSONObject(postResponse.getBody());
            if (res.has("status")) {
                if (res.getString("status").compareTo(status) != 0) {
                    return false;
                }
                else if (res.has("seconds_left")) {
                    return res.getInt("seconds_left") == duration;
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
