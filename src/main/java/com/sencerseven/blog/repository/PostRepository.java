package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findPostsBy(Pageable pageable);

    Optional<Post> findPostByUrl(String url);


    Page<Post> findPostsByCategory(Pageable pageable, Category category);

    Page<Post> findPostByTitleContaining(Pageable pageable,String containing);


    Page<Post> findPostByTagsContaining(Pageable pageable,String containing);

    Page<Post> findPostsByCategoryAndIdNot(Pageable pageable, Category category,Long id);

}
