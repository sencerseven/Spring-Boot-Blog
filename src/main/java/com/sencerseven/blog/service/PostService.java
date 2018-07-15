package com.sencerseven.blog.service;



import com.sencerseven.blog.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    public Page<Post> findPostsBy(Pageable pageable);

    Post getPostByUrl(String url);
}
