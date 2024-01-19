package net.steelcode.api.services.converters.art;

import net.steelcode.api.models.dto.art.ArtistDTO;
import net.steelcode.api.models.entity.art.Artist;
import net.steelcode.api.services.converters.AbstractConverter;
import org.springframework.stereotype.Service;

@Service
public class ArtistConverter extends AbstractConverter<Artist, ArtistDTO> {
    @Override
    public Artist fromDTO(ArtistDTO artistDTO) {
        Artist artist = new Artist();
        artist.setId(artistDTO.getId());
        artist.setDescription(artistDTO.getDescription());
        artist.setDiscord(artistDTO.getDiscord());
        artist.setBehance(artistDTO.getBehance());
        artist.setKofi(artistDTO.getKofi());
        artist.setNick(artistDTO.getNick());
        artist.setCommissionSite(artistDTO.getCommissionSite());
        artist.setPatreon(artistDTO.getPatreon());
        artist.setTwitter(artistDTO.getTwitter());
        artist.setInstagram(artistDTO.getInstagram());
        artist.setImageProfile(artistDTO.getImageProfile());
        artist.setRedPrincipal(artistDTO.getRedPrincipal());
        artist.setWeb(artistDTO.getWeb());
        artist.setLinksDirectory(artistDTO.getLinksDirectory());
        artist.setHasCommissionsOpen(artistDTO.getHasCommissionsOpen());

        return artist;
    }

    @Override
    public ArtistDTO fromEntity(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());
        dto.setDescription(artist.getDescription());
        dto.setDiscord(artist.getDiscord());
        dto.setBehance(artist.getBehance());
        dto.setKofi(artist.getKofi());
        dto.setNick(artist.getNick());
        dto.setCommissionSite(artist.getCommissionSite());
        dto.setPatreon(artist.getPatreon());
        dto.setTwitter(artist.getTwitter());
        dto.setInstagram(artist.getInstagram());
        dto.setImageProfile(artist.getImageProfile());
        dto.setRedPrincipal(artist.getRedPrincipal());
        dto.setWeb(artist.getWeb());
        dto.setLinksDirectory(artist.getLinksDirectory());
        dto.setHasCommissionsOpen(artist.getHasCommissionsOpen());

        return dto;
    }
}
