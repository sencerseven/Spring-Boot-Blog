package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersDetailCommand;
import com.sencerseven.blog.domain.UsersDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsersDetailCommandToUsersDetailConverter implements Converter<UsersDetailCommand,UsersDetail> {

    @Override
    public UsersDetail convert(UsersDetailCommand usersDetailCommand) {
        if(usersDetailCommand == null)
            return null;

        UsersDetail usersDetail = new UsersDetail();
        usersDetail.setId(usersDetailCommand.getId());
        usersDetail.setDescription(usersDetailCommand.getDescription());
        usersDetail.setEmail(usersDetailCommand.getEmail());
        usersDetail.setFirstName(usersDetailCommand.getFirstName());
        usersDetail.setLastName(usersDetailCommand.getLastName());

        return usersDetail;


    }
}
