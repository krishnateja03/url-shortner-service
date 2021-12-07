package com.urlshortener.urlshortenerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TinyUrlRepository extends JpaRepository<ShortenerEntity, Integer> {

    @Query("select se.id from ShortenerEntity as se where longUrl = :longUrl")
    int getIdUsingLongUrl(@Param("longUrl") String longUrl);

    @Query("select se.longUrl from ShortenerEntity as se where id = :id")
    String getOriginalUrlUsingId(@Param("id") int id);
}
