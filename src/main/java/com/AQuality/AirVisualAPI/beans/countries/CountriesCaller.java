package com.AQuality.AirVisualAPI.beans.countries;

import com.AQuality.AirVisualAPI.APICaller;
import com.AQuality.AirVisualAPI.beans.countries.Countries;
import com.AQuality.core.Util;

import java.net.URL;

public class CountriesCaller extends APICaller<Countries>
{
    public CountriesCaller() throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/countries?key=" + AIRVISUALAPIKEY), Countries.class);
    }
}
