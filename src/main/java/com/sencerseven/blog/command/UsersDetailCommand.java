package com.sencerseven.blog.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersDetailCommand {

    private Long id;

    private String description;

    private String profileImg;

    private String firstName;

    private String lastName;

    private String email;
}
