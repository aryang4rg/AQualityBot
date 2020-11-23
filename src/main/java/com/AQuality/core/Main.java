package com.AQuality.core;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.Presence;
import discord4j.discordjson.json.gateway.StatusUpdate;
import org.apache.log4j.PropertyConfigurator;

import java.util.Scanner;

/**
 * main method, where the thread starts
 */
public class Main {

    public static void main(String[] args) {

        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));


        DiscordClient client = DiscordClient.create(Util.DISCORDBOTTOKEN);
        GatewayDiscordClient gateway = client.login().block();
        Util.setGatewayDiscordClient(gateway);

        Thread t = new Thread(() -> {
            Scanner s = new Scanner(System.in);

            while (true)
            {
                if (s.next().equals("exit"))
                {
                    System.out.println("Goodbye");
                    System.exit(0);
                }

                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        t.start();

        gateway.updatePresence(StatusUpdate.builder()
                .game(Activity.watching("For the air quality and weather. Use aq!help for more info!"))
                .afk(false)
                .status(Presence.online().status())
                .build()).block();


        gateway.on(MessageCreateEvent.class).subscribe(
                (event) ->
                {
                    Message message = event.getMessage();
                    String strMessage = message.getContent();
                    if (Util.isValidString(strMessage))
                    {
                        String command = Util.getCommand(strMessage);
                        Util.runCommand(command, event);
                    }
                }
        );

        gateway.on(ReactionAddEvent.class).subscribe
                (
                        (event) ->
                        {
                            Util.onReact(event.getMessage().block().getId(), event);
                        }
                );


        gateway.onDisconnect().block();




    }

}
