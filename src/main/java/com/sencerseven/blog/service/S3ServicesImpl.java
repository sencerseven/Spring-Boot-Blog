package com.sencerseven.blog.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class S3ServicesImpl implements S3Services {

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    @Override
    public void downloadFile(String keyName) {

    }

    @Override
    public void uploadFile(String keyName, String uploadFilePath) {

        try {

            File file = new File(uploadFilePath);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, keyName, file);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);


            s3client.putObject(putObjectRequest);
            log.info("===================== Upload File - Done! =====================");

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException from PUT requests, rejected reasons:");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException: ");
            log.info("Error Message: " + ace.getMessage());
        }
    }

    @Override
    public String uploadFile(String keyName,String path, MultipartFile file) {

        try {

            InputStream is = file.getInputStream();
            ObjectMetadata metadataCopy = new ObjectMetadata();
// copy previous metadata
            metadataCopy.setContentType(file.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,path +"/"+ keyName,is,metadataCopy);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);


           PutObjectResult name = s3client.putObject(putObjectRequest);
            log.info("===================== Upload File - Done! =====================");

            return path+"/"+keyName;

        } catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException from PUT requests, rejected reasonss:");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException: ");
            log.info("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUrl(String path){
        String url =  s3client.getUrl(bucketName,path).toExternalForm();
        return url;
    }
}
