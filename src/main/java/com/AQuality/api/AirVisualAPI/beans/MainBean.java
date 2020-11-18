package com.AQuality.api.AirVisualAPI.beans;


import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class MainBean<T>
{
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

    public abstract String getStatus();
    public abstract T getData();
}
