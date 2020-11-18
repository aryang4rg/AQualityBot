package com.AQuality.api.AirVisualAPI.beans.airquality;

import java.io.Serializable;

public class CurrWeatherPollution implements Serializable {

    private Weather weather;
    private Pollution pollution;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

}