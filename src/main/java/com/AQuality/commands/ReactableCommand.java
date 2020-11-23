package com.AQuality.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.ReactionAddEvent;

import java.util.TreeMap;

/**
 * interface for when a command is reactable
 */
public interface ReactableCommand
{
    String LEFTARROW = "⬅";
    String RIGHTARROW = "➡";

    /**
     * even that happens when a message is reacted to and is directed towards this object
     * @param obj Reaction Event from discord4j
     */
    void onReact(ReactionAddEvent obj);
}
