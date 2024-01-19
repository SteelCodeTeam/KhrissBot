package net.steelcode.discord.events.ready_events;

import discord4j.core.event.domain.lifecycle.ReadyEvent;
import reactor.core.publisher.Mono;

public interface IReadyEvent {

    Mono<Void> handle(ReadyEvent event);
}
