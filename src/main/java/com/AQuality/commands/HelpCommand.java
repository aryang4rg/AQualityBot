package com.AQuality.commands;

import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Help command that lists all of the commands this bot takes
 */
public class HelpCommand extends Command{

    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception {
        channel.createEmbed(spec ->
                {
                    spec.setDescription("Only add white space to input if you are trying to enter a new parameter.");
                    for (String command : Util.commandNamesForHelpCommand.keySet())
                    {
                        String descForHelp = Util.commandNamesForHelpCommand.get(command).getHelpDesc() + "\n";
                        for (Pair<String, String> paramAndDesc : Util.commandNamesForHelpCommand.get(command).getHelpParameters())
                        {
                            descForHelp += "        " + paramAndDesc.getVal1() + " : " + paramAndDesc.getVal2() + "\n";
                        }
                        spec.addField(command, descForHelp ,false);
                    }
                    spec.setColor(Color.RED);
                    spec.setThumbnail(Util.gatewayDiscordClient.getSelf().block().getAvatarUrl());
                }
        ).block();
    }

    @Override
    public Command createNew() {
        return new HelpCommand();
    }

    @Override
    public String getHelpDesc() {
        return "Get all information about the commands you can use with this bot";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters() {
        return new ArrayList<>();
    }
}
