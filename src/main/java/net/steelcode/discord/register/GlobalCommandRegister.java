package net.steelcode.discord.register;

import discord4j.common.JacksonResources;
import discord4j.core.object.entity.Guild;
import discord4j.discordjson.json.ApplicationCommandRequest;
import discord4j.discordjson.json.UserGuildData;
import discord4j.rest.RestClient;
import discord4j.rest.service.ApplicationService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@Component
public class GlobalCommandRegister implements ApplicationRunner {


    private final RestClient client;

    public GlobalCommandRegister(RestClient client) {
        this.client = client;
    }
    @Override
    public void run(ApplicationArguments args) throws IOException {
        final JacksonResources d4jMapper = JacksonResources.create();

        // Convenience variables for the sake of easier to read code below.
        PathMatchingResourcePatternResolver matcher = new PathMatchingResourcePatternResolver();
        final ApplicationService applicationService = client.getApplicationService();
        final long applicationId = client.getApplicationId().block();


        //Get our commands json from resources as command data
        List<ApplicationCommandRequest> commands = new ArrayList<>();
        for (Resource resource : matcher.getResources("commands/global/*.json")) {
            ApplicationCommandRequest request = d4jMapper.getObjectMapper()
                    .readValue(resource.getInputStream(), ApplicationCommandRequest.class);

            commands.add(request);
        }

        applicationService.bulkOverwriteGlobalApplicationCommand(applicationId, commands)
                .doOnNext(ignore -> log.info("Successfully registered Global Command: " + ignore.name()))
                .doOnError(e -> log.warning("Failed to register global commands: " + e.toString()))
                .subscribe();


    }
}
