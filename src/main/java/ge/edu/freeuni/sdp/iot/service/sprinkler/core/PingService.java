package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by misho on 6/9/16.
 */
@Path("ping")
public class PingService {

    @GET
    public Response get() {
        return Response.ok().build();
    }
}
