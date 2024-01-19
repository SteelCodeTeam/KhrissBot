package net.steelcode.discord.events.slash_commands_events.text_commands.fixed_response;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreativityCommand implements ISlashCommand {

    @Override
    public String getName() {
        return "creativity";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply()
                .withEphemeral(true)
                .withContent("Si quieres subir tu arte al bot o quieres unirte al discord de la comunidad de artistas puedes unirte con este link: https://discord.gg/NqnTwg62XH");
    }
}