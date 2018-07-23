package com.sencerseven.blog.command;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
public class PostCommand {

    private Long id;

    private String title;

    private String description;

    private String text;

    private CategoryCommand category;

    private UsersCommand users;

    private MultipartFile multipartFile;

}
