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

    private boolean active = true;

    private CategoryCommand category;

    private UsersCommand users;

    private String imageUrl;

    private Date createdAt;

    private String url;

    private MultipartFile multipartFile;

    private int commentCount;

    private String tags;

    public PostCommand(String title, String description, String text, boolean active, CategoryCommand category, UsersCommand users, String imageUrl, Date createdAt, String url, int commentCount, String tags) {
        this.title = title;
        this.description = description;
        this.text = text;
        this.active = active;
        this.category = category;
        this.users = users;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.url = url;
        this.commentCount = commentCount;
        this.tags = tags;
    }
}
