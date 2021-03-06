package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import sun.security.x509.AVA;

public class HumidityServiceIot implements HumidityService {
    private static final String URI = "https://iot-soil-moisture-sensor.herokuapp.com/house/{houseid}";
    private static final String AVAILABLE = "available";
    private static final String VALUE = "sensorValue";

    private static final double MINIMUM_MOIST = 40.0;


    @Override
    public boolean isSoilMoist(String houseId) {
        try {

            JSONObject obj = Unirest.get(URI)
                    .routeParam("houseid", houseId)
                    .asJson()
                    .getBody()
                    .getObject();
            boolean available = obj.getBoolean(AVAILABLE);
            double sensorValue = obj.getDouble(VALUE);
            return available && sensorValue >= MINIMUM_MOIST;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
