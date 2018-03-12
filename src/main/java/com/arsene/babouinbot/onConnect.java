package com.arsene.babouinbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;

public class onConnect extends BotCore implements IListener<ReadyEvent> {

    public onConnect(IDiscordClient client) {
        super(client);
        client.getDispatcher().registerListener(this);
    }

    @Override
    public void handle(ReadyEvent readyEvent) {
        try {
            for (IChannel channel : client.getChannels()) {
                System.out.println(channel.getName());
                System.out.println(channel.getLongID());
            }

            MessageBuilder message = new MessageBuilder(client);

            message.withContent("@everyone <:mrdestructoid:422739464281128978> I am the revolution OOK OOK <:mrdestructoid:422739464281128978>");

            IChannel res = null;
            for (IChannel channel : client.getChannels()) {
                if (channel.getName().equals("annonces-robots")) {
                    res = channel;
                } else {
                    System.out.println("Channel " + channel.getName() + " doesn't match");
                }
            }
            if (res == null)
                throw new DiscordException("Can't locate channels");
            System.out.println(res.getName());
            message.withChannel(res);
            message.build();
        } catch (DiscordException exception) {
            exception.printStackTrace();
        }
    }
}
