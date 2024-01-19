package net.steelcode.discord.events.slash_commands_events.text_commands.text;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.extern.java.Log;
import net.steelcode.api.models.dto.quote.QuoteDTO;
import net.steelcode.api.services.entities.QuoteService;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@Log
public class QuoteCommand implements ISlashCommand {

    @Autowired
    private QuoteService quoteService;

    @Override
    public String getName() {
        return "quote";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        log.info("handling quote event");

        Long id = event.getOption("id")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElse(null);

        String tag = event.getOption("tag")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);


        Optional<QuoteDTO> quoteDTO;
        if (tag == null && id == null) {
            return event.reply("No se ha enviado ningun parametro. Debes enviar un tag o un id.").withEphemeral(true);
        } else if (id != null) {
            quoteDTO = quoteService.findById(id);
            return quoteDTO.<Mono<Void>>map(quote -> event.reply(quoteDTO.get().getDiscordTextFormatted())).orElseGet(() -> event.reply("No se ha encontrado ninguna quote para el id: " + id + ".").withEphemeral(true));
        } else {
            quoteDTO = quoteService.findQuoteByTag(tag);
            return quoteDTO.<Mono<Void>>map(dto -> event.reply(quoteDTO.get().getDiscordTextFormatted())).orElseGet(() -> event.reply("No se ha encontrado ninguna quote para el tag: " + tag + ".").withEphemeral(true));
        }
    }
}
