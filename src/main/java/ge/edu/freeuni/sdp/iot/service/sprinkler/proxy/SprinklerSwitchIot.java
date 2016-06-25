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
    public JSONObject getSprinklerStatus(String houseId) {
        try {
            String url = "http://iot-sprinkler-switch.herokuapp.com/webapi/houses/" + houseId;
            HttpResponse<String> postResponse = Unirest.get(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asString();
            String str = postResponse.getBody();
            JSONObject res = new JSONObject(str);
            return res;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean setSprinklerStatus(String houseId, boolean newStatus, int duration) {
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
        }
        try {
            String url = "http://iot-sprinkler-switch.herokuapp.com/webapi/houses/" + houseId;
            HttpResponse<String> postResponse = Unirest.put(url)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(body)
                    .asString();
            String str = postResponse.getBody();
            JSONObject res = new JSONObject(postResponse.getBody());
            if (res.has("status")) {
                return res.getString("status").equals("on");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
