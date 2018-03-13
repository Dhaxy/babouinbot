package com.arsene.babouinbot;

import sx.blah.discord.Discord4J;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.obj.Message;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;

public class BotCore {

    public static BotCore INSTANCE;
    public static IDiscordClient client;

    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("This bot needs at least 1 argument.");

        INSTANCE = login(args[0]);
        //new onConnect(client);
        new onMessageReceived(client);
        new onNewUser(client);
    }

    public BotCore(IDiscordClient client) {
        this.client = client;
    }

    public static BotCore login(String token) {
        BotCore bot = null;
        ClientBuilder clientBuilder = new ClientBuilder();
        clientBuilder.withToken(token);
        try {
            IDiscordClient client = clientBuilder.login();
            bot = new BotCore(client);
        } catch (DiscordException exception) {
            System.err.println("Error loging in !");
            exception.printStackTrace();
        }
        return bot;
    }
}
