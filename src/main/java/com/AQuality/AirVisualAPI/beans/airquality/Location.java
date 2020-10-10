package com.AQuality.AirVisualAPI.beans.airquality;

import com.AQuality.AirVisualAPI.beans.Bean;

import java.io.Serializable;
import java.util.List;

public class Location implements Bean, Serializable {

    private String type;
    private List<Double> coordinates = null;

    public String getType() {
        return type;
    }

    public Double getLatitude()
    {
        return coordinates.get(1);
    }

    public Double getLongitude()
    {
        return coordinates.get(0);
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

}