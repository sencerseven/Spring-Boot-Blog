package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import com.sencerseven.blog.service.S3Services;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostToPostCommandConverterTest {

    @InjectMocks
    PostToPostCommandConverter postToPostCommandConverter;

    private static final Long ID = 1L;

    @Mock
    UsersToUsersCommandConverter usersToUsersCommandConverter;

    @Mock
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    @Mock
    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Mock
    TagToTagCommandConverter tagToTagCommandConverter;

    @Mock
    S3Services s3Services;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void nullTest() {
        assertNull(postToPostCommandConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(postToPostCommandConverter.convert(new Post()));
    }

    @Test
    public void convert() {
        Post post = new Post();
        post.setId(ID);

        PostCommand postCommand = postToPostCommandConverter.convert(post);


        assertEquals(ID, postCommand.getId());

    }
}