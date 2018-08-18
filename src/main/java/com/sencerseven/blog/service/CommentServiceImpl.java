package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.converter.CommentCommandToCommentConverter;
import com.sencerseven.blog.converter.CommentToCommentCommandConverter;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.CommentRepository;
import com.sencerseven.blog.service.specification.CommentCommandSpecification;
import com.sun.xml.internal.rngom.ast.builder.CommentList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    CommentCommandToCommentConverter commentCommandToCommentConverter;
    CommentToCommentCommandConverter commentToCommentCommandConverter;
    CommentCommandSpecification commentCommandSpecification;

    public CommentServiceImpl(CommentRepository commentRepository, CommentCommandToCommentConverter commentCommandToCommentConverter, CommentToCommentCommandConverter commentToCommentCommandConverter, CommentCommandSpecification commentCommandSpecification) {
        this.commentRepository = commentRepository;
        this.commentCommandToCommentConverter = commentCommandToCommentConverter;
        this.commentToCommentCommandConverter = commentToCommentCommandConverter;
        this.commentCommandSpecification = commentCommandSpecification;
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
    public CommentCommand saveCommentCommand(CommentCommand commentCommand, String type) {
        commentCommand.setType(type);
        Comment commentDetach = commentCommandToCommentConverter.convert(commentCommand);

        Comment comment = commentRepository.save(commentDetach);

        return commentToCommentCommandConverter.convert(comment);
    }

    @Override
    public List<Comment> findCommentsByPostAndActive(Post post, int status) {
        return commentRepository.findCommentsByPostAndActive(post, status).stream().collect(Collectors.toList());
    }

    @Override
    public List<CommentCommand> findCommentsByType(int page, int size, Sort.Direction direction, String column, String type) {
        return commentRepository.findCommentsByType(PageRequest.of(page, size, direction, column), type).stream()
                .map(commentToCommentCommandConverter::convert).collect(Collectors.toList());
    }

    @Override
    public CommentCommand findById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent())
            return null;

        return commentOptional.map(commentToCommentCommandConverter::convert).get();

    }

    @Override
    public Long countAllByReadAndType(boolean read, String type) {
        return commentRepository.countAllByReadAndType(read,type);
    }

    @Override
    public List<CommentCommand> findAll(CommentCommand commentCommand) {
       Page<Comment> comment = commentRepository.findAll(commentCommandSpecification.getFilter(commentCommand),PageRequest.of(0,10,Sort.Direction.DESC,"id"));

       return comment.getContent().stream().map(commentToCommentCommandConverter::convert).collect(Collectors.toList());
    }
}
