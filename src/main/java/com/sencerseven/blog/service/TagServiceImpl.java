package com.sencerseven.blog.service;

import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.converter.TagToTagCommandConverter;
import com.sencerseven.blog.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    TagRepository tagRepository;

    TagToTagCommandConverter tagToTagCommandConverter;


    public TagServiceImpl(TagRepository tagRepository, TagToTagCommandConverter tagToTagCommandConverter) {
        this.tagRepository = tagRepository;
        this.tagToTagCommandConverter = tagToTagCommandConverter;
    }

    @Override
    public List<TagCommand> findAll() {
        return tagRepository.findAll().stream().map(tagToTagCommandConverter::convert).collect(Collectors.toList());
    }
}
