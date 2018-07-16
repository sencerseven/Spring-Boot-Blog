package com.sencerseven.blog.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentUserCommand {

    private Long id;

    private String name;

    private String email;

    private String website;

}
