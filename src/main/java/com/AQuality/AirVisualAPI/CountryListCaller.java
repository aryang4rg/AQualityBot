package com.AQuality.AirVisualAPI;

import com.AQuality.AirVisualAPI.beans.countries.Countries;
import com.AQuality.core.Util;

import java.net.URL;

public class CountryListCaller extends APICaller<Countries>
{
    public CountryListCaller() throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/countries?key=" + AIRVISUALAPIKEY), Countries.class);
    }
}
