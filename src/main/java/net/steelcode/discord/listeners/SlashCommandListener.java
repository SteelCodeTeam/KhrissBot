package net.steelcode.discord.listeners;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

@Component
public class SlashCommandListener {

    private final Collection<ISlashCommand> commands;

    public SlashCommandListener(List<ISlashCommand> slashCommands, GatewayDiscordClient client) {
        commands = slashCommands;

        client.on(ChatInputInteractionEvent.class, this::handle).retry().subscribe();
    }


    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return Flux.fromIterable(commands)
                .filter(command -> command.getName().equals(event.getCommandName()))
                .next()
                .timeout(Duration.of(60, ChronoUnit.SECONDS))
                .flatMap(command -> command.handle(event));
    }
}
