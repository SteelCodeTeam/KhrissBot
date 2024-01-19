package net.steelcode.discord.listeners;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import net.steelcode.discord.events.ready_events.IReadyEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Component
public class ReadyEventListener {

    private final Collection<IReadyEvent> readyEvents;

    public ReadyEventListener(List<IReadyEvent> readyEvents, GatewayDiscordClient client) {

        this.readyEvents = readyEvents;
        client.on(ReadyEvent.class, this::handle).subscribe();
    }


    public Mono<Void> handle(ReadyEvent event) {
        return Flux.fromIterable(this.readyEvents)
                .next()
                .flatMap(iReadyEvent -> iReadyEvent.handle(event));
    }
}
