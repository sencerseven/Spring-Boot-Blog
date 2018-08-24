package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.converter.PostCommandToPostConverter;
import com.sencerseven.blog.converter.PostToPostCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.exception.NotFoundCategoryException;
import com.sencerseven.blog.exception.NotFoundPostInCategoryException;
import com.sencerseven.blog.exception.NotFoundSearchException;
import com.sencerseven.blog.repository.PostRepository;
import com.sencerseven.blog.service.specification.PostCommandSpecification;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    PostRepository postRepository;

    PostCommandToPostConverter postCommandToPostConverter;
    PostToPostCommandConverter postToPostCommandConverter;
    PostCommandSpecification postCommandSpecification;

    public PostServiceImpl(PostRepository postRepository, PostCommandToPostConverter postCommandToPostConverter, PostToPostCommandConverter postToPostCommandConverter, PostCommandSpecification postCommandSpecification) {
        this.postRepository = postRepository;
        this.postCommandToPostConverter = postCommandToPostConverter;
        this.postToPostCommandConverter = postToPostCommandConverter;
        this.postCommandSpecification = postCommandSpecification;
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
    public Page<PostCommand> findPostSpecification(int page, int size, String column, Sort.Direction direction) {
        Page<Post> postPage = postRepository.findAll(postCommandSpecification.getFilter(new PostCommand()),PageRequest.of(page,size,direction,column));

        List<PostCommand> postCommandList = postPage.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommandList,PageRequest.of(page,size),postCommandList.size());
    }

    @Override
    public Post getPostByUrlAndActive(String url,boolean status) {
        Optional<Post> postOptional = postRepository.findPostByUrlAndActive(url,status);

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

        Page<Post> postCommands = postRepository.findAll(postCommandSpecification.getFilter(new PostCommand()),PageRequest.of(page,size,direction,column));

        return postCommands.getContent().stream().map(postToPostCommandConverter::convert).collect(Collectors.toList());
    }

    @Override
    public Page<PostCommand> findPostsByCategory(int page, int size, String column, Sort.Direction direction,Category category) {
        Page<Post> posts = postRepository.findPostsByCategory(PageRequest.of(page,size,direction,column),category);
            if(posts.getContent().size() == 0)
                throw new NotFoundPostInCategoryException(category.getName());

            List<PostCommand> postCommands = posts.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommands,PageRequest.of(page,size),posts.getTotalElements());
    }

    @Override
    public Page<PostCommand> findPostByTitleContaining(int page, int size, String column, Sort.Direction direction, String containing) {


        Page<Post> posts = postRepository.findPostByTitleContaining(PageRequest.of(page,size,direction,column),containing);

        if(posts.getContent().size() == 0)
            throw new NotFoundSearchException(containing);

        List<PostCommand> postCommandList = posts.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommandList,PageRequest.of(page,size),posts.getTotalElements());

    }

    @Override
    public Page<PostCommand> findPostByTagsContaining(int page, int size, String column, Sort.Direction direction, String containing) {
        Page<Post> posts = postRepository.findPostsByTagsContains(PageRequest.of(page,size,direction,column),containing);

        if(posts.getContent().size() == 0)
            throw new NotFoundSearchException(containing);

        List<PostCommand> postCommandList = posts.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommandList,PageRequest.of(page,size),posts.getTotalElements());
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

    @Override
    public List<PostCommand> findPostRand(int page, int size, String column, Sort.Direction direction,Post tempPost) {
        double count = (double)postRepository.count();
        int id= (int)Math.ceil(count / size);

        int random = new Random().nextInt(id);
        return postRepository.findPostsByCategoryAndIdNot(PageRequest.of(random,size,direction,column),tempPost.getCategory(),tempPost.getId()).getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

    }

    @Override
    public Long countByActive(boolean status) {
        return postRepository.countByActive(status);
    }

    @Override
    public Page<PostCommand> findSpecificationPost(int page, int size, String column, Sort.Direction direction, PostCommand postCommand) {
        Page<Post> posts = postRepository.findAll(postCommandSpecification.getFilter(postCommand),PageRequest.of(page,size,direction,column));

        if(posts.getContent().size() == 0){

            if(postCommand.getTitle() != null){
                throw new NotFoundSearchException(postCommand.getTitle());
            }else{
                throw new NotFoundCategoryException();
            }
        }




        List<PostCommand> postCommandList = posts.getContent().stream().map(post -> postToPostCommandConverter.convert(post)).collect(Collectors.toList());

        return new PageImpl<>(postCommandList,PageRequest.of(page,size),posts.getTotalElements());
    }


}
