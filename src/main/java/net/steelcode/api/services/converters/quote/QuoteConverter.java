package net.steelcode.api.services.converters.quote;

import net.steelcode.api.models.dto.quote.QuoteDTO;
import net.steelcode.api.models.entity.quote.Quote;
import net.steelcode.api.services.converters.AbstractConverter;
import org.springframework.stereotype.Service;

@Service
public class QuoteConverter extends AbstractConverter<Quote, QuoteDTO> {
    @Override
    public Quote fromDTO(QuoteDTO quoteDTO) {
        Quote quote = new Quote();

        quote.setQuote(quoteDTO.getQuote());
        quote.setId(quoteDTO.getId());
        quote.setPage(quoteDTO.getPage());
        quote.setBook(quoteDTO.getBook());
        quote.setSaidByAnyone(quoteDTO.getSaidByAnyone());
        quote.setSaid(quoteDTO.getSaid());
        quote.setSpoiler(quoteDTO.getSpoiler());

        return quote;
    }

    @Override
    public QuoteDTO fromEntity(Quote quote) {
        QuoteDTO quoteDTO = new QuoteDTO();

        quoteDTO.setQuote(quote.getQuote());
        quoteDTO.setId(quote.getId());
        quoteDTO.setPage(quote.getPage());
        quoteDTO.setBook(quote.getBook());
        quoteDTO.setSaidByAnyone(quote.getSaidByAnyone());
        quoteDTO.setSaid(quote.getSaid());
        quoteDTO.setSpoiler(quote.getSpoiler());

        return quoteDTO;
    }
}
