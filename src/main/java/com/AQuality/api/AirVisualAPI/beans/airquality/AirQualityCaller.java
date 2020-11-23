package com.AQuality.api.AirVisualAPI.beans.airquality;

import com.AQuality.api.AirVisualAPI.APICaller;

import java.net.URL;

/**
 * given a location it will call the air visual api and get its content and dump it into the AirQuality bean
 */
public class AirQualityCaller extends APICaller<AirQuality>
{
    public AirQualityCaller(double latitude, double longitude) throws Exception {
        super(new URL("https://api.airvisual.com/v2/nearest_city?lat=" + latitude + "&lon=" + longitude + "&key=" + AIRVISUALAPIKEY), AirQuality.class);
    }

    public AirQualityCaller(String country, String state, String city) throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/city?city=" + city + "&state=" + state + "&country=" + country +"&key=" + AIRVISUALAPIKEY), AirQuality.class);
    }

    public static void main(String[] args) throws Exception {
        //System.out.println(new AirQualityCaller(20,20));
        System.out.println(new AirQualityCaller("USA","California", "Cupertino"));
    }
}
