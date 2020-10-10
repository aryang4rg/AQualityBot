package com.AQuality.AirVisualAPI;

import com.AQuality.AirVisualAPI.beans.airquality.AirQuality;

import java.net.URL;

public class AirQualityCaller extends APICaller<AirQuality>
{
    public AirQualityCaller() throws Exception {
        super(new URL("http://api.airvisual.com/v2/nearest_city?lat=-37.3&lon=122.03&key=365a3411-985d-4d30-ba92-6a7ac80a669e"), AirQuality.class);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(new AirQualityCaller());
    }
}
