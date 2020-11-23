package com.AQuality.commands;

import com.AQuality.core.Pair;
import com.AQuality.core.Util;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * command for getting the ping of the current bot to a specified url / discord.gg
 */
public class PingCommand extends Command
{
    @Override
    public void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception {
        String urlToPing = "discord.gg";

        if (Util.isInputType(Util.getInputParams(obj.getMessage().getContent()), String.class))
        {
            urlToPing = Util.getInputParams(obj.getMessage().getContent()).get(0);
        }

        String finalUrlToPing = urlToPing;
        Thread t = new Thread(() -> {

            try {
                Runtime r = Runtime.getRuntime();
                Process p = r.exec("ping " + finalUrlToPing);
                p.waitFor();
                BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String line = "";
                String totalString = "";
                while ( (line = b.readLine()) != null )
                {
                    if (!line.isEmpty())
                    {
                        totalString += line + "\n";
                    }
                }
                b.close();
                channel.createMessage("```" + totalString + "```").block();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                channel.createMessage("There seems to be an internal error. Sorry about that!").block();
            }
        });
        t.start();
    }

    @Override
    public Command createNew() {
        return new PingCommand();
    }

    @Override
    public String getHelpDesc()
    {
        return "Find the ping of the bot to a URL";
    }

    @Override
    public List<Pair<String, String>> getHelpParameters()
    {
        ArrayList<Pair<String, String>> parameters = new ArrayList<>();
        parameters.add(new Pair<>("(No Input)", "Pings discord.gg and finds ping"));
        parameters.add(new Pair<>("(String) Url to Ping", "Pings specified URL"));

        return parameters;
    }
}
