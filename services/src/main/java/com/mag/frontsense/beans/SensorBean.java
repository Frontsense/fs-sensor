package com.mag.frontsense.beans;

import com.mag.frontsense.models.MongoSensor;
import com.mag.frontsense.models.Sensor;
import org.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.List;

@RequestScoped
public class SensorBean {

    private Client httpClient;

    @Inject
    private SensorBean sensorBean;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public List<Sensor> listReadings() {
        MongoSensor ms = new MongoSensor();

        return ms.testReadings();
    }

    public void insertReadings(JSONObject sensorReadings) {
        MongoSensor ms = new MongoSensor();

        ms.insertReadings(sensorReadings);
    }
}
