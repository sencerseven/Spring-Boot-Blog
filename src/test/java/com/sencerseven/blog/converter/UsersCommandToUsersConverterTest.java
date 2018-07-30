package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.domain.Users;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UsersCommandToUsersConverterTest {

    UsersCommandToUsersConverter usersCommandToUsersConverter;

    private static final Long ID = 1L;

    @Mock
    RoleCommandToRoleConverter roleCommandToRoleConverter;

    @Mock
    UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        usersCommandToUsersConverter = new UsersCommandToUsersConverter(roleCommandToRoleConverter,usersDetailCommandToUsersDetailConverter);
    }

    @Test
    public void nullTest(){
        assertNull(usersCommandToUsersConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(usersCommandToUsersConverter.convert(new UsersCommand()));
    }

    @Test
    public void convert() {
        UsersCommand usersCommand = new UsersCommand();
        usersCommand.setId(ID);

        Users users = usersCommandToUsersConverter.convert(usersCommand);

        assertEquals(ID,users.getId());
    }
}