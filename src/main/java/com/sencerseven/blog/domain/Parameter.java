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
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tip;

    private String key;

    private String value;

    public Parameter() {
    }

    public Parameter(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
