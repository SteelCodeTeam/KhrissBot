package net.steelcode.api.models.dto.art;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.steelcode.api.models.dto.IDiscordFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO implements IDiscordFormat {

    private Long id;
    private String title;
    private String spoilers;
    private byte[] image;

    @Override
    public String getDiscordTextFormatted() {
        return null;
    }
}
