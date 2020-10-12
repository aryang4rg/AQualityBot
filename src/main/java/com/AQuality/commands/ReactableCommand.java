package com.AQuality.commands;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.message.ReactionAddEvent;

import java.util.TreeMap;

public interface ReactableCommand
{
    void onReact(ReactionAddEvent obj);
}
