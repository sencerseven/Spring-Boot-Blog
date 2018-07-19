package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersDetailCommand;
import com.sencerseven.blog.domain.UsersDetail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.Assert.*;

public class UsersDetailCommandToUsersDetailConverterTest {

    private static final Long ID = 1L;

    UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter;

    @Before
    public void setUp() throws Exception {
        usersDetailCommandToUsersDetailConverter = new UsersDetailCommandToUsersDetailConverter();
    }

    @Test
    public void nullTest(){
        assertNull(usersDetailCommandToUsersDetailConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(usersDetailCommandToUsersDetailConverter.convert(new UsersDetailCommand()));
    }

    @Test
    public void convert() {
        UsersDetailCommand usersDetailCommand = new UsersDetailCommand();
        usersDetailCommand.setId(ID);

        UsersDetail usersDetail  = usersDetailCommandToUsersDetailConverter.convert(usersDetailCommand);

        assertEquals(ID,usersDetail.getId());

    }
}