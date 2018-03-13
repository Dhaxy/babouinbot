package com.arsene.babouinbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;

import java.util.List;

public class onMessageReceived extends BotCore implements IListener<MessageReceivedEvent> {
    public onMessageReceived(IDiscordClient client) {
        super(client);
        client.getDispatcher().registerListener(this);
    }

    @Override
    public void handle(MessageReceivedEvent messageReceivedEvent) {
        IMessage message = messageReceivedEvent.getMessage();
        IChannel channel = message.getChannel();
        if (channel.getName().contentEquals("blabla") && message.getContent().contentEquals("test")) {
            try {
                for (IChannel channelAnswer:client.getChannels()) {
                    if (channelAnswer.getName().contentEquals("annonces-robots"))
                        channel = channelAnswer;
                }
                new MessageBuilder(client).withChannel(channel).withContent("gotcha bitch").build();
            } catch (DiscordException exception) {
                exception.printStackTrace();
            }
        }
    }
}
