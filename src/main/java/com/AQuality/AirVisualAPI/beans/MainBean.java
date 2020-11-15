package com.AQuality.AirVisualAPI.beans;


public abstract class MainBean<T>
{
    public abstract String getStatus();
    public abstract T getData();
}
