package com.AQuality.commands;

import com.AQuality.AirVisualAPI.beans.countries.Country;
import com.AQuality.AirVisualAPI.beans.countries.CountriesCaller;
import com.AQuality.core.Util;
import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.List;
import java.util.regex.Pattern;

public class GetCountries extends Command<MessageCreateEvent> {

    @Override
    public void acceptImpl(MessageCreateEvent messageCreateEvent, MessageChannel channel) throws Exception
    {
        CountriesCaller list = new CountriesCaller();
        List<String> tokensOfMessage = Util.getInputParams(messageCreateEvent.getMessage().getContent());

        if (tokensOfMessage.size() == 0) {
            channel.createEmbed(
                    (spec) ->
                    {
                        spec.setColor(Color.RED);
                        String desc = "";
                        List<Country> countryList = list.getObject().getData();
                        for (int i = 0; i < countryList.size() - 1; i++) {
                            String country = countryList.get(i).getCountry();
                            desc += country + " ";
                            desc += Util.countryNameToEmoji(country);

                            desc += ",\n";
                        }
                        String country = countryList.get(countryList.size() - 1).getCountry();
                        desc += country + " ";
                        desc += Util.countryNameToEmoji(country);
                        spec.setDescription(desc);

                    }).block();
        }
        else
        {
            String totalRegex = "";
            for (int i = 0; i < tokensOfMessage.size(); i++)
            {
                totalRegex += tokensOfMessage.get(i);
            }
            final String TOTALREGEX = totalRegex;
            Pattern pattern = Pattern.compile(totalRegex);
            Message m = channel.createEmbed(
                    (spec) ->
                    {
                        spec.setColor(Color.RED);
                        String desc = "";
                        List<Country> countryList = list.getObject().getData();
                        for (int i = 0; i < countryList.size(); i++) {
                            String country = countryList.get(i).getCountry();
                            if (pattern.matcher(country).matches())
                            {
                                desc += country + " ";
                                desc += Util.countryNameToEmoji(country);
                                desc += ",\n";
                            }
                        }
                        if (desc.length() == 0)
                        {
                            spec.setDescription("No countries which matches REGEX pattern: " + TOTALREGEX);
                        }
                        else
                        {
                            spec.setDescription(desc.substring(0, desc.length()-2));
                        }

                    }).block();

        }
    }
}
