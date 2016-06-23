package ge.edu.freeuni.sdp.iot.service.sprinkler.core;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

/**
 * Created by misho on 6/22/16.
 */
public class TestTaskService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeTaskService.class);
    }
}
