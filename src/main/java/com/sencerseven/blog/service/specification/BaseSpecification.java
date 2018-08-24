package com.sencerseven.blog.service.specification;

import org.springframework.data.jpa.domain.Specification;

public abstract class BaseSpecification<T,U> {

    private final String wildcard = "%";

    public abstract Specification<T> getFilter(U request);

    protected String contains(String searchField){
        return wildcard + searchField + wildcard;
    }
    protected String containsLeft(String searchField){
        return wildcard + searchField;
    }
    protected String containsRight(String searchField){
        return searchField + wildcard;
    }

}
