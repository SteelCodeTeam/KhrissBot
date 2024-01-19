package net.steelcode.api.models.repository.quote;

import net.steelcode.api.models.entity.quote.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
