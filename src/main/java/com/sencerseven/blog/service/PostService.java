package com.sencerseven.blog.service;


import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import javafx.geometry.Pos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post findByID(Long id);

    List<Post> findAll(Pageable pageable);

    Page<Post> findPostsBy(int page, int size, String column, Sort.Direction direction);

    Post getPostByUrl(String url);

    Post updateBy(Post post);

    List<Post> getPopulerPost(int page, int size, String column, Sort.Direction direction);

    Page<Post> findPostsByCategory(int page, int size, String column, Sort.Direction direction, Category category);

    Page<Post> findPostByTitleContaining(int page, int size, String column, Sort.Direction direction, String containing);

    PostCommand savePostsCommand(PostCommand postCommand, UsersCommand usersCommand);
}
