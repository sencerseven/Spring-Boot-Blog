package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersCommand;
import com.sencerseven.blog.domain.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsersCommandToUsersConverter implements Converter<UsersCommand,Users> {

    RoleCommandToRoleConverter roleCommandToRoleConverter;
    UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter;

    public UsersCommandToUsersConverter(RoleCommandToRoleConverter roleCommandToRoleConverter, UsersDetailCommandToUsersDetailConverter usersDetailCommandToUsersDetailConverter) {
        this.roleCommandToRoleConverter = roleCommandToRoleConverter;
        this.usersDetailCommandToUsersDetailConverter = usersDetailCommandToUsersDetailConverter;
    }

    @Override
    public Users convert(UsersCommand usersCommand) {
        if(usersCommand == null)
            return null;

        Users users = new Users();
        users.setId(usersCommand.getId());
        users.setUserName(usersCommand.getUserName());
        users.setPassword(usersCommand.getPassword());
        users.setActive(usersCommand.getActive());

        if(usersCommand.getRoles() != null && usersCommand.getRoles().size() >0 )
            usersCommand.getRoles().forEach(roleCommand -> users.getRole().add(roleCommandToRoleConverter.convert(roleCommand)));


        if(usersCommand.getUsersDetail() != null)
            users.setUsersDetail(usersDetailCommandToUsersDetailConverter.convert(usersCommand.getUsersDetail()));

        return users;
    }
}
