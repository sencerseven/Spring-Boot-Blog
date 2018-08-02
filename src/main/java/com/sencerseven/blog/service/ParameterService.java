package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ParameterService {

    List<Parameter> findParameterByTipAndKey(String tip,String key);

    Map<String,String> findParameterByTip(String tip);
}
