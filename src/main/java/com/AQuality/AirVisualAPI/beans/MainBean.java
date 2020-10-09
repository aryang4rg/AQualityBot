package com.AQuality.AirVisualAPI.beans;

import java.util.List;

public abstract class MainBean implements Bean
{
    public abstract String getStatus();
    public abstract List<Country> getData();

}
