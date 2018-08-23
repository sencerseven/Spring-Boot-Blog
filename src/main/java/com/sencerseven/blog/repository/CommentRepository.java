package com.sencerseven.blog.repository;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> ,JpaSpecificationExecutor<Comment> {

    Set<Comment> findCommentsByPostAndActive(Post post, boolean status);

    List<Comment> findCommentsByType(Pageable pageable,String type);

    Long countAllByReadAndType(boolean read,String type);

    Long countByActiveAndType(boolean status,String type);
}
