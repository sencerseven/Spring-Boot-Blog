package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;

import java.util.List;

public interface CommentService {

    public CommentCommand saveCommentCommand(CommentCommand commentCommand, Post post);
    public CommentCommand saveCommentCommand(CommentCommand commentCommand,String type);

    List<Comment> findCommentsByPostAndActive(Post post, int status);
}
