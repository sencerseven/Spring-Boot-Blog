package com.sencerseven.blog.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonResponder {

    private String status;
    private Object object;

}
