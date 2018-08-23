package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentCommandConverter implements Converter<Comment,CommentCommand> {

    UsersDetailToUsersDetailCommandConverter usersDetailToUsersDetailCommandConverter;

    PostToPostCommandConverter postToPostCommandConverter;

    public CommentToCommentCommandConverter(UsersDetailToUsersDetailCommandConverter usersDetailToUsersDetailCommandConverter) {
        this.usersDetailToUsersDetailCommandConverter = usersDetailToUsersDetailCommandConverter;
    }

    public PostToPostCommandConverter getPostToPostCommandConverter() {
        return postToPostCommandConverter;
    }
    @Autowired
    public void setPostToPostCommandConverter(PostToPostCommandConverter postToPostCommandConverter) {
        this.postToPostCommandConverter = postToPostCommandConverter;
    }

    @Override
    public CommentCommand convert(Comment comment) {
        if(comment == null)
            return null;

        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(comment.getId());
        commentCommand.setText(comment.getText());
        commentCommand.setType(comment.getType());
        commentCommand.setRead(comment.isRead());
        commentCommand.setActive(comment.isActive());
        commentCommand.setCreatedAt(comment.getCreatedAt());
        commentCommand.setTitle(comment.getTitle());
        if(comment.getUsersDetail() != null)
            commentCommand.setUsersDetailCommand(usersDetailToUsersDetailCommandConverter.convert(comment.getUsersDetail()));

        return commentCommand;
    }
}
