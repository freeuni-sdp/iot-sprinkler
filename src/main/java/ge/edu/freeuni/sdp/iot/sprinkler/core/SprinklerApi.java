package ge.edu.freeuni.sdp.iot.sprinkler.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by misho on 6/9/16.
 * TODO-REMOVE!
 */
@Path("sprinkler")
public class SprinklerApi {

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public DataTest Test(@PathParam("id") String id) {
        DataTest test = new DataTest();
        test.setId(id);
        test.setText("agic : " + id);
        return test;
    }
}
