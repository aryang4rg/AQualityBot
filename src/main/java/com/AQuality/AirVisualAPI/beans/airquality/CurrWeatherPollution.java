package com.AQuality.AirVisualAPI.beans.airquality;

import com.AQuality.AirVisualAPI.beans.Bean;

import java.io.Serializable;

public class CurrWeatherPollution implements Bean, Serializable {

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