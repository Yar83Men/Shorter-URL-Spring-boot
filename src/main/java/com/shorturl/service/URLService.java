package com.shorturl.service;

import com.shorturl.model.URLEntity;
import com.shorturl.repository.URLRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class URLService {
    private final URLRepository urlRepository;

    @Autowired
    public URLService(URLRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public URLEntity saveUrl(URLEntity urlEntity, String primaryUrl) {
        String sha256 = DigestUtils.sha256Hex(primaryUrl);
        String convertedURL = sha256.substring(0, 4) + sha256.substring(60, 64);
        URLEntity entity = urlRepository.findByConvertedUrlOrPrimaryUrl(convertedURL, primaryUrl);
        if (entity != null) {
            return entity;
        }
        urlEntity.setPrimaryUrl(primaryUrl);
        urlEntity.setConvertedUrl(convertedURL);
        urlRepository.save(urlEntity);
        return urlEntity;
    }

    public URLEntity findByConvertedUrl(String path) {
        return urlRepository.findByConvertedUrl(path);
    }
}
