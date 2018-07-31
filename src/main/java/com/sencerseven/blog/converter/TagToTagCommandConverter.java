package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.TagCommand;
import com.sencerseven.blog.domain.Tag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TagToTagCommandConverter implements Converter<Tag,TagCommand> {


    @Override
    public TagCommand convert(Tag tag) {
       if(tag == null)
           return null;

       TagCommand tagCommand = new TagCommand();
       tagCommand.setId(tag.getId());
       tagCommand.setTagName(tag.getTagName());


       return tagCommand;
    }
}
