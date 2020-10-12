package com.AQuality.commands;

import com.AQuality.AirVisualAPI.APIException;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;

public abstract class Command
{
    public void accept(MessageCreateEvent obj, MessageChannel channel)
    {
        try
        {
            acceptImpl(obj, channel);
        }
        catch (APIException e)
        {
            e.printStackTrace();
            String error = e.getMessage();
            switch (error)
            {
                case("call_limit_reached"):
                case ("too_many_requests"):
                {
                    channel.createMessage("Too many people have been making calls. Please try again in a couple of minutes!").block();
                    break;
                }
                case("api_key_expired"):
                case("incorrect_api_key"): {
                    channel.createMessage("Uh oh, the bot seems to be having the wrong api key").block();
                    break;
                }
                case("no_nearest_station"):
                {
                    channel.createMessage("There seems to not be a station near the location you chose").block();
                    break;
                }
                default:
                {
                    channel.createMessage("API ERROR: " + error).block();
                }
            }
        }
        catch (UserException e)
        {
            channel.createMessage(e.getMessage());
        }
        catch (Exception e)
        {
            channel.createMessage("There seems to be an internal error! Sorry about that.").block();
            e.printStackTrace();
            //TODO add a logging function here
        }

    }
    public abstract void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception;
    public abstract Command createNew();

}
