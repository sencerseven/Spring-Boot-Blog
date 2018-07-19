package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersDetailCommand;
import com.sencerseven.blog.domain.UsersDetail;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UsersDetailToUsersDetailCommandConverterTest {

    private static final Long ID = 1L;

    UsersDetailToUsersDetailCommandConverter usersDetailToUsersDetailCommandConverter;

    @Before
    public void setUp() throws Exception {
        usersDetailToUsersDetailCommandConverter = new UsersDetailToUsersDetailCommandConverter();
    }

    @Test
    public void nullTest() {
        assertNull(usersDetailToUsersDetailCommandConverter.convert(null));
    }

    @Test
    public void emptyTest() {
        assertNotNull(usersDetailToUsersDetailCommandConverter.convert(new UsersDetail()));
    }

    @Test
    public void convert() {
        UsersDetail usersDetail = new UsersDetail();
        usersDetail.setId(ID);

        UsersDetailCommand usersDetailCommand =  usersDetailToUsersDetailCommandConverter.convert(usersDetail);

        assertEquals(ID,usersDetailCommand.getId());
    }
}