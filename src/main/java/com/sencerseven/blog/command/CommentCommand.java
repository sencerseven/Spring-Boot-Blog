package com.sencerseven.blog.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentCommand {

    private Long id;

    private String text;

    private String title;

    private String type;

    private boolean read = false;

    private Date createdAt;

    UsersDetailCommand usersDetailCommand;
}
