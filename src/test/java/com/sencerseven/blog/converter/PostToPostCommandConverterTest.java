package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.PostCommand;
import com.sencerseven.blog.domain.Post;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class PostToPostCommandConverterTest {

    PostToPostCommandConverter postToPostCommandConverter;

    private static final Long ID = 1L;

    @Mock
    UsersToUsersCommandConverter usersToUsersCommandConverter;

    @Mock
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    @Mock
    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Before
    public void setUp() throws Exception {
        postToPostCommandConverter = new PostToPostCommandConverter(usersToUsersCommandConverter, categoryToCategoryCommandConverter, commentToCommentCommandConverter);
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