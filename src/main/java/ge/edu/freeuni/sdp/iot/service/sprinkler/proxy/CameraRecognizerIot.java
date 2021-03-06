package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

/**
 * Created by misho on 6/23/16.
 */
public class CameraRecognizerIot implements CameraRecognizer {

    private static final String URI = "https://iot-camera-object-recognizer.herokuapp.com/webapi/houses/{house_id}/check";

    @Override
    public boolean isUnknownObjectPresent(String houseId) {
        try {
            HttpResponse<JsonNode> postResponse = Unirest.get(URI)
                    .header("accept", "application/json")
                    .routeParam("house_id", houseId)
                    .asJson();
            JSONObject o = postResponse.getBody().getObject();
            if (o.has("result")) {
                return !o.getBoolean("result");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return false;
    }
}
