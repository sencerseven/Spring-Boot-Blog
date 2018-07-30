package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.domain.Users;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class UsersToUsersCommandConverterTest {

    UsersToUsersCommandConverter usersToUsersCommandConverter;

    private static final Long ID = 1L;
    @Mock
    UsersDetailToUsersDetailCommandConverter usersDetailToUsersDetailCommandConverter;

    @Mock
    RoleToRoleCommandConverter roleToRoleCommandConverter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        usersToUsersCommandConverter = new UsersToUsersCommandConverter(usersDetailToUsersDetailCommandConverter,roleToRoleCommandConverter);

    }

    @Test
    public void nullTest(){
        assertNull(usersToUsersCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(usersToUsersCommandConverter.convert(new Users()));
    }

    @Test
    public void convert() {
        Users users = new Users();
        users.setId(ID);

        UsersCommand usersCommand = usersToUsersCommandConverter.convert(users);

        assertEquals(ID,usersCommand.getId());
    }
}