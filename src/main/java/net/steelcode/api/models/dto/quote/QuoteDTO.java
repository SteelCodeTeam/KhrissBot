package net.steelcode.api.models.dto.quote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.steelcode.api.models.dto.IDiscordFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuoteDTO implements IDiscordFormat {
    private Long id;
    private String book;
    private int page;
    private String quote;
    private Boolean saidByAnyone;
    private String said;

    @Override
    public String getDiscordTextFormatted() {
        String str = "\"" + quote + "\"";
        if (saidByAnyone) {
            str = str + "\n- " + said;
        }
        str = str + "\n*" + book + ". Pagina " + page + ". Id: " + id + "*";

        return str;
    }
}
