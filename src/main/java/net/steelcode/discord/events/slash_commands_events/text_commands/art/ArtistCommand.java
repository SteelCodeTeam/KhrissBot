package net.steelcode.discord.events.slash_commands_events.text_commands.art;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateFields;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.rest.util.Color;
import lombok.extern.java.Log;
import net.steelcode.api.models.dto.art.ArtistDTO;
import net.steelcode.api.services.entities.ImageAndArtistService;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ArtistCommand implements ISlashCommand {

    @Autowired
    private ImageAndArtistService service;

    @Override
    public String getName() {
        return "artista";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        log.info("handling artista event");

        String nick = event.getOption("nick")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);

        Optional<ArtistDTO> artistDTO;
        if (nick != null) {
            artistDTO = service.findArtistByNick(nick);
        } else {
            return event.reply("Debes enviar un nick para consultar su tarjeta de artista.").withEphemeral(true);
        }

        if (artistDTO.isEmpty()) {
            return event.reply("No hay ningun artista registrado con ese nick.").withEphemeral(true);
        }

        System.out.println(artistDTO.get());

        EmbedCreateSpec.Builder embedBuilder = EmbedCreateSpec.builder()
                .color(Color.PINK)
                .title(artistDTO.get().getNick())
                .url(artistDTO.get().getRedPrincipal())
                .thumbnail(artistDTO.get().getImageProfile())
                .description(artistDTO.get().getDescription())
                .footer("Puedes unirte como artista usando el comando /creativity", null);

        List<EmbedCreateFields.Field> fields = new ArrayList<>();
        if (artistDTO.get().getBehance() != null && !artistDTO.get().getBehance().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Behance", artistDTO.get().getBehance(), true));
        }
        if (artistDTO.get().getInstagram() != null && !artistDTO.get().getInstagram().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Instagram", artistDTO.get().getInstagram(), true));
        }
        if (artistDTO.get().getTwitter() != null && !artistDTO.get().getTwitter().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Twitter", artistDTO.get().getTwitter(), true));
        }
        if (artistDTO.get().getPatreon() != null && !artistDTO.get().getPatreon().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Patreon", artistDTO.get().getPatreon(), true));
        }
        if (artistDTO.get().getKofi() != null && !artistDTO.get().getKofi().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Ko-Fi", artistDTO.get().getKofi(), true));
        }
        if (artistDTO.get().getDiscord() != null && !artistDTO.get().getDiscord().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Discord", artistDTO.get().getDiscord(), true));
        }
        if (artistDTO.get().getWeb() != null && !artistDTO.get().getWeb().isBlank()) {
            fields.add(EmbedCreateFields.Field.of("Web Personal", artistDTO.get().getWeb(), true));
        }
        fields.add(EmbedCreateFields.Field.of("Comisiones", (artistDTO.get().getHasCommissionsOpen()) ? "Abiertas" : "Cerradas", false));

        EmbedCreateSpec embed = embedBuilder.addAllFields(fields).build();

        return event.reply().withEmbeds(List.of(embed));

    }
}