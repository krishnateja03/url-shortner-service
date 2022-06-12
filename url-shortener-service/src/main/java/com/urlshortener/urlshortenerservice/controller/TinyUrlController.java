package com.urlshortener.urlshortenerservice.controller;

import com.urlshortener.urlshortenerservice.service.TinyUrlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * sample url for getShortenedUrl endpoint :
 * http://localhost:8080/tinyUrlService/getShortUrl?longUrl=www.facebook.com/krisha/photos/latest/demo.png
 *
 * sample url for getOriginalUrl endpoint :
 * http://localhost:8080/tinyUrlService/getOriginalUrl?shortUrl=www.bit.ly/asfgoq
 */
@RestController
@RequestMapping("/tinyUrlService")
public class TinyUrlController {

    private TinyUrlService tinyUrlService;

    TinyUrlController(TinyUrlService tinyUrlService) {
        this.tinyUrlService = tinyUrlService;
    }

    @GetMapping("/getShortUrl")
    public String getShortenedUrl(@RequestParam("longUrl") String longUrl) {
        return tinyUrlService.getShortenedUrl(longUrl);
    }

    @GetMapping("getOriginalUrl")
    public String getOriginalUrl(@RequestParam("shortUrl") String shortUrl) {
        return tinyUrlService.getOriginalUrl(shortUrl);
    }

}
