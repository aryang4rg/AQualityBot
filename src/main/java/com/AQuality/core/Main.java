package com.AQuality.core;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.event.domain.message.ReactionAddEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.apache.log4j.BasicConfigurator;


public class Main {

    public static void main(String[] args) {

        BasicConfigurator.configure(); //idk what this line of code does but it fixed my issue


        DiscordClient client = DiscordClient.create(Util.DISCORDBOTTOKEN);
        GatewayDiscordClient gateway = client.login().block();
        Util.setGatewayDiscordClient(gateway);
        /*
        gateway.on(MessageCreateEvent.class).subscribe((
                event ->
                {
                    final Message message = event.getMessage();
                    System.out.println(message.getAuthor().get().getUsername() +" : " + message.getContent());
                    if (message.getContent().equals("!ping")) {
                        final MessageChannel channel = message.getChannel().block();
                        for (int i = 0; i < 100; i++) {
                            channel.createMessage("Pong!").block();
                        }
                    }
                }));

         */



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
