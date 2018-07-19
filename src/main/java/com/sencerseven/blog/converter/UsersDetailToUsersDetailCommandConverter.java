package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.UsersDetailCommand;
import com.sencerseven.blog.domain.UsersDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsersDetailToUsersDetailCommandConverter implements Converter<UsersDetail,UsersDetailCommand> {

    @Override
    public UsersDetailCommand convert(UsersDetail usersDetail) {
        if(usersDetail == null)
            return null;

        UsersDetailCommand usersDetailCommand = new UsersDetailCommand();

        usersDetailCommand.setId(usersDetail.getId());
        usersDetailCommand.setDescription(usersDetail.getDescription());
        usersDetailCommand.setProfileImg(usersDetail.getProfileImg());

        return usersDetailCommand;

    }
}
