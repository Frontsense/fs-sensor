package com.mag.frontsense.models;

public class Sensor {
    private String sensorName;
    private String readings;

    Sensor(String sensorName, String readings) {
        this.sensorName = sensorName;
        this.readings = readings;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getReadings() {
        return readings;
    }

    public void setReadings(String readings) {
        this.readings = readings;
    }
}
