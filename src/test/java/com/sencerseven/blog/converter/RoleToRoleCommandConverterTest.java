package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.RoleCommand;
import com.sencerseven.blog.domain.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleToRoleCommandConverterTest {

    private static final Long ID = 1L;

    RoleToRoleCommandConverter roleToRoleCommandConverter;

    @Before
    public void setUp() throws Exception {
        roleToRoleCommandConverter = new RoleToRoleCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(roleToRoleCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(roleToRoleCommandConverter.convert(new Role()));
    }

    @Test
    public void convert() {
        Role role = new Role();
        role.setId(ID);

        RoleCommand roleCommand = roleToRoleCommandConverter.convert(role);

        assertEquals(ID,roleCommand.getId());

    }
}