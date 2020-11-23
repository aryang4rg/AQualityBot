package com.AQuality.api.AirVisualAPI.beans.countries;

import com.AQuality.api.AirVisualAPI.APICaller;

import java.net.URL;

/**
 * Calls the Airvisual API to get a list of the supported countries, dumps it into the Countries bean
 */
public class CountriesCaller extends APICaller<Countries>
{
    public CountriesCaller() throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/countries?key=" + AIRVISUALAPIKEY), Countries.class);
    }
}
