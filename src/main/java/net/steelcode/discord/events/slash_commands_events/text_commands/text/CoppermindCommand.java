package net.steelcode.discord.events.slash_commands_events.text_commands.text;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.event.domain.interaction.DeferrableInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.InteractionCallbackSpecDeferReplyMono;
import discord4j.rest.http.client.ClientException;
import lombok.extern.java.Log;
import net.steelcode.api.services.rest.CoppermindRestService;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Objects;

@Service
@Log
public class CoppermindCommand implements ISlashCommand {

    @Autowired
    private CoppermindRestService copperService;

    @Override
    public String getName() {
        return "coppermind";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        log.info("handling coppermind event");

        String tag = event.getOption("tag")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);


        return event.deferReply().then(Mono.fromCallable(() -> {
                    HashMap<String, String> searchResult = copperService.searchForTag(tag);

                    if (tag == null) {
                        return "No se ha enviado ningun parametro. Debes enviar un tag o un id.";
                    }

                    if (searchResult != null) {
                        String text = copperService.getDataFromCoppermind(searchResult.get("page"));
                        return text + "\n\n* Puedes seguir leyendo el articulo aqui: " + searchResult.get("link");
                    } else {
                        return "No se ha encontrado ningun articulo para el tag: " + tag + "\n\nPuedes buscar directamente aqui: https://es.coppermind.net/wiki/Coppermind:Bienvenidos";
                    }
                }
        )).flatMap(text -> event.editReply(text).then());
    }
}
