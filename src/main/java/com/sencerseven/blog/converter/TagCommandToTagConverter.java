package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.domain.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagCommandToTagConverter implements Converter<TagCommand,Tag> {

    @Override
    public Tag convert(TagCommand tagCommand) {
        if(tagCommand == null)
            return null;

        Tag tag = new Tag();
        tag.setTagName(tagCommand.getTagName());



        return tag;
    }
}
