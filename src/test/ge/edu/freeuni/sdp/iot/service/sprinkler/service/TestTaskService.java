package ge.edu.freeuni.sdp.iot.service.sprinkler.service;

import org.easymock.EasyMock;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by misho on 6/22/16.
 */
public class TestTaskService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(FakeTaskService.class);
    }

    /*
        tests "on" task when duration is not provided
     */
    @Test
    public void testOnDefaultDuration() {
        Entity e = Entity.json("{ \"house_id\": \"1\", \"set_status\": \"on\"}");
        Response r = target("houses/1/task")
                            .request(MediaType.APPLICATION_JSON_TYPE)
                            .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(60, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }


    /*
        tests "on" task when soil is humid
     */
    @Test
    public void testOnHumidity() {
        Entity e = Entity.json("{ \"house_id\": \"2\", \"set_status\": \"on\"}");
        Response r = target("houses/2/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(50, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }


    /*
        tests "on" task when switch can't change sprinkler status
     */
    @Test
    public void testOnSwitch() {
        Entity e = Entity.json("{ \"house_id\": \"3\", \"set_status\": \"on\"}");
        Response r = target("houses/3/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("off", json.getString("status"));
        assertEquals(0, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests "off" task when switch can't change sprinkler status
     */
    @Test
    public void testOffSwitch() {
        Entity e = Entity.json("{ \"house_id\": \"3\", \"set_status\": \"off\"}");
        Response r = target("houses/3/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("off", json.getString("status"));
        assertFalse(json.has("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests "on" task when there are unknown objects on the scene
     */
    @Test
    public void testOnCamera() {
        Entity e = Entity.json("{ \"house_id\": \"4\", \"set_status\": \"on\"}");
        Response r = target("houses/4/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("off", json.getString("status"));
        assertFalse(json.has("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests "on" task when weather is likely
     */
    @Test
    public void testOnWeather() {
        Entity e = Entity.json("{ \"house_id\": \"5\", \"set_status\": \"on\"}");
        Response r = target("houses/5/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(50, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests normal "on" task case with duration in (0, 60]
     */
    @Test
    public void testOnNormal() {
        Entity e = Entity.json("{ \"house_id\": \"1\", \"set_status\": \"on\", \"duration\": 5}");
        Response r = target("houses/1/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(5, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        test normal "off" task case
     */
    @Test
    public void testOffNormal() {
        Entity e = Entity.json("{ \"house_id\": \"1\", \"set_status\": \"off\" }");
        Response r = target("houses/1/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("off", json.getString("status"));
        assertFalse(json.has("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests "on" task case when given duration is larger than 60
     */
    @Test
    public void testOnLongDuration() {
        Entity e = Entity.json("{ \"house_id\": \"1\", \"set_status\": \"on\", \"duration\": 65}");
        Response r = target("houses/1/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(60, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        tests "on" task case when given duration is negative
     */
    @Test
    public void testOnNegativeDuration() {
        Entity e = Entity.json("{ \"house_id\": \"1\", \"set_status\": \"on\", \"duration\": -1}");
        Response r = target("houses/1/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        JSONObject json = new JSONObject(r.readEntity(String.class));
        assertEquals("on", json.getString("status"));
        assertEquals(60, json.getInt("duration"));
        assertEquals(200, r.getStatus());
    }

    /*
        Tests house not exists response
     */
    @Test
    public void houseNotExists(){
        Entity e = Entity.json("{ \"house_id\": \"12\", \"set_status\": \"on\"}");
        Response r = target("houses/12/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), r.getStatus());
    }

    /*
        Tests bad request body response
     */
    @Test
    public void badRequest(){
        Entity e = Entity.json("{ \"house_id\": \"12\"}");
        Response r = target("houses/12/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(e);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), r.getStatus());
    }
}
