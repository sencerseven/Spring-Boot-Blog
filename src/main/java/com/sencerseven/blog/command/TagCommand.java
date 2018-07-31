package com.sencerseven.blog.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TagCommand {


    private Long id;

    private String tagName;

    public TagCommand(String tagName) {
        this.tagName = tagName;
    }
}
