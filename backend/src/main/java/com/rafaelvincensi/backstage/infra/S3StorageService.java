package com.rafaelvincensi.backstage.infra;

import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class S3StorageService {

    private final S3Template s3Template;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    public S3StorageService(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public String uploadFile(MultipartFile file){
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            S3Resource s3Resource = s3Template.upload(bucketName, uniqueFilename, file.getInputStream());

            return s3Resource.getURL().toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file to Amazon S3", e);
        }
    }
}
