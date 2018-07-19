package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentUserCommand;
import com.sencerseven.blog.domain.CommentUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.xml.stream.events.Comment;

import static org.junit.Assert.*;

public class CommentUserCommandToCommentUserConverterTest {

    private static final Long ID =1L;

    CommentUserCommandToCommentUserConverter commentUserCommandToCommentUserConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentUserCommandToCommentUserConverter = new CommentUserCommandToCommentUserConverter();
    }

    @Test
    public void nullTest(){
        assertNull(commentUserCommandToCommentUserConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(commentUserCommandToCommentUserConverter.convert(new CommentUserCommand()));
    }

    @Test
    public void convert() {
        CommentUserCommand commentUserCommand = new CommentUserCommand();
        commentUserCommand.setId(ID);

        CommentUser commentUser = commentUserCommandToCommentUserConverter.convert(commentUserCommand);

        assertEquals(ID,commentUser.getId());

    }
}