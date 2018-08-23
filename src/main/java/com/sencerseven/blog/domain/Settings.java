package com.sencerseven.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean status;

    private String siteImageUrl;

    public Settings() {
    }

    public Settings(String title, boolean status) {
        this.title = title;
        this.status = status;
    }
}
