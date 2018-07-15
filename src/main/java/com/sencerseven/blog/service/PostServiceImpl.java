package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
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
}
