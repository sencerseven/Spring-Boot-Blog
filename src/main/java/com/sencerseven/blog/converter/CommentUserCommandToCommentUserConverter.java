package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.CommentUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentUserCommandToCommentUserConverter implements Converter<CommentUserCommand,CommentUser> {

    @Override
    public CommentUser convert(CommentUserCommand commentUserCommand) {
       if(commentUserCommand == null)
           return null;

       CommentUser commentUser = new CommentUser();

       commentUser.setId(commentUserCommand.getId());
       commentUser.setEmail(commentUserCommand.getEmail());
       commentUser.setName(commentUserCommand.getName());
       commentUser.setWebsite(commentUserCommand.getWebsite());

       return  commentUser;
    }
}
