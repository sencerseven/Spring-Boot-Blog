package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.functions.BlogHelpers;
import com.sencerseven.blog.repository.PostRepository;
import com.sencerseven.blog.service.PostService;
import com.sencerseven.blog.service.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostCommandToPostConverter implements Converter<PostCommand,Post> {

    @Autowired
    BlogHelpers blogHelpers;

    S3Services s3Services;

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    @Autowired
    PostRepository postRepository;

    public PostCommandToPostConverter(S3Services s3Services, UsersCommandToUsersConverter usersCommandToUsersConverter, CategoryCommandToCategoryConverter categoryCommandToCategoryConverter) {
        this.s3Services = s3Services;
        this.usersCommandToUsersConverter = usersCommandToUsersConverter;
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
    }

    @Override
    public Post convert(PostCommand postCommand) {
        if(postCommand == null)
            return null;

        Post post = new Post();
        post.setId(postCommand.getId());
        post.setTitle(postCommand.getTitle());
        post.setDescription(postCommand.getDescription());
        post.setText(postCommand.getText());
        post.setUrl(blogHelpers.toSlug(postCommand.getTitle()));
        post.setTags(postCommand.getTags());

        if(postCommand.getId() != null){
            Optional<Post> tempPost = postRepository.findById(postCommand.getId());

            if(tempPost.isPresent()){
                if(postCommand.getMultipartFile() == null){
                    post.setImageUrl(tempPost.get().getImageUrl());
                }
                if(postCommand.getCreatedAt() == null){
                    post.setCreatedAt(tempPost.get().getCreatedAt());
                }
            }
        }

        if(postCommand.getCategory() != null)
            post.setCategory(categoryCommandToCategoryConverter.convert(postCommand.getCategory()));

        if(postCommand.getUsers() != null && postCommand.getUsers().getId() != null)
            post.setUsers(usersCommandToUsersConverter.convert(postCommand.getUsers()));

        if(postCommand.getMultipartFile() != null && postCommand.getMultipartFile().getSize() > 0){

            post.setImageUrl(s3Services.uploadFile(postCommand.getMultipartFile().getOriginalFilename(),
                    "posts",postCommand.getMultipartFile()));
        }
        return post;

    }
}
