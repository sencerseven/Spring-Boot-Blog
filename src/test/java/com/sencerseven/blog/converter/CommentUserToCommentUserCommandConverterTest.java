package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.CommentUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CommentUserToCommentUserCommandConverterTest {

    private static final Long ID = 1L;

    CommentUserToCommentUserCommandConverter commentUserToCommentUserCommandConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentUserToCommentUserCommandConverter = new CommentUserToCommentUserCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(commentUserToCommentUserCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(commentUserToCommentUserCommandConverter.convert(new CommentUser()));
    }


    @Test
    public void convert() {
    CommentUser commentUser = new CommentUser();
    commentUser.setId(ID);

    CommentUserCommand commentUserCommand = commentUserToCommentUserCommandConverter.convert(commentUser);

    assertEquals(ID,commentUserCommand.getId());

    }
}