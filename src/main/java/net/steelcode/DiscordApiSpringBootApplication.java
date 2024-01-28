package net.steelcode;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.rest.RestClient;

import net.steelcode.discord.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DiscordApiSpringBootApplication {

    public static void main(String[] args) {

        //Start spring application
        SpringApplication.run(DiscordApiSpringBootApplication.class, args);

    }

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(Configuration.TOKEN).build()
                .gateway()
                .setInitialPresence(ignore -> ClientPresence.online(ClientActivity.watching( "como programa SteelCodeTeam")))
                .login()
                .block();
    }

    @Bean
    public RestClient discordRestClient(GatewayDiscordClient client) {
        return client.getRestClient();
    }
}