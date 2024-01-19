package net.steelcode.api.models.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyEntryDTO implements IDiscordFormat {

    private Long id;

    private String date;

    private Integer week;

    private Integer year;

    private List<String> indexLines;

    private String urlVideo;

    private String urlMessage;

    @Override
    public String getDiscordTextFormatted() {
        StringBuilder text = new StringBuilder("## Weekly Update — " + date + " - (Semana " + week + ")\n");
        text.append("Puedes consultar el original aqui: ").append(urlVideo).append("\n\n");
        text.append("**Indice**\n");

        for (String line: indexLines) {
            text.append("- ").append(line).append("\n");
        }

        text.append("\n**Puedes leer el weekly completo y traducido aqui: ").append(urlMessage).append("**");
        text.append("\n¡Puedes unirte a la comunidad de El Club De Las Tormentas! https://discord.gg/elclubdelastormentas");

        return text.toString();
    }
}
