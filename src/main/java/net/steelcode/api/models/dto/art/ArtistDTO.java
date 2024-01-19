package net.steelcode.api.models.dto.art;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.steelcode.api.models.dto.IDiscordFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO implements IDiscordFormat {

    private Long Id;
    private String nick;
    private String imageProfile;
    private String description;
    private String instagram;
    private String twitter;
    private String discord;
    private String behance;
    private String web;
    private String patreon;
    private String kofi;
    private Boolean hasCommissionsOpen;
    private String commissionSite;
    private String redPrincipal;
    private String linksDirectory;

    @Override
    public String getDiscordTextFormatted() {
        return null;
    }
}
