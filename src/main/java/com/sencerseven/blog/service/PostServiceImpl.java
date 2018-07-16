package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
    public Page<Post> findPostsBy(Pageable pageable) {
        if (pageable == null)
            return null;

        Page<Post> postPage = postRepository.findPostsBy(pageable);

        return postPage;
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
    public List<Post> getPopulerPost(int page, int size, String column, Sort.Direction direction) {

        return findAll(PageRequest.of(page,size,direction,column));
    }


}
