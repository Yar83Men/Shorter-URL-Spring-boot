package com.shorturl.model;


import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "urls")
public class URLEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "primary_url")
    @NotEmpty(message = "Not empty field, enter URL")
    private String primaryUrl;

    @Column(name = "converted_url")
    private String convertedUrl;

    public URLEntity() {
    }

    public URLEntity(String primaryUrl) {
        this.primaryUrl = primaryUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrimaryUrl() {
        return primaryUrl;
    }

    public void setPrimaryUrl(String primaryUrl) {
        this.primaryUrl = primaryUrl;
    }

    public String getConvertedUrl() {
        return convertedUrl;
    }

    public void setConvertedUrl(String convertedUrl) {
        this.convertedUrl = convertedUrl;
    }

    @Override
    public String toString() {
        return "URLEntity{" +
                "id=" + id +
                ", primaryUrl='" + primaryUrl + '\'' +
                ", convertedUrl='" + convertedUrl + '\'' +
                '}';
    }
}
