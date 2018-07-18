package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ParameterRepository extends JpaRepository<Parameter,Long> {

    Set<Parameter> findParameterByKey(String keyValue);
}
