package com.sencerseven.blog.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SettingsCommand implements Serializable {

    public static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private boolean status;

    private MultipartFile siteLogo;

    private String siteLogoUrl;


}
