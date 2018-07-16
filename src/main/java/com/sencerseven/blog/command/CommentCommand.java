package com.sencerseven.blog.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentCommand {

    private Long id;

    private String text;

    CommentUserCommand commentUserCommand;
}
