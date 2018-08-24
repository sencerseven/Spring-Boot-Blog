package com.sencerseven.blog.service;


import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PostService {

    Post findByID(Long id);

    List<Post> findAll(Pageable pageable);

    Page<PostCommand> findPostsBy(int page, int size, String column, Sort.Direction direction);

    Page<PostCommand> findPostSpecification(int page, int size, String column, Sort.Direction direction);

    Post getPostByUrlAndActive(String url, boolean status);

    Post updateBy(Post post);

    List<PostCommand> getPopulerPost(int page, int size, String column, Sort.Direction direction);

    Page<PostCommand> findPostsByCategory(int page, int size, String column, Sort.Direction direction, Category category);

    Page<PostCommand> findPostByTitleContaining(int page, int size, String column, Sort.Direction direction, String containing);

    Page<PostCommand> findPostByTagsContaining(int page, int size, String column, Sort.Direction direction, String containing);

    PostCommand savePostsCommand(PostCommand postCommand, UsersCommand usersCommand);

    List<PostCommand> findPostRand(int page, int size, String column, Sort.Direction direction, Post tempPost);

    Long countByActive(boolean status);

    Page<PostCommand> findSpecificationPost(int page, int size, String column, Sort.Direction direction, PostCommand postCommand);
}
