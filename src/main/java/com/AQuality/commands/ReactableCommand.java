package com.AQuality.commands;

import discord4j.common.util.Snowflake;

import java.util.TreeMap;

public interface ReactableCommand<T>
{
    void onReact(T obj);
}
