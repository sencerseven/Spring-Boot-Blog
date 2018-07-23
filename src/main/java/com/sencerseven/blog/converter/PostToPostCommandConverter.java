package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PostToPostCommandConverter implements Converter<Post,PostCommand> {

    UsersToUsersCommandConverter usersToUsersCommandConverter;
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    public PostToPostCommandConverter(UsersToUsersCommandConverter usersToUsersCommandConverter, CategoryToCategoryCommandConverter categoryToCategoryCommandConverter) {
        this.usersToUsersCommandConverter = usersToUsersCommandConverter;
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
    }

    @Override
    public PostCommand convert(Post post) {
        if(post == null)
            return null;

        PostCommand postCommand = new PostCommand();
        postCommand.setId(post.getId());
        postCommand.setText(post.getText());
        postCommand.setTitle(post.getTitle());
        postCommand.setDescription(post.getDescription());


        if(post.getUsers() != null)
            postCommand.setUsers(usersToUsersCommandConverter.convert(post.getUsers()));

        if(post.getCategory() != null)
            postCommand.setCategory(categoryToCategoryCommandConverter.convert(post.getCategory()));

        return postCommand;

    }
}
