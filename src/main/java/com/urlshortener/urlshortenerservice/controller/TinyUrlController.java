package com.urlshortener.urlshortenerservice.controller;

import com.urlshortener.urlshortenerservice.repository.ShortenerEntity;
import com.urlshortener.urlshortenerservice.service.TinyUrlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tinyUrlService")
public class TinyUrlController {

    private TinyUrlService tinyUrlService;

    TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @GetMapping("/getShortUrl/{longUrl}")
    public String getShortenedUrl(@PathVariable("longUrl") String longUrl) {
        return tinyUrlService.getShortenedUrl(longUrl);
    }

    @GetMapping("getOriginalUrl/{shortUrl}")
    public String getOriginalUrl(@PathVariable("shortUrl") String shortUrl) {
        return tinyUrlService.getOriginalUrl(shortUrl);
    }

}
