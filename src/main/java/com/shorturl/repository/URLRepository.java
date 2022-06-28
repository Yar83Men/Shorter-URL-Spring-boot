package com.shorturl.repository;

import com.shorturl.model.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.URL;

@Repository
public interface URLRepository extends JpaRepository<URLEntity, Integer> {
    URLEntity findByConvertedUrl(String hash);
    URLEntity findByConvertedUrlOrPrimaryUrl(String converted, String primary);
}
