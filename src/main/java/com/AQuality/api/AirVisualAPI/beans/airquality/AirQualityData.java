package com.AQuality.api.AirVisualAPI.beans.airquality;

import java.io.Serializable;
public class AirQualityData implements Serializable {

    private String city;
    private String state;
    private String country;
    private Location location;
    private CurrWeatherPollution current;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CurrWeatherPollution getCurrent() {
        return current;
    }

    public void setCurrent(CurrWeatherPollution current) {
        this.current = current;
    }

    public CurrWeatherPollution getCurrWeatherPollution() {
        return current;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Pollution getPollution() {
        return current.getPollution();
    }

    public Weather getWeather()
    {
        return current.getWeather();
    }



}