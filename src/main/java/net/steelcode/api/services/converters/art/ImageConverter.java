package net.steelcode.api.services.converters.art;

import net.steelcode.api.models.dto.art.ImageDTO;
import net.steelcode.api.models.entity.art.Image;
import net.steelcode.api.services.converters.AbstractConverter;
import org.springframework.stereotype.Service;

@Service
public class ImageConverter extends AbstractConverter<Image, ImageDTO> {
    @Override
    public Image fromDTO(ImageDTO imageArtistDTO) {
        Image ia = new Image();
        ia.setId(imageArtistDTO.getId());
        ia.setImage(imageArtistDTO.getImage());
        ia.setTitle(imageArtistDTO.getTitle());
        ia.setSpoilers(imageArtistDTO.getSpoilers());

        return ia;
    }

    @Override
    public ImageDTO fromEntity(Image imageArtist) {
        ImageDTO dto = new ImageDTO();
        dto.setId(imageArtist.getId());
        dto.setImage(imageArtist.getImage());
        dto.setTitle(imageArtist.getTitle());
        dto.setSpoilers(imageArtist.getSpoilers());

        return dto;
    }
}
