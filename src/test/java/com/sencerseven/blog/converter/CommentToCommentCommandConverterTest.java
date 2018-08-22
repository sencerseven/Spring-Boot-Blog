package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CommentToCommentCommandConverterTest {

    private static final Long ID =1L;

    CommentToCommentCommandConverter commentToCommentCommandConverter;

    @Mock
    UsersDetailToUsersDetailCommandConverter usersDetailToUsersDetailCommandConverter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentToCommentCommandConverter = new CommentToCommentCommandConverter(usersDetailToUsersDetailCommandConverter);
    }

    @Test
    public void nullTest(){
        assertNull(commentToCommentCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(commentToCommentCommandConverter.convert(new Comment()));
    }

    @Test
    public void convert() {
        Comment comment = new Comment();
        comment.setId(ID);

        CommentCommand commentCommand = commentToCommentCommandConverter.convert(comment);

        assertEquals(ID,commentCommand.getId());

    }
}