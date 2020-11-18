package com.AQuality.api.AirVisualAPI.beans.countries;

import com.AQuality.api.AirVisualAPI.APICaller;

import java.net.URL;

public class CountriesCaller extends APICaller<Countries>
{
    public CountriesCaller() throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/countries?key=" + AIRVISUALAPIKEY), Countries.class);
    }
}
