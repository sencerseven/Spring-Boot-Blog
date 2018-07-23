package com.sencerseven.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Services {
    public void downloadFile(String keyName);
    public void uploadFile(String keyName, String uploadFilePath);
    public String uploadFile(String keyName, String path, MultipartFile file);
    public String getUrl(String path);
}
