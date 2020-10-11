package com.AQuality.AirVisualAPI.beans.airquality;


import com.AQuality.AirVisualAPI.beans.MainBean;

import java.io.Serializable;

public class AirQuality extends MainBean<AirQualityData> implements Serializable {

    private String status;
    private AirQualityData data;

    public String getStatus() {
        return status;
    }

    public Weather getWeather()
    {
        return data.getCurrWeatherPollution().getWeather();
    }

    public Pollution getPollution()
    {
        return data.getCurrWeatherPollution().getPollution();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AirQualityData getData() {
        return data;
    }

    public void setData(AirQualityData data) {
        this.data = data;
    }

}