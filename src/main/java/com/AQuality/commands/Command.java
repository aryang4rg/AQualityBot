package com.AQuality.commands;

import com.AQuality.AirVisualAPI.APIException;
import discord4j.core.object.entity.channel.MessageChannel;

public abstract class Command<T>
{
    public void accept(T obj, MessageChannel channel)
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
                    channel.createMessage("Too many people have been making calls. Please try again in a couple of minutes!");
                    break;
                }
                case("api_key_expired"):
                case("incorrect_api_key"): {
                    channel.createMessage("Uh oh, the bot seems to be having the wrong api key");
                    break;
                }
                case("no_nearest_station"):
                {
                    channel.createMessage("There seems to not be a station near the location you chose");
                    break;
                }
                default:
                {
                    channel.createMessage("API ERROR: " + error);
                }
            }
        }
        catch (Exception e)
        {
            channel.createMessage("There seems to be an internal error! Sorry about that.");
            e.printStackTrace();
            //TODO add a logging function here
        }

    }
    public abstract void acceptImpl(T obj, MessageChannel channel) throws Exception;

}
