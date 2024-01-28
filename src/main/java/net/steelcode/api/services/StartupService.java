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

        log.info("Iniciando...");

    }
}
