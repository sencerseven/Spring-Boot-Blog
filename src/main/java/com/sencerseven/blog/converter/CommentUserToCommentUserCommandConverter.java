package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.CommentUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentUserToCommentUserCommandConverter implements Converter<CommentUser,CommentUserCommand> {

    @Override
    public CommentUserCommand convert(CommentUser commentUser) {
        if(commentUser == null)
            return null;

        CommentUserCommand commentUserCommand = new CommentUserCommand();

        commentUserCommand.setId(commentUser.getId());
        commentUserCommand.setEmail(commentUser.getEmail());
        commentUserCommand.setName(commentUser.getName());
        commentUserCommand.setWebsite(commentUser.getWebsite());

        return commentUserCommand;
    }
}
