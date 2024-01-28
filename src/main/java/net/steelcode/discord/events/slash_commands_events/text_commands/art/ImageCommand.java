package net.steelcode.discord.events.slash_commands_events.text_commands.art;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateFields;
import discord4j.rest.util.Color;
import lombok.extern.java.Log;
import net.steelcode.api.models.dto.art.ArtistDTO;
import net.steelcode.api.models.dto.art.ImageDTO;
import net.steelcode.api.services.entities.ImageAndArtistService;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log
public class ImageCommand implements ISlashCommand {

    @Autowired
    private ImageAndArtistService service;

    @Override
    public String getName() {
        return "imagen";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        log.info("handling image event");

        Long id = event.getOption("id")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElse(null);

        String tag = event.getOption("busqueda")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);

        String nick = event.getOption("nick")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asString)
                .orElse(null);

        return event.deferReply().then(Mono.fromCallable(() -> {
            return "Buscando imagen...";
        }).flatMap(message -> {

            event.editReply(message);
            Optional<ImageDTO> imageOpt;
            List<ArtistDTO> artistDTO;

            if (id != null) {
                imageOpt = service.findImageById(id);
            } else if (tag != null) {
                if (nick != null) {
                    imageOpt = service.findImageByTagAndArtist(tag, nick);
                } else {
                    imageOpt = service.findImageByTag(tag);
                }
            } else {
                return event.editReply("Debes introducir un parametro de busqueda o un id para buscar la imagen.");
            }

            if (imageOpt.isEmpty()) {
                return event.editReply("No se ha encontrado ninguna imagen.");
            }

            artistDTO = service.findArtistsByImageId(imageOpt.get().getId());

            List<EmbedCreateSpec> embeds = new ArrayList<>();
            for (ArtistDTO artist: artistDTO) {
                embeds.add(EmbedCreateSpec.builder()
                        .color(Color.BLUE)
                        .title("Link a las redes de " + artist.getNick())
                        .url(artist.getRedPrincipal())
                        .author("Imagen de " + imageOpt.get().getTitle(), null, "")
                        .thumbnail(artist.getImageProfile())
                        .description("Puedes ver mas informacion del artista con el comando /artista")
                        .footer("Mas artistas en Creativity Avatars. /creativity", null)
                        .build());
            }

            try {
                InputStream targetStream = new ByteArrayInputStream(imageOpt.get().getImage());

                return event.editReply("Puede contener spoilers de: **" + imageOpt.get().getSpoilers() + "**").withEmbeds(embeds).withFileSpoilers(MessageCreateFields.FileSpoiler.of("SPOILER_image.png", targetStream));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;

        })).then();

    }
}

