package com.AQuality.api;

/**
 * API exception thrown when rather there is found an error when calling
 * google's geocode api or air visuals air quality api
 */
public class APIException extends Exception {
    public APIException()
    {
        super();
    }
    public APIException(String message)
    {
        super(message);
    }


}
