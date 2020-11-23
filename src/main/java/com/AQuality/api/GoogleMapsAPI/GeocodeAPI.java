package com.AQuality.api.GoogleMapsAPI;

import com.AQuality.api.APIException;
import com.AQuality.api.AirVisualAPI.beans.airquality.Location;
import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.HashMap;

/**
 * Class for connecting with google's geocode api to get long / lat cords from a description
 */
public class GeocodeAPI
{
    private static final String geocodeURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     *
     * @param desc description given to the google geocode api
     * @return returns a pair of double double, first double is latitude second double is longitude
     * @throws Exception idfk java machine broke
     */
    public static Pair<Double, Double> getLocationFromDescription(String desc) throws Exception
    {
        String urlToCall = geocodeURL + desc + "&key=" + Util.GOOGLEMAPSAPIKEY;
        URL myUrl = new URL(urlToCall);
        String contentFromUrl = Util.urlReader(myUrl.openConnection());
        JsonNode node = objectMapper.readTree(contentFromUrl);

        if (!node.path("status").asText().equals("OK"))
        {
            throw new APIException(node.path("status").asText() + " " + node.path("error_message").asText());
        }

        Pair<Double, Double> myLocation = new Pair<>();
        myLocation.setVal1(Double.parseDouble(
                node.path("results").get(0).
                path("geometry").
                path("location").
                path("lat").asText()));
        myLocation.setVal2(Double.parseDouble(
                node.path("results").get(0).
                        path("geometry").
                        path("location").
                        path("lng").asText()));

        return myLocation;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getLocationFromDescription("cupertino"));
    }
}
