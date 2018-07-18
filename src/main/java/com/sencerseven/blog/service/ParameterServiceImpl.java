package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Parameter;
import com.sencerseven.blog.repository.ParameterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParameterServiceImpl implements ParameterService {

    ParameterRepository parameterRepository;

    public ParameterServiceImpl(ParameterRepository parameterRepository) {
        this.parameterRepository = parameterRepository;
    }

    @Override
    public List<Parameter> findParameterByKey(String keyValue) {
        return parameterRepository.findParameterByKey(keyValue).stream().collect(Collectors.toList());
    }
}
