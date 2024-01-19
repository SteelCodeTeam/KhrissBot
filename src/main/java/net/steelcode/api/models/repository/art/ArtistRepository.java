package net.steelcode.api.models.repository.art;

import net.steelcode.api.models.entity.art.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    public Optional<Artist> findArtistByNick(String nick);
}
