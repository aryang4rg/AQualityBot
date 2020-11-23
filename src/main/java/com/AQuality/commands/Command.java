package com.AQuality.commands;

import com.AQuality.api.APIException;
import com.AQuality.core.Pair;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.channel.MessageChannel;

import java.util.List;

/**
 * abstract class that all classes that want to accept commands have to extend
 */
public abstract class Command
{
    /**
     * Accepts a message create event and runs acceptImpl but with exception checking
     * @param obj message create object
     */
    public void accept(MessageCreateEvent obj)
    {
        MessageChannel channel = obj.getMessage().getChannel().block();
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

    /**
     * implementation of what happens when a message is created and directed towards this command
     * @param obj MessageCreateObj
     * @param channel channel where this happened
     * @throws Exception idk many things could have happened, message should be handled in the accept() method hopefully
     */
    public abstract void acceptImpl(MessageCreateEvent obj, MessageChannel channel) throws Exception;

    /**
     * create new of this object
     * @return just creates a new version of this object
     */
    public abstract Command createNew();

    /**
     * used to get a description of what the command does for the help method
     * @return description of what this command does
     */
    public abstract String getHelpDesc();

    /**
     * gets help documentation on what parameters it takes in
     * @return a list of all the parameters in a pair, the first pair being the parameters it takes in
     * in the form of (Class, Class) desc, desc. The second element is a description of what that parameter list
     * does
     */
    public abstract  List<Pair<String, String>> getHelpParameters();

}
