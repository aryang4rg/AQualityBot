package com.AQuality.commands;

import com.AQuality.api.AirVisualAPI.beans.countries.Country;
import com.AQuality.api.AirVisualAPI.beans.countries.CountriesCaller;
import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.object.reaction.ReactionEmoji;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//todo: this classes code is very messy

/**
 * Command for getting a list of all supported countries of the Air Visual API
 */
public class CountriesCommand extends Command implements ReactableCommand {

    private CountriesCaller list;
    private int pageNumber = 1;
    private final int numberOfElementsPerPage = 20;
    private String regexParameter = "";
    private Message message;

    @Override
    public Command createNew() {
        return new CountriesCommand();
    }

    @Override
    public void acceptImpl(MessageCreateEvent messageCreateEvent, MessageChannel channel) throws Exception {
        list = new CountriesCaller();
        List<String> tokensOfMessage = Util.getInputParams(messageCreateEvent.getMessage().getContent());
        if (Util.isInputType(tokensOfMessage, Integer.class))
        {
            pageNumber = Integer.parseInt(tokensOfMessage.get(0));
            if (pageNumber <= 0)
            {
                throw new UserException("Can not choose a page number under 1");
            }
            regexParameter = tokensOfMessage.get(0);
        }
        else if (Util.isInputType(tokensOfMessage, String.class, Integer.class))
        {
            regexParameter = tokensOfMessage.get(0);
            pageNumber = Integer.parseInt(tokensOfMessage.get(1) );
            throw new UserException("unable to parse page number: " + pageNumber + "into a number");
        }


        if (regexParameter.length() == 0)
        {
            message = channel.createEmbed(this::createNormalEmbed).block();
            makePageMessage();
        }
        else if(regexParameter.equalsIgnoreCase("all"))
        {
            message = channel.createEmbed(this::createEmbedWithAll).block();
        }
        else
        {
            message = channel.createEmbed(
                    this::createEmbedWithRegex
            ).block();
            makePageMessage();
        }
    }

    @Override
    public void onReact(ReactionAddEvent event) {
        if (regexParameter.equalsIgnoreCase("all"))
        {
            return;
        }
        if (event.getEmoji().equals(ReactionEmoji.unicode(LEFTARROW)))
        {
            if (pageNumber <= 1)
            {
                return;
            }
            pageNumber -= 1;

        }
        else if (event.getEmoji().equals(ReactionEmoji.unicode(RIGHTARROW)))
        {
            pageNumber += 1;
        }

        if (regexParameter.length() == 0)
        {
            message = message.edit(messageEditSpec -> {messageEditSpec.setEmbed(this::createNormalEmbed);}).block();
        }
        else
        {
            message = message.edit(messageEditSpec -> {messageEditSpec.setEmbed(this::createEmbedWithRegex);}).block();
        }

    }

    private void makePageMessage()
    {
        message.addReaction(ReactionEmoji.unicode(LEFTARROW)).block();
        message.addReaction(ReactionEmoji.unicode(RIGHTARROW)).block();
        Util.addToReactToConsumer(message.getId(), this);
    }

    private void createNormalEmbed(EmbedCreateSpec spec)
    {
        initializeSpec(spec);
        String desc = "";
        List<Country> countryList = list.getObject().getData();
        for (int i = numberOfElementsPerPage*(pageNumber -1); i < countryList.size() && i < pageNumber * numberOfElementsPerPage; i++) {
            String country = countryList.get(i).getCountry();
            desc += country + " ";
            desc += Util.countryNameToEmoji(country);

            desc += ",\n";
        }

        if (desc.length() == 0)
        {
            spec.setDescription("No Results Found!");
        }
        else {
            spec.setDescription(desc.substring(0, desc.length() - 2));
        }
        spec.setFooter("Page " + pageNumber, null);
    }

    private void createEmbedWithRegex(EmbedCreateSpec spec)
    {
        Pattern pattern = Pattern.compile(regexParameter);
        initializeSpec(spec);
        spec.setColor(Color.RED);
        String desc = "";
        List<Country> countryList = list.getObject().getData();
        ArrayList<String> countriesThatMatchRegex = new ArrayList<>();
        for (int i = 0; i < countryList.size(); i++)
        {
            String country = countryList.get(i).getCountry();
            if (pattern.matcher(country).matches())
            {
                countriesThatMatchRegex.add(country);
            }
        }
        if (countriesThatMatchRegex.size() == 0)
        {
            spec.setDescription("No countries which matches REGEX pattern: " + regexParameter);
        }
        else
        {
            for (int i = numberOfElementsPerPage*(pageNumber -1); i < countriesThatMatchRegex.size() && i < pageNumber * numberOfElementsPerPage; i++)
            {
                desc += countriesThatMatchRegex.get(i) + Util.countryNameToEmoji( countriesThatMatchRegex.get(i) ) + ",\n";
            }
            if (desc.length() == 0)
            {
                spec.setDescription("No Results Found!");
            }
            else
            {
                spec.setDescription(desc.substring(0, desc.length()-2));
            }
        }
        spec.setFooter("Page " + pageNumber, null);


    }

    private void createEmbedWithAll(EmbedCreateSpec spec)
    {
        initializeSpec(spec);
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
    }

    private void initializeSpec(EmbedCreateSpec spec)
    {
        spec.setColor(Color.RED);
        spec.setThumbnail("https://images-na.ssl-images-amazon.com/images/I/61GaHo-zgQL._AC_SL1000_.jpg"); //picture of world map
        spec.setTitle("Available  Countries");
    }

    @Override
    public String getHelpDesc() {
        return "Gets List of Supported Countries.";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters()
    {
        ArrayList<Pair<String, String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("(No Parameters)", "Gives list of countries from page one"));
        parameters.add(new Pair<>("(Integer) Page Number", "Gives list of countries from specified page"));
        parameters.add(new Pair<>("(String) Regex", "Gives list of countries that fit a regex pattern"));

        return parameters;

    }

}
