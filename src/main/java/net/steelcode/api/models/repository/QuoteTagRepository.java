package net.steelcode.api.models.repository;

import net.steelcode.api.models.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteTagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByTag(String tag);
}
