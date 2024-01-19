package net.steelcode.api.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import net.steelcode.api.models.entity.art.Artist;
import net.steelcode.api.services.entities.ImageAndArtistService;
import net.steelcode.discord.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Log
@Controller
@RequestMapping(value = "/v1/artist")
public class ArtistController {

    @Autowired
    private ImageAndArtistService service;

    @PostMapping(path = "backup")
    public ResponseEntity<?> importBackup(HttpServletRequest request, @RequestParam(required = false) String keyPass, @RequestBody List<Artist> artists) {

        if (keyPass != null) {
            if (keyPass.equals(Configuration.KEYPASS)) { //1NA.G6YAaC.Zxmd
                if (request.getRemoteAddr().equals("127.0.0.1")) {
                    service.importBackup(artists);
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
