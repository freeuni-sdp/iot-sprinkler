package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;

/**
 * Created by misho on 6/23/16.
 */
public class WeatherServiceIot implements WeatherService {

    private static final String URI = "https://iot-weather.herokuapp.com/webapi/houses/{house_id}/rain";
    private static final String KEY = "rain";
    private static final String POSITIVE_VAL = "yes";

    @Override
    public boolean isRainLikely(String houseId) {
        try {
            HttpResponse<JsonNode> resp = Unirest.get(URI)
                    .routeParam("house_id", houseId)
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
