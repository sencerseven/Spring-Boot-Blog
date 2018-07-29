package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostCommandConverter implements Converter<Post, PostCommand> {

    UsersToUsersCommandConverter usersToUsersCommandConverter;
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;
    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Autowired
    S3Services s3Services;

    public PostToPostCommandConverter(UsersToUsersCommandConverter usersToUsersCommandConverter, CategoryToCategoryCommandConverter categoryToCategoryCommandConverter, CommentToCommentCommandConverter commentToCommentCommandConverter) {
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
        this.commentToCommentCommandConverter = commentToCommentCommandConverter;
    }

    @Override
    public PostCommand convert(Post post) {
        if (post == null)
            return null;

        PostCommand postCommand = new PostCommand();
        postCommand.setId(post.getId());
        postCommand.setText(post.getText());
        postCommand.setTitle(post.getTitle());
        postCommand.setDescription(post.getDescription());
        postCommand.setCreatedAt(post.getCreatedAt());
        postCommand.setUrl(post.getUrl());

        postCommand.setImageUrl(s3Services.getSignUrl(post.getImageUrl(),60));


        if (post.getUsers() != null)
            postCommand.setUsers(usersToUsersCommandConverter.convert(post.getUsers()));

        if (post.getCategory() != null)
            postCommand.setCategory(categoryToCategoryCommandConverter.convert(post.getCategory()));

        if(post.getComment() != null && post.getComment().size() > 0)
            post.getComment().forEach(comment -> postCommand.getComment().add(commentToCommentCommandConverter.convert(comment)));

        return postCommand;

    }
}
