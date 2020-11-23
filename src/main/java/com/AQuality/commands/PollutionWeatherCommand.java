package com.AQuality.commands;

import com.AQuality.api.AirVisualAPI.beans.airquality.AirQualityCaller;
import com.AQuality.api.AirVisualAPI.beans.airquality.AirQualityData;
import com.AQuality.api.GoogleMapsAPI.GeocodeAPI;
import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;
import net.iakovlev.timeshape.TimeZoneEngine;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * Command for getting the pollution and weather information given a location
 */
public class PollutionWeatherCommand extends Command
{
    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception
    {
        List<String> inputs = Util.getInputParams(obj.getMessage().getContent());
        AirQualityCaller caller;
        if (Util.isInputType(inputs, Double.class, Double.class))
        {
            caller = new AirQualityCaller(Double.parseDouble(inputs.get(0)),Double.parseDouble(inputs.get(1)));
        }
        else if (Util.isInputType(inputs, String.class, String.class, String.class))
        {
            caller = new AirQualityCaller(inputs.get(0), inputs.get(1), inputs.get(2));
        }
        else if (Util.isInputType(inputs, String.class))
        {
            Pair<Double, Double> cords = GeocodeAPI.getLocationFromDescription(inputs.get(0));
            caller = new AirQualityCaller(cords.getVal1(), cords.getVal2());
        }
        else
        {
            return;
        }

        channel.createEmbed(spec ->
        {
            AirQualityData data = caller.getObject().getData();
            spec.setTitle("Weather / Air Quality for: " + data.getCity() + ", " + data.getCountry());
            spec.addField("Location",
                    "Latitude: " + data.getLocation().getRoundedLatitude() + "\n" +
                            "Longitude: " + data.getLocation().getRoundedLongitude() + "\n" +
                            "Country: " + data.getCountry() + "\n" +
                            "State: " + data.getState() + "\n" +
                            "City: " + data.getCity() + "\n"
                    , false);
            spec.addField("Weather",
                    "Weather: " + data.getWeather().getWeatherInDescriptionForm() + " " + data.getWeather().getWeatherInEmojiForm() + "\n" +
                            "Temperature: " + data.getWeather().getTemperatureC() + " C° / " +  data.getWeather().getTemperatureF() + "F°\n" +
                            "Atmospheric Pressure: " + data.getWeather().getAtmosphericPressure() + " hPa\n" +
                            "Humidity: " + data.getWeather().getHumidity() + " %\n" +
                            "Wind Speed: " + data.getWeather().getWindSpeed() + " m/s\n" +
                            "Wind Bearing: " + data.getWeather().getWindDirection() + "°\n"
                    ,false);

            spec.setThumbnail(data.getWeather().getWeatherInPhotoForm());

            spec.addField("Air Pollution",
                    "AQI Rating: " + data.getPollution().getAqi() + ", " + data.getPollution().getDescriptionOfAqi() + "\n" +
                            "Main Pollutant: " + data.getPollution().getFullNameOfPollutant()
                    ,false);

            spec.setColor( Color.of(data.getPollution().getColorOfAqi().getRed(), data.getPollution().getColorOfAqi().getGreen(),
                    data.getPollution().getColorOfAqi().getBlue())) ;

            //todo timezone broken
            ZoneOffset timeZone = TimeZoneEngine.initialize().query(data.getLocation().getLatitude(),
                    data.getLocation().getLongitude()).get().getRules().getOffset(LocalDateTime.now());
            spec.setTimestamp(
                    (data.getWeather().getTime().toInstant(timeZone).toEpochMilli() < data.getPollution().getTime().toInstant(timeZone).toEpochMilli()) ?
                            data.getWeather().getTime().toInstant(timeZone) : data.getPollution().getTime().toInstant(timeZone)
            );

            spec.setFooter("Last Updated", null);

        }
        ).block();

    }

    @Override
    public Command createNew() {
        return new PollutionWeatherCommand();
    }

    @Override
    public String getHelpDesc() {
        return "Gets Pollution and Weather information for a specified place";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters() {
        ArrayList<Pair<String, String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("(Double, Double) Latitude Longitude", "Information for specified longitude / latitude"));
        parameters.add(new Pair<>("(String, String, String) Country, State, City", "Information for specified Country, State, City"));
        parameters.add(new Pair<>("(String) Description for place", "Tries to get information from description"));
        return parameters;
    }
}
