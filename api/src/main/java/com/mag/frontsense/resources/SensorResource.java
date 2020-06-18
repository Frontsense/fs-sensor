package com.mag.frontsense.resources;

import com.mag.frontsense.beans.SensorBean;
import com.mag.frontsense.models.Sensor;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/sensor")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SensorResource {

    @Inject
    private SensorBean sensorBean;

    @GET
    @Path("/test")
    public Response testRest() {
        return Response.ok("Sensor api is up and running")
                .build();
    }

    @GET
    @Path("/all")
    public Response getReadings() {
        List<Sensor> readings = sensorBean.listReadings();

        return Response.ok(readings)
                .build();
    }

    @POST
    @Path("/insert")
    public Response insertReadings(String readings) {
        sensorBean.insertReadings(new JSONObject(readings));

        return Response.ok().build();
    }
}
