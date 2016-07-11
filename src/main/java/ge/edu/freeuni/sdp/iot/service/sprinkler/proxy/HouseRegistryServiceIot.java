package ge.edu.freeuni.sdp.iot.service.sprinkler.proxy;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.json.JSONObject;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by misho on 7/11/16.
 */
public class HouseRegistryServiceIot implements HouseRegistryService {
    private static final String API_URL =
            "http://private-3aa89-iothouseregistry.apiary-mock.com";

    private static final String KEY_SPRINKLER = "sprinkler_ip";
    private static final String KEY_SUB_IP = "_";

    private final Client client;

    public HouseRegistryServiceIot() {
        ClientConfig config = new ClientConfig().register(JacksonFeature.class);
        this.client = ClientBuilder.newClient(config);
    }


    @Override
    public boolean houseExists(String houseId) {
        String requestUrl = String.format("%s/houses/%s", API_URL, houseId);
        Response response = client
                .target(requestUrl)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        return response.getStatus() == Response.Status.OK.getStatusCode();
    }

    @Override
    public String getSprinklerIp(String houseId) {
        String requestUrl = String.format("%s/houses/%s", API_URL, houseId);
        Response response = client
                .target(requestUrl)
                .request(MediaType.APPLICATION_OCTET_STREAM)
                .get();
        try {
            JSONObject obj =  new JSONObject(response.readEntity(String.class));
            return obj.getJSONObject(KEY_SPRINKLER)
                    .getString(KEY_SUB_IP);
        } catch (Exception ignored){}
        return null;
    }
}
