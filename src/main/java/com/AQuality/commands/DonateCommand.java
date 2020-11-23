package com.AQuality.commands;

import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.rest.util.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Command for user donations
 */
public class DonateCommand extends Command
{
    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception {
        User author = Util.gatewayDiscordClient.getUserById(Util.AuthorId).block();
        channel.createEmbed(spec ->
        {
            spec.setColor(Color.SEA_GREEN);
            spec.setAuthor(author.getTag(), "https://github.com/slayerofthend", author.getAvatarUrl());
            spec.setThumbnail(author.getAvatarUrl());
            spec.setDescription(
                    "Thank you for wanting to donate, though it is 100% unnecessary, though it does help support me and keep this project " +
                            "running! Ways to donate are through:\n" +
                     "Bitcoin: bc1qxk54e7js4202wtq7h9vefse082l55y8u8sfwek\n" +
                            "Ethereum: 0xa85c6F9381285c770cf0B42Bd57c18C5998fc199\n" +
                            "Litecoin: LYG1MsX6mPoaPhp2Lh5hCzfheMh8K9juyP");
        }
        ).block();
    }

    @Override
    public Command createNew() {
        return new DonateCommand();
    }

    @Override
    public String getHelpDesc() {
        return "Place to donate for the creator of this bot.";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters() {
        return new ArrayList<>();
    }
}
