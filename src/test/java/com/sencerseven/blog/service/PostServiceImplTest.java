package com.sencerseven.blog.service;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.converter.PostCommandToPostConverter;
import com.sencerseven.blog.converter.PostToPostCommandConverter;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.PostRepository;
import com.sencerseven.blog.service.specification.PostCommandSpecification;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    PostServiceImpl postService;

    @Mock
    PostRepository postRepository;

    @Mock
    PostCommandToPostConverter postCommandToPostConverter;

    @Mock
    PostToPostCommandConverter postToPostCommandConverter;

    @Mock
    PostCommandSpecification postCommandSpecification;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        postService = new PostServiceImpl(postRepository,postCommandToPostConverter,postToPostCommandConverter,postCommandSpecification);
    }

    @Test
    public void findPostsBy() {

       // assertNull(postService.findPostsBy(1,2  ,"id",Sort.Direction.DESC));

        Page<Post> postPage = Page.empty();

        when(postRepository.findPostsBy(PageRequest.of(1, 2,Sort.Direction.DESC,"id"))).thenReturn(postPage);

        Page<PostCommand> postResult = postService.findPostsBy(1, 2,"id", Sort.Direction.DESC);

        assertNotNull(postResult);
        verify(postRepository, times(1)).findPostsBy(any());

    }


    @Test
    public void getPostByUrl() {

        Optional<Post> postOptional = Optional.of(new Post());

        when(postRepository.findPostByUrl(anyString())).thenReturn(postOptional);

        Post post = postService.getPostByUrl(anyString());

        assertNotNull(post);
        verify(postRepository, times(1)).findPostByUrl(anyString());
    }
}