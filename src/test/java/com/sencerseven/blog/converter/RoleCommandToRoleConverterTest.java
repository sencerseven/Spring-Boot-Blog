package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.RoleCommand;
import com.sencerseven.blog.domain.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleCommandToRoleConverterTest {

    private static final Long ID = 1L;

    RoleCommandToRoleConverter roleCommandToRoleConverter;

    @Before
    public void setUp() throws Exception {
        roleCommandToRoleConverter = new RoleCommandToRoleConverter();
    }

    @Test
    public void nullTest(){
        assertNull(roleCommandToRoleConverter.convert(null));
    }

    public void emptyTest(){
        assertNotNull(roleCommandToRoleConverter.convert(new RoleCommand()));
    }

    @Test
    public void convert() {
        RoleCommand roleCommand = new RoleCommand();
        roleCommand.setId(ID);

        Role role =roleCommandToRoleConverter.convert(roleCommand);

        assertEquals(ID,role.getId());

    }
}