package net.steelcode.discord.events.slash_commands_events.text_commands.fixed_response;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CosmereEsCommand implements ISlashCommand {

    @Override
    public String getName() {
        return "cosmere_es";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        return event.reply()
                .withEphemeral(true)
                .withContent("""
Puedes estar al dia de las ultimas novedades en https://cosmere.es
Puedes entrar a la coppermind en español aqui: https://es.coppermind.net/wiki/Coppermind:Bienvenidos
Puedes unirte al discord aqui: https://discord.com/invite/KuVRdF2""");
    }
}
