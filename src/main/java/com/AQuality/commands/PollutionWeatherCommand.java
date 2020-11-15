package com.AQuality.commands;

import com.AQuality.AirVisualAPI.beans.airquality.AirQualityCaller;
import com.AQuality.AirVisualAPI.beans.airquality.AirQualityData;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;

import java.util.List;

//2 input types, long / lat or city, state, country
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
        else
        {
            return;
        }

        channel.createEmbed(spec ->
        {
            AirQualityData data = caller.getObject().getData();
            spec.setTitle("Weather / Air Quality for: " + data.getCity() + ", " + data.getCountry());
            spec.addField("Location",
                    "Latitude: " + data.getLocation().getLatitude() + "\n" +
                            "Longitude: " + data.getLocation().getLongitude() + "\n" +
                            "Country: " + data.getCountry() + "\n" +
                            "State: " + data.getState() + "\n" +
                            "City: " + data.getCity() + "\n"
                    , false);
            spec.addField("Weather",
                    "Weather " + "" +
                            "Temperature: (C°) " + data.getWeather().getTemperatureC() + "\n" +
                            "Temperature (F°) " + data.getWeather().getTemperatureF() + "\n"
                    ,false);
        }
        );

    }

    @Override
    public Command createNew() {
        return new PollutionWeatherCommand();
    }
}
