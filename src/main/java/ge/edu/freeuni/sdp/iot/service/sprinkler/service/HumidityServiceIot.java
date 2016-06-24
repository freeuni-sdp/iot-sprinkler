package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by misho on 6/23/16.
 */
public class HumidityServiceIot implements HumidityService {
    private static final String URI = "https://private-17a5da-sdp2.apiary-mock.com/house/{house_id}";
    private static final String LIST_KEY = "values";
    private static final String AVAILABLE = "available";
    private static final String ID = "sensorId";
    private static final String VALUE = "sensorValue";

    private static final double MINIMUM_MOIST = 40.0;


    @Override
    public boolean isSoilMoist(String houseId) {
        try {
            HttpResponse<JsonNode> resp = Unirest.get(URI)
                    .routeParam("house_id", houseId)
                    .asJson();
            JSONArray node = resp.getBody()
                    .getObject()
                    .getJSONArray(LIST_KEY);
            List<HumidityServiceObj> ls = new ArrayList<>();
            for (int i=0; i < node.length(); i++) {
                JSONObject curr = node.getJSONObject(i);
                HumidityServiceObj newOne = new HumidityServiceObj();
                newOne.available = curr.getBoolean(AVAILABLE);
                newOne.id = curr.getInt(ID);
                newOne.value = curr.getDouble(VALUE);
                ls.add(newOne);
            }

            // only needed information is value.. for now
            // other values and list is for future use

            double avg = 0;
            for (HumidityServiceObj curr : ls)
                avg += curr.value;
            avg = avg / ls.size();
            return avg < MINIMUM_MOIST;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private class HumidityServiceObj {
        public double value;

        public int id;

        public boolean available;
    }
}
