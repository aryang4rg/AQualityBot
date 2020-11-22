package com.AQuality.commands;

import com.AQuality.core.Pair;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;

import java.util.ArrayList;
import java.util.List;

public class InviteCommand extends Command
{
    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception {
        channel.createMessage("You can invite this bot to any server using this link: " +
                "https://discord.com/api/oauth2/authorize?client_id=449832399833137153&permissions=0&scope=bot").block();
    }

    @Override
    public Command createNew() {
        return new InviteCommand();
    }

    @Override
    public String getHelpDesc() {
        return "Get invite link for the bot!";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters() {
        return new ArrayList<>();
    }
}
