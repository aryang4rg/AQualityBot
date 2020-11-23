package com.AQuality.commands;

import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * command for giving out the important links
 */
public class LinksCommand extends Command
{
    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception {

        channel.createMessage(
                "You can visit the github repository with the full code of this bot here: https://github.com/slayerofthend/AQualityBot\n\n" +
                "You can also join the support server here if you have any questions or find any errors about the bot: https://discord.gg/tv2eKFbeza\n\n" +
                        "You can find our top.gg page here: https://top.gg/bot/449832399833137153\nplease upvote the bot!").block();

        channel.createEmbed(spec ->
        {
          spec.setColor(Color.RED)
                  .setTitle("You can invite the bot using this link")
                  .setUrl("https://discord.com/api/oauth2/authorize?client_id=449832399833137153&permissions=0&scope=bot")
                  .setDescription("A discord bot to help show the air quality and weather, " +
                          "using the AirVisual API to get data. Also uses a little bit of google's" +
                          " geocode api to get information about different locations.")
                  .setThumbnail(Util.gatewayDiscordClient.getSelf().block().getAvatarUrl());
        }
        ).block();
    }

    @Override
    public Command createNew() {
        return new LinksCommand();
    }

    @Override
    public String getHelpDesc() {
        return "Get links associated with the bot!";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters() {
        return new ArrayList<>();
    }
}
