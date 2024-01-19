package net.steelcode.api.services.entities;

import lombok.extern.java.Log;
import net.steelcode.api.models.dto.art.ArtistDTO;
import net.steelcode.api.models.dto.art.ImageDTO;
import net.steelcode.api.models.entity.art.Artist;
import net.steelcode.api.models.entity.art.Image;
import net.steelcode.api.models.entity.Tag;
import net.steelcode.api.models.repository.art.ArtistRepository;
import net.steelcode.api.models.repository.art.ImageRepository;
import net.steelcode.api.models.repository.QuoteTagRepository;
import net.steelcode.api.services.converters.art.ArtistConverter;
import net.steelcode.api.services.converters.art.ImageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class ImageAndArtistService {

    @Autowired
    private ImageConverter imageConverter;
    @Autowired
    private ArtistConverter artistConverter;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private QuoteTagRepository tagRepo;

    public List<Artist> exportArtistBackup() {
        return artistRepository.findAll();
    }

    public void importBackup(List<Artist> artists) {
        artistRepository.saveAllAndFlush(artists);
    }

    public Optional<ImageDTO> findImageByTag(String tag) {
        Optional<Tag> optTag = tagRepo.findByTag(tag.toUpperCase());
        if (optTag.isEmpty()) {
            return Optional.empty();
        } else {
            List<Image> image = optTag.get().getImages();
            if (image.isEmpty()) {
                return Optional.empty();
            } else {
                Collections.shuffle(image);
                return Optional.of(imageConverter.fromEntity(image.get(0)));
            }
        }
    }

    public Optional<ImageDTO> findImageById(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        return image.map(imageArtist -> imageConverter.fromEntity(imageArtist));
    }

    public Optional<ImageDTO> findImageByTagAndArtist(String tag, String artist) {
        Optional<Tag> optTag = tagRepo.findByTag(tag.toUpperCase());
        if (optTag.isEmpty()) {
            return Optional.empty();
        } else {
            List<Image> image = optTag.get().getImages();
            if (image.isEmpty()) {
                return Optional.empty();
            } else {
                List<Image> imagesFromArtist = new ArrayList<>(image.stream().filter(img -> img.getArtists().stream().filter(art -> art.getNick().equals(artist)).isParallel()).toList());
                Collections.shuffle(imagesFromArtist);
                return Optional.of(imageConverter.fromEntity(image.get(0)));
            }
        }
    }

    public List<ArtistDTO> findArtistsByImageId(Long id) {
        List<ArtistDTO> artists;
        Optional<Image> image = imageRepository.findById(id);

        if (image.isPresent()) {
            artists = artistConverter.fromEntities(image.get().getArtists());
        } else {
            artists = new ArrayList<>();
        }

        return artists;
    }

    public Optional<ArtistDTO> findArtistById(Long id) {
        Optional<Artist> artistOpt = artistRepository.findById(id);
        return artistOpt.map(artist -> artistConverter.fromEntity(artist));
    }
    public Optional<ArtistDTO> findArtistByNick(String nick) {
        Optional<Artist> artistOpt = artistRepository.findArtistByNick(nick);
        return artistOpt.map(artist -> artistConverter.fromEntity(artist));
    }

}
