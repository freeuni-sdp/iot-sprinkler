package ge.edu.freeuni.sdp.iot.sprinkler.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by misho on 6/9/16.
 */
@Path("sprinkler")
public class SprinklerApi {

    @Path("{id}")
    public DataTest Test(@PathParam("id") String id) {
        DataTest test = new DataTest();
        test.setId(id);
        test.setText("agic : " + id);
        return test;
    }
}
