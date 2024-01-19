package net.steelcode.discord.events.ready_events;

import discord4j.common.util.Snowflake;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@Log
public class GetGuildsOnStartupEvent implements IReadyEvent {

    @Override
    public Mono<Void> handle(ReadyEvent event) {
        Set<ReadyEvent.Guild> guilds = event.getGuilds();

        StringBuilder str = new StringBuilder();

        str.append("# Los servidores activos son los siguientes: " );
        for (ReadyEvent.Guild guild: guilds) {
            str.append("\"" + event.getClient().getGuildById(guild.getId()).block().getName() + "\", ");
        }

        log.info(str.toString());

        return Mono.empty();
    }
}
