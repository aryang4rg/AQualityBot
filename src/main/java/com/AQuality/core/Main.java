package com.AQuality.core;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;


public class Main {

    public static void main(String[] args) {
        DiscordClient client = DiscordClient.create(Util.DISCORDBOTTOKEN);
        GatewayDiscordClient gateway = client.login().block();
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


        gateway.onDisconnect().block();

    }

}
