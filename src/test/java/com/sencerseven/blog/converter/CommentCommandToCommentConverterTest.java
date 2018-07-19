package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CommentCommand;
import com.sencerseven.blog.domain.Comment;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static reactor.core.publisher.Mono.when;

public class CommentCommandToCommentConverterTest {

    private static final Long ID = 1L;
    CommentCommandToCommentConverter commentCommandToCommentConverter;

    @Mock
    CommentUserCommandToCommentUserConverter commentUserCommandToCommentUserConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        commentCommandToCommentConverter = new CommentCommandToCommentConverter(commentUserCommandToCommentUserConverter);
    }

    @Test
    public void nullTest(){
        assertNull(commentCommandToCommentConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(commentCommandToCommentConverter.convert(new CommentCommand()));
    }


    @Test
    public void convert() {

        CommentCommand commentCommand = new CommentCommand();
        commentCommand.setId(ID);

        Comment comment = commentCommandToCommentConverter.convert(commentCommand);

        assertEquals(ID,comment.getId());


    }
}