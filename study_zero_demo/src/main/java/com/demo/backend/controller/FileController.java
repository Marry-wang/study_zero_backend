package com.demo.backend.controller;

import com.demo.backend.dto.Result;
import com.demo.backend.util.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName FileController
 * @Author 王孟伟
 * @Date 2021/12/2 14:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/file")
@Api(value = "minio文件", tags = {"minio文件"})
public class FileController {

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传", notes = "文件上传")
    public Result upload(@RequestParam("file") MultipartFile file) {
        return MinioUtil.upload(file);
    }
}
