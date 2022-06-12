package com.urlshortener.urlshortenerservice.service;

import com.urlshortener.urlshortenerservice.repository.ShortenerEntity;
import com.urlshortener.urlshortenerservice.repository.TinyUrlRepository;
import org.springframework.stereotype.Service;

@Service
public class TinyUrlService {

    private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String prefix = "www.bit.ly/";
    private TinyUrlRepository tinyUrlRepository;

    TinyUrlService(TinyUrlRepository tinyUrlRepository) {
        this.tinyUrlRepository = tinyUrlRepository;
    }

    public String getShortenedUrl(String longUrl) {
        ShortenerEntity shortenerEntity = new ShortenerEntity();
        shortenerEntity.setLongUrl(longUrl);
        shortenerEntity = tinyUrlRepository.save(shortenerEntity);
        tinyUrlRepository.flush();
        int id = shortenerEntity.getId();

        String shortUrl = generateShortUrl(id);
        shortenerEntity.setShortUrl(prefix + shortUrl);
        tinyUrlRepository.save(shortenerEntity);

        return prefix + shortUrl;
    }

    /**
     * Base 62 encoding to generate unique short url
     * @param id
     * @return
     */
    private String generateShortUrl(int id) {
        char[] map = chars.toCharArray();

        StringBuilder str = new StringBuilder();
        while(id > 0) {
            str.append(map[id%62]);
            id = id/62;
        }

        while(str.length() < 6) {
            str.append('a');
        }

        return str.reverse().toString();
    }

    /**
     * getting id from the shorturl and thereby getting the long original url using the id generated
     * @param shortUrl
     * @return
     */
    public String getOriginalUrl(String shortUrl) {
        int id = 0;
        String suffix = shortUrl.substring(shortUrl.indexOf('/')+1);
        //String suffix = shortUrl.substring(prefix.length()-1);
        for(int i=0;i<suffix.length();i++) {
            if(suffix.charAt(i) >= 'a' && suffix.charAt(i) <= 'z') {
                id = id*62 + suffix.charAt(i) - 'a';
            } else if (suffix.charAt(i) >= 'A' && suffix.charAt(i) <= 'Z') {
                id = id*62 + suffix.charAt(i) - 'A' + 26;
            } else {
                id = id*62 + suffix.charAt(i) - '0' + 52;
            }
        }
        System.out.println(id);

        return tinyUrlRepository.getOriginalUrlUsingId(id);
    }
}
