package com.AQuality.core;

import com.AQuality.commands.Command;
import com.AQuality.commands.GetCountries;
import com.fasterxml.jackson.databind.ObjectMapper;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Util
{
    public static final String PREFIX = "aq!";
    public static final String AIRVISUALAPIKEY;
    public static final String DISCORDBOTTOKEN;

    public static HashMap<String, Command<MessageCreateEvent>> commandToConsumer= new HashMap<>();

    static
    {
        commandToConsumerFactory();

        ObjectMapper objectMapper = new ObjectMapper();
        Credentials creds = null;
        try {
            creds = objectMapper.readValue(new File("src/main/resources/config.json"), Credentials.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
        AIRVISUALAPIKEY = creds.getAirVisualApiKey();
        DISCORDBOTTOKEN = creds.getDiscordBotToken();

    }

    public static void commandToConsumerFactory()
    {
        commandToConsumer.put("countries", new GetCountries());

    }


    /**
     * gets the command from a message string,
     * @param str string starts with prefix
     * @return returns command
     */
    public static String getCommand(String str)
    {
        String ans = "";

        if (!isValidString(str))
        {
            return ans;
        }
        for (int i = PREFIX.length(); i < str.length(); i++)
        {
            char currChar = str.charAt(i);
            if (Character.isWhitespace(currChar))
            {
                break;
            }
            ans += currChar;
        }
        return ans.toLowerCase();
    }

    /**
     * checks if message is a message for the bot
     * @param str whole message
     * @return true or false based on if the message is directed towards the bot or not
     */
    public static boolean isValidString(String str)
    {
        if (str.length() <= PREFIX.length()
                || !str.substring(0, PREFIX.length()).equalsIgnoreCase(PREFIX))
        {
            return false;
        }
        return true;
    }

    public static List<String> getInputParams(String message)
    {
        ArrayList<String> str = new ArrayList<>();
        if (!isValidString(message))
        {
            return str;
        }
        int startIndex = PREFIX.length() + (getCommand(message).length()) +1;
        //aq!coun USA
        //01234456789
        //3+4+1 = 7
        String currStr = "";
        for (int i = startIndex; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (Character.isWhitespace(c))
            {
                str.add(currStr);
                currStr = "";
            }
            else
            {
                currStr += c;
            }
        }

        return str;
    }

    /**
     *
     * @param command prerequisite: string has already been passed through the getCommand() method
     * @param event event where the command was ran
     */
    public static void runCommand(String command, MessageCreateEvent event )
    {
        if (commandToConsumer.containsKey(command))
        {
            commandToConsumer.get(command).accept(event, event.getMessage().getChannel().block());
        }
    }



}
