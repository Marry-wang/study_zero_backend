package com.demo.domain.file.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 王孟伟
 * @Date: 2024/12/13 17:16
 * @Description:
 */
@Service
public interface FileService {

    public String uploadFile(MultipartFile file);


    public String viewUrl(String minioFileName);
}
