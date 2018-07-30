package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.converter.CommentCommandToCommentConverter;
import com.sencerseven.blog.converter.CommentToCommentCommandConverter;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    CommentCommandToCommentConverter commentCommandToCommentConverter;
    CommentToCommentCommandConverter commentToCommentCommandConverter;

    public CommentServiceImpl(CommentRepository commentRepository, CommentCommandToCommentConverter commentCommandToCommentConverter, CommentToCommentCommandConverter commentToCommentCommandConverter) {
        this.commentRepository = commentRepository;
        this.commentCommandToCommentConverter = commentCommandToCommentConverter;
        this.commentToCommentCommandConverter = commentToCommentCommandConverter;
    }

    @Transactional
    @Override
    public CommentCommand saveCommentCommand(CommentCommand commentCommand, Post post) {
        commentCommand.setType("post");
        Comment commentDetach = commentCommandToCommentConverter.convert(commentCommand);

        commentDetach.setPost(post);

        Comment comment = commentRepository.save(commentDetach);

        return commentToCommentCommandConverter.convert(comment);
    }

    @Override
    public CommentCommand saveCommentCommand(CommentCommand commentCommand,String type) {
        commentCommand.setType(type);
        Comment commentDetach = commentCommandToCommentConverter.convert(commentCommand);

        Comment comment = commentRepository.save(commentDetach);

        return commentToCommentCommandConverter.convert(comment);
    }

    @Override
    public List<Comment> findCommentsByPostAndActive(Post post, int status) {
        return commentRepository.findCommentsByPostAndActive(post,status).stream().collect(Collectors.toList());
    }
}
