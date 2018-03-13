package com.arsene.babouinbot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.impl.obj.Role;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.RoleBuilder;

import javax.management.relation.RoleList;

public class onNewUser extends BotCore implements IListener<UserJoinEvent> {

    public onNewUser(IDiscordClient client) {
        super(client);
        client.getDispatcher().registerListener(this);
    }

    @Override
    public void handle(UserJoinEvent userJoinEvent) {
        String username = userJoinEvent.getUser().getName();
        IChannel channel = null;
        IRole addRole = null;
        try {
            for (IChannel res : client.getChannels()) {
                if (res.getName().contentEquals("welcome-messages")) {
                    channel = res;
                }
            }
            for (IRole role : client.getRoles()) {
                System.out.println(role.getName() + " - color : " + role.getColor() + " - string id :  " + role.getStringID() + " - long id : " + role.getLongID());
                if (role.getName().contentEquals("Les habitants de la jungle")) {
                    addRole = role;
                }
            }
            new MessageBuilder(client).withChannel(channel).withContent("Welcome to the jungle " + username + " !").build();
            userJoinEvent.getUser().addRole(addRole);
        } catch (DiscordException exception) {
            exception.printStackTrace();
        }
    }
}