package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by misho on 6/23/16.
 */
public class WeatherServiceIot implements WeatherService {

    private static final String URI = "http://private-6e8eb-iotweather.apiary-mock.com/webapi/rain";
    private static final String KEY = "rain";
    private static final String POSITIVE_VAL = "yes";

    @Override
    public boolean isRainLikely() {
        try {
            HttpResponse<JsonNode> resp = Unirest.get(URI)
                    .asJson();
            JSONArray node = resp.getBody()
                    .getArray();
            return node.length() != 0 && node.getJSONObject(0).getString(KEY).equals(POSITIVE_VAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
