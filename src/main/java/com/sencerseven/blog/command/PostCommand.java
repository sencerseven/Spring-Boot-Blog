package com.sencerseven.blog.command;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.domain.Comment;
import com.sencerseven.blog.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

    private String imageUrl;

    private Date createdAt;

    private String url;

    private MultipartFile multipartFile;

    private List<CommentCommand> comment = new ArrayList<>();

    private String tags;

}
