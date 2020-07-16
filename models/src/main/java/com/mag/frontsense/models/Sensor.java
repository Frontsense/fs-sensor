package com.mag.frontsense.models;


import org.bson.BsonTimestamp;

import java.util.Date;

public class Sensor {
    private String sensorType;
    private Integer userId;
    private String readings;
    private double lng;
    private double lat;
    private Date timestamp;
    private Integer taskId;

    Sensor(String sensorType, Integer userId, String readings, double lng, double lat, Date timestamp, Integer taskId) {
        this.sensorType = sensorType;
        this.userId = userId;
        this.readings = readings;
        this.lng = lng;
        this.lat = lat;
        this.timestamp = timestamp;
        this.taskId = taskId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReadings() {
        return readings;
    }

    public void setReadings(String readings) {
        this.readings = readings;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
