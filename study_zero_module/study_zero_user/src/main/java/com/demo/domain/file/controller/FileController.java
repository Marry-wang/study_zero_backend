package com.demo.domain.file.controller;

import com.demo.api.ZeroResult;
import com.demo.domain.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 王孟伟
 * @Date: 2024/12/13 17:30
 * @Description:
 */
@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload")
    public ZeroResult<String> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return ZeroResult.success(fileService.uploadFile(file));
    }

    @GetMapping(value = "/viewUrl")
    public ZeroResult<String> uploadFile(@RequestParam(value = "fileName") String fileName) {
        return ZeroResult.success(fileService.viewUrl(fileName));
    }
}
