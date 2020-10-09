package com.AQuality.AirVisualAPI;

import com.AQuality.AirVisualAPI.beans.Countries;
import com.AQuality.core.Util;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class CountryList extends APICaller<Countries>
{
    public CountryList() throws Exception
    {
        super(new URL("https://api.airvisual.com/v2/countries?key=" + Util.AIRVISUALAPIKEY));
    }
}
