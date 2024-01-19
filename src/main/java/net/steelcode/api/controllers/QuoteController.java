package net.steelcode.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import net.steelcode.api.models.entity.quote.Quote;
import net.steelcode.api.services.entities.QuoteService;
import net.steelcode.discord.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@Controller
@RequestMapping(value = "/v1/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @GetMapping(path = "backup")
    public ResponseEntity<?> exportBackup(HttpServletRequest request, @RequestParam(required = false) String keyPass) {

        if (keyPass != null) {
            if (keyPass.equals(Configuration.KEYPASS)) { //1NA.G6YAaC.Zxmd
               if (request.getRemoteAddr().equals("127.0.0.1")) {
                   return ResponseEntity.status(HttpStatus.OK).body(quoteService.exportBackup());
               } else {
                   return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
               }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping(path = "backup")
    public ResponseEntity<?> importBackup(HttpServletRequest request, @RequestParam(required = false) String keyPass, @RequestBody List<Quote> quotes) {

        if (keyPass != null) {
            if (keyPass.equals(Configuration.KEYPASS)) { //1NA.G6YAaC.Zxmd
                if (request.getRemoteAddr().equals("127.0.0.1")) {
                    quoteService.importBackup(quotes);
                    return ResponseEntity.status(HttpStatus.CREATED).body(null);
                } else {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
