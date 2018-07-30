package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.repository.PostRepository;
import com.sencerseven.blog.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostCommandToPostConverterTest {

    @InjectMocks
    PostCommandToPostConverter postCommandToPostConverter;

    private static final Long ID = 1L;

    @Mock
    S3Services s3Services;

    @Mock
    UsersCommandToUsersConverter usersCommandToUsersConverter;

    @Mock
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    @Mock
    PostRepository postRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nullTest(){
        assertNull(postCommandToPostConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(postCommandToPostConverter.convert(new PostCommand()));
    }

    @Test
    public void convert() {
        PostCommand postCommand = new PostCommand();
        postCommand.setId(ID);

        Optional<Post> postOptional = Optional.of(new Post());

        when(postRepository.findById(postCommand.getId())).thenReturn(postOptional);

        Post post = postCommandToPostConverter.convert(postCommand);

        assertEquals(ID,post.getId());
    }
}