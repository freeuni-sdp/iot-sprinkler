package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by misho on 6/23/16.
 */
public class CameraRecognizerIot implements CameraRecognizer {
    @Override
    public boolean isUnknownObjectPresent(String houseId) {
        try {
            String url = "http://iot-camera-object-recognizer.herokuapp.com/webapi/houses/" + houseId + "/check";
            HttpResponse<JsonNode> postResponse = Unirest.get(url)
                    .header("accept", "application/json")
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
