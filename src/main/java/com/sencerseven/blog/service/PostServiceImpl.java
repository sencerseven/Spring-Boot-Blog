package com.sencerseven.blog.service;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.converter.PostCommandToPostConverter;
import com.sencerseven.blog.converter.PostToPostCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.exception.NotFoundPostInCategoryException;
import com.sencerseven.blog.exception.NotFoundSearchException;
import com.sencerseven.blog.repository.PostRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    PostCommandToPostConverter postCommandToPostConverter;
    PostToPostCommandConverter postToPostCommandConverter;

    public PostServiceImpl(PostRepository postRepository, PostCommandToPostConverter postCommandToPostConverter, PostToPostCommandConverter postToPostCommandConverter) {
        this.postRepository = postRepository;
        this.postCommandToPostConverter = postCommandToPostConverter;
        this.postToPostCommandConverter = postToPostCommandConverter;
    }

    @Override
    public Post findByID(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);

        if(!postOptional.isPresent())
            throw new RuntimeException();

        return postOptional.get();
    }

    @Override
    public List<Post> findAll(Pageable pageable) {
        if(pageable == null)
            return null;

        return postRepository.findAll();
    }

    @Override
    public Page<PostCommand> findPostsBy(int page, int size, String column, Sort.Direction direction) {
        Page<Post> postPage = postRepository.findPostsBy(PageRequest.of(page,size,direction,column));

        List<PostCommand> postCommandList = postPage.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommandList,PageRequest.of(page,size),postPage.getTotalElements());
    }

    @Override
    public Post getPostByUrl(String url) {
        Optional<Post> postOptional = postRepository.findPostByUrl(url);

        if(!postOptional.isPresent())
            throw new RuntimeException();

        return postOptional.get();
    }

    @Override
    public Post updateBy(Post post) {

        Optional<Post> postOptional = Optional.of(post);

        if(!postOptional.isPresent())
            return null;

        post.setView(post.getView());

        return postRepository.save(post);


    }

    @Override
    public List<PostCommand> getPopulerPost(int page, int size, String column, Sort.Direction direction) {

        return findAll(PageRequest.of(page,size,direction,column)).stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());
    }

    @Override
    public Page<Post> findPostsByCategory(int page, int size, String column, Sort.Direction direction,Category category) {
        Page<Post> posts = postRepository.findPostsByCategory(PageRequest.of(page,size,direction,column),category);
            if(posts.getContent().size() == 0)
                throw new NotFoundPostInCategoryException(category.getName());
        return posts;
    }

    @Override
    public Page<Post> findPostByTitleContaining(int page, int size, String column, Sort.Direction direction, String containing) {


        Page<Post> posts = postRepository.findPostByTitleContaining(PageRequest.of(page,size,direction,column),containing);

        if(posts.getContent().size() == 0)
            throw new NotFoundSearchException(containing);

        return posts;

    }

    @Override
    public PostCommand savePostsCommand(PostCommand postCommand, UsersCommand usersCommand) {
        if(postCommand == null ||usersCommand == null)
            return null;

        postCommand.setUsers(usersCommand);

        Optional<Post> postOptional = Optional.of(postCommandToPostConverter.convert(postCommand));

        if(postOptional.isPresent()) {
            Optional<Post> save =  Optional.of(postRepository.save(postOptional.get()));

            if (save.isPresent())
                return postToPostCommandConverter.convert(save.get());
        }
        return null;





    }


}
