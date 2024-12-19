package com.demo.domain.file.service.impl;

import com.demo.domain.file.service.FileService;
import com.demo.util.MinioUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 王孟伟
 * @Date: 2024/12/13 17:17
 * @Description:
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(MultipartFile file) {
        return MinioUtil.upload(file);
    }

    @Override
    public String viewUrl(String minioFileName) {
        return MinioUtil.viewUrl(minioFileName);
    }

    @Override
    public Boolean delFile(String minioFileName) {
        MinioUtil.delFile(minioFileName);
        return true;
    }
}
