package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommentService {

    public CommentCommand saveCommentCommand(CommentCommand commentCommand, Post post);

    public CommentCommand saveCommentCommand(CommentCommand commentCommand, String type);

    List<Comment> findCommentsByPostAndActive(Post post, boolean status);

    List<CommentCommand> findCommentsByType(int page, int size, Sort.Direction direction,String column, String type);

    CommentCommand findById(Long id);

    Comment getById(Long id);

    Long countAllByReadAndType(boolean read, String type);

    List<CommentCommand> findAll(CommentCommand commentCommand,String type);

    Comment saveOrUpdate(Comment comment);

    Long countByActiveAndType(boolean status,String type);
}
