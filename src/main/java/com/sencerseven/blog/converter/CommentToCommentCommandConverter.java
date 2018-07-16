package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentCommandConverter implements Converter<Comment,CommentCommand> {

    CommentUserToCommentUserCommandConverter commentUserToCommentUserCommandConverter;

    public CommentToCommentCommandConverter(CommentUserToCommentUserCommandConverter commentUserToCommentUserCommandConverter) {
        this.commentUserToCommentUserCommandConverter = commentUserToCommentUserCommandConverter;
    }

    @Override
    public CommentCommand convert(Comment comment) {
        if(comment == null)
            return null;

        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(comment.getId());
        commentCommand.setText(comment.getText());

        if(comment.getCommentUser() != null)
            commentCommand.setCommentUserCommand(commentUserToCommentUserCommandConverter.convert(comment.getCommentUser()));

        return commentCommand;
    }
}
