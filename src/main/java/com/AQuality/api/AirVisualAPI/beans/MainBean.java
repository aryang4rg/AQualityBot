package com.AQuality.api.AirVisualAPI.beans;


import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * the first "main bean" that connects to the rest of the information has to extend this class
 * @param <T> the data type of the main bean
 */
public abstract class MainBean<T>
{
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

    public abstract String getStatus();
    public abstract T getData();
}
