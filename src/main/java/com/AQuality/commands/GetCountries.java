package com.AQuality.commands;

import com.AQuality.AirVisualAPI.beans.countries.Country;
import com.AQuality.AirVisualAPI.CountryListCaller;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.List;
import java.util.function.Consumer;

public class GetCountries implements Consumer<MessageCreateEvent> {
    @Override
    public void accept(MessageCreateEvent messageCreateEvent) {
        MessageChannel channel = messageCreateEvent.getMessage().getChannel().block();
        try
        {
            CountryListCaller list = new CountryListCaller();

            channel.createEmbed(
                    (spec) ->
                    {
                        spec.setColor(Color.RED);
                        String desc = "";
                        List<Country> countryList = list.getObject().getData();
                        for (int i = 0; i < countryList.size()-1; i++)
                        {
                            desc += countryList.get(i).getCountry() + ",\n";
                        }
                        desc += countryList.get(countryList.size()-1).getCountry();
                        spec.setDescription(desc);
                    }
            ).block();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
