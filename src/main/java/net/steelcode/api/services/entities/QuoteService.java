package net.steelcode.api.services.entities;

import lombok.extern.java.Log;
import net.steelcode.api.models.dto.quote.QuoteDTO;
import net.steelcode.api.models.entity.quote.Quote;
import net.steelcode.api.models.entity.Tag;
import net.steelcode.api.models.repository.quote.QuoteRepository;
import net.steelcode.api.models.repository.QuoteTagRepository;
import net.steelcode.api.services.converters.quote.QuoteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class QuoteService {

    @Autowired
    private QuoteRepository quoteRepo;
    @Autowired
    private QuoteTagRepository tagRepo;
    @Autowired
    private QuoteConverter quoteConverter;

    public void importBackup(List<Quote> quotes) {
        quoteRepo.saveAll(quotes);
    }

    public List<Quote> exportBackup() {
        List<Quote> quotes = quoteRepo.findAll();
        return quotes;
    }

    public void addQuote(Quote quote) {
        quoteRepo.save(quote);
    }

    public Optional<QuoteDTO> findById(Long id) {
        Optional<Quote> optQuote = quoteRepo.findById(id);
        return optQuote.map(quote -> quoteConverter.fromEntity(quote));
    }

    public Optional<QuoteDTO> findQuoteByTag(String tag) {
        Optional<Tag> optQuoteTag = tagRepo.findByTag(tag.toUpperCase());
        if (optQuoteTag.isEmpty()) {
            return Optional.empty();
        } else {
            List<Quote> quotes = optQuoteTag.get().getQuotes();
            if (quotes.isEmpty()) {
                return Optional.empty();
            } else {
                Collections.shuffle(quotes);
                return Optional.of(quoteConverter.fromEntity(quotes.get(0)));
            }
        }
    }

}
