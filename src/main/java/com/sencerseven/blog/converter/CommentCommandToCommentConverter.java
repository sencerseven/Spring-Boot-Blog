package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentCommandToCommentConverter implements Converter<CommentCommand,Comment> {

    CommentUserCommandToCommentUserConverter commentUserCommandToCommentUserConverter;

    public CommentCommandToCommentConverter(CommentUserCommandToCommentUserConverter commentUserCommandToCommentUserConverter) {
        this.commentUserCommandToCommentUserConverter = commentUserCommandToCommentUserConverter;
    }

    @Override
    public Comment convert(CommentCommand commentCommand) {
        if(commentCommand == null)
            return null;

        Comment comment = new Comment();

        comment.setId(commentCommand.getId());
        comment.setText(commentCommand.getText());
        comment.setType(commentCommand.getType());

        if(commentCommand.getCommentUserCommand() != null)
            comment.addCommentUser(commentUserCommandToCommentUserConverter.convert(commentCommand.getCommentUserCommand()));

        return comment;
    }
}
