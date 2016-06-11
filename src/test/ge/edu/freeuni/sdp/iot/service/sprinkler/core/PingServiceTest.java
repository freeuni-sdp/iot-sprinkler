package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import ge.edu.freeuni.sdp.iot.service.sprinkler.core.PingService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by misho on 6/9/16.
 */
public class PingServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(PingService.class);
    }

    @Test
    public void testPing() {
        Response actual = target("ping").request().get();
        assertThat(actual.getStatus(), is(equalTo(Response.Status.OK.getStatusCode())));
    }
}
