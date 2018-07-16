package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Set<Comment> findCommentsByPostAndActive(Post post, int status);

}
