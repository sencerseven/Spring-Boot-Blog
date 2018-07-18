package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Parameter;

import java.util.List;

public interface ParameterService {

    List<Parameter> findParameterByKey(String keyValue);
}
