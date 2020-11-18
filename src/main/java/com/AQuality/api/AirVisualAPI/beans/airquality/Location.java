package com.AQuality.api.AirVisualAPI.beans.airquality;

import java.io.Serializable;
import java.util.List;

public class Location implements Serializable {

    private String type;
    //(index) long = 0 lat = 1
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

    public void setLatitude(Double lat)
    {
        coordinates.set(1, lat);
    }

    public void setLongitude(Double longitude)
    {
        coordinates.set(0, longitude);
    }


    public String getRoundedLatitude()
    {
        String str = getLatitude().toString();
        if (str.length() < 10)
        {
            return str;
        }
        else
        {
            return str.substring(0,10);
        }
    }

    public String getRoundedLongitude()
    {
        String str = getLongitude().toString();
        if (str.length() < 10)
        {
            return str;
        }
        else
        {
            return str.substring(0,10);
        }
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

    @Override
    public String toString() {
        return "Location{" +
                "type='" + type + '\'' +
                ", lat=" + getLatitude() +
                ", long=" + getLongitude() +
                '}';
    }
}