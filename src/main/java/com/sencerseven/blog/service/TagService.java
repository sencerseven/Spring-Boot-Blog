package com.sencerseven.blog.service;

import com.sencerseven.blog.command.TagCommand;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagService {

    List<TagCommand> findAll();
}
