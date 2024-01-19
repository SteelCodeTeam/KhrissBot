package net.steelcode.api.services;

import lombok.extern.java.Log;
import net.steelcode.api.models.repository.quote.QuoteRepository;
import net.steelcode.api.models.repository.QuoteTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Log
public class StartupService implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private QuoteTagRepository quoteTagRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {


        log.info("Incializando base de datos.");
        /*
        QuoteTags quoteTagsKaladin = new QuoteTags();
        quoteTagsKaladin.setTag("KALADIN");

        QuoteTags quoteTagsSarene = new QuoteTags();
        quoteTagsSarene.setTag("SARENE");

        QuoteTags quoteTagsElantris = new QuoteTags();
        quoteTagsElantris.setTag("ELANTRIS");


        Quote quoteKaladin = new Quote();
        quoteKaladin.setQuote("El honor ha muerto, pero veré que puedo hacer");
        quoteKaladin.setPage(123);
        quoteKaladin.setBook("Palabras Radiantes");
        quoteKaladin.setTags(List.of(quoteTagsKaladin));

        Quote quoteSarene = new Quote();
        quoteSarene.setQuote("Cosa guay que dijo sarene");
        quoteSarene.setPage(432);
        quoteSarene.setBook("Elantris");
        quoteSarene.setTags(List.of(quoteTagsSarene, quoteTagsElantris));

        quoteTagsElantris.setQuotes(List.of(quoteSarene));
        quoteTagsSarene.setQuotes(List.of(quoteSarene));
        quoteTagsKaladin.setQuotes(List.of(quoteKaladin));

        quoteRepository.save(quoteKaladin);
        quoteRepository.save(quoteSarene);

        quoteTagRepository.save(quoteTagsElantris);
        quoteTagRepository.save(quoteTagsKaladin);
        quoteTagRepository.save(quoteTagsSarene);
        */

    }
}
