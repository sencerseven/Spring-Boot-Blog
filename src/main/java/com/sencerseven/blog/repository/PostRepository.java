package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>,JpaSpecificationExecutor<Post> {

    Page<Post> findPostsBy(Pageable pageable);

    Optional<Post> findPostByUrlAndActive(String url,boolean status);

    Page<Post> findPostsByCategory(Pageable pageable, Category category);

    Page<Post> findPostByTitleContaining(Pageable pageable, String containing);


    @Query("Select p from Post p INNER JOIN p.tags t where t.tagName =:tag")
    Page<Post> findPostsByTagsContains(Pageable pageable, @Param("tag") String containing);

    Page<Post> findPostsByCategoryAndIdNot(Pageable pageable, Category category, Long id);

    Long countByActive(boolean status);


}
