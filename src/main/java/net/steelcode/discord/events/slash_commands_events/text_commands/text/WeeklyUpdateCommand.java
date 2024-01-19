package net.steelcode.discord.events.slash_commands_events.text_commands.text;

import discord4j.core.event.domain.interaction.ChatInputInteractionEvent;
import discord4j.core.object.command.ApplicationCommandInteractionOption;
import discord4j.core.object.command.ApplicationCommandInteractionOptionValue;
import lombok.extern.java.Log;
import net.steelcode.api.models.dto.WeeklyEntryDTO;
import net.steelcode.api.services.entities.WeeklyEntryService;
import net.steelcode.discord.events.slash_commands_events.ISlashCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log
public class WeeklyUpdateCommand implements ISlashCommand {

    @Autowired
    private WeeklyEntryService service;

    @Override
    public String getName() {
        return "weekly";
    }

    @Override
    public Mono<Void> handle(ChatInputInteractionEvent event) {
        log.info("handling weekly update event");


        Long week = event.getOption("week")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElse(null);

        Long year = event.getOption("year")
                .flatMap(ApplicationCommandInteractionOption::getValue)
                .map(ApplicationCommandInteractionOptionValue::asLong)
                .orElse(null);

        WeeklyEntryDTO dto;

        if (year != null) {
            if (week != null) {
                dto = service.getWeeklyEntryFromWeekAndYear(Math.toIntExact(week), Math.toIntExact(year));
            } else {
                return event.reply()
                        .withEphemeral(true)
                        .withContent("Necesitas especificar la semana y el año, o solamente la semana si consultas el ultimo año");
            }
        } else {
            if (week != null) {
                dto = service.getWeeklyEntryFromWeek(Math.toIntExact(week));
            } else {
                dto = service.getLastWeeklyEntry();
            }
        }

        if (dto != null) {
            return event.reply()
                    .withEphemeral(false)
                    .withContent(dto.getDiscordTextFormatted());
        } else {
            return event.reply()
                    .withEphemeral(true)
                    .withContent("¡No se ha podido recuperar una weekly update para esa fecha! Puedes consultar los disponibles aqui: https://discord.gg/elclubdelastormentas");
        }

    }
}
