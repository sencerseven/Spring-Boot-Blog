package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ParameterServiceImpl implements ParameterService {

    ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> findParameterByTipAndKey(String tip,String key) {
        return parameterRepository.findParameterByTipAndKey(tip,key).stream().collect(Collectors.toList());
    }

    @Override
    public Map<String,String> findParameterByTip(String tip) {
        return parameterRepository.findParameterByTip(tip).stream().collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
    }
}
