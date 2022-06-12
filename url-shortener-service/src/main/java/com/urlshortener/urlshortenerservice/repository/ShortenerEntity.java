package com.urlshortener.urlshortenerservice.repository;

import javax.persistence.*;

@Entity
@Table(name = "URL_SHORTENER")
public class ShortenerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "SHORT_URL")
    private String shortUrl;

    @Column(name = "LONG_URL")
    private String longUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
