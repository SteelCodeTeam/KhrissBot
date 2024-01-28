package net.steelcode.api.models.repository.art;

import net.steelcode.api.models.entity.Tag;
import net.steelcode.api.models.entity.art.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    @Query(value="SELECT image.* " +
            "FROM image, tag, image_tags " +
            "WHERE image.id = image_tags.image_id " +
            "AND tag.id = image_tags.tag_id " +
            "AND tag.tag = :tagStr " +
            "ORDER BY RAND() " +
            "LIMIT 1;", nativeQuery = true)
    Optional<Image> findImagesByTags(@Param("tagStr")String tag);
}
