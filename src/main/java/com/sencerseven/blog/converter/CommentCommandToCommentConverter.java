package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentCommandToCommentConverter implements Converter<CommentCommand,Comment> {

    UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter;

    public CommentCommandToCommentConverter(UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter) {
        this.usersDetailCommandToUsersDetailConverter = usersDetailCommandToUsersDetailConverter;
    }

    @Override
    public Comment convert(CommentCommand commentCommand) {
        if(commentCommand == null)
            return null;

        Comment comment = new Comment();

        comment.setId(commentCommand.getId());
        comment.setText(commentCommand.getText());
        comment.setType(commentCommand.getType());
        comment.setRead(commentCommand.isRead());
        comment.setCreatedAt(commentCommand.getCreatedAt());
        comment.setActive(commentCommand.getActive());
        comment.setTitle(commentCommand.getTitle());
        if(commentCommand.getUsersDetailCommand() != null)
            comment.addUsersDetail(usersDetailCommandToUsersDetailConverter.convert(commentCommand.getUsersDetailCommand()));

        return comment;
    }
}
