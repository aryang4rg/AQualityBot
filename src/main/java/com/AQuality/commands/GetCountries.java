package com.AQuality.commands;

import com.AQuality.AirVisualAPI.beans.countries.Country;
import com.AQuality.AirVisualAPI.beans.countries.CountriesCaller;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.List;

public class GetCountries extends Command<MessageCreateEvent> {

    @Override
    public void acceptImpl(MessageCreateEvent messageCreateEvent, MessageChannel channel) throws Exception
    {
        CountriesCaller list = new CountriesCaller();

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
                }).block();
    }
}
