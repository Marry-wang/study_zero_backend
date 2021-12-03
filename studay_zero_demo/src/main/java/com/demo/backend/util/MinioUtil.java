package com.demo.backend.util;

import com.demo.backend.dto.Result;
import com.demo.backend.dto.ResultFactory;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * @ClassName minioUtil
 * @Author 王孟伟
 * @Date 2021/12/2 10:59
 * @Version 1.0
 */
@Slf4j
@Component
public class MinioUtil implements InitializingBean {

    private static String ENDPOINT;
    private static String BUCKETNAME;
    private static String ACCESSKEY;
    private static String SECRETKEY;

    @Value("${minio.endpoint}")
    public void setENDPOINT(String ENDPOINT){MinioUtil.ENDPOINT=ENDPOINT;}

    @Value("${minio.bucketName}")
    public void setBUCKETNAME(String BUCKETNAME){MinioUtil.BUCKETNAME=BUCKETNAME;}

    @Value("${minio.accessKey}")
    public void setACCESSKEY(String ACCESSKEY){MinioUtil.ACCESSKEY=ACCESSKEY;}

    @Value("${minio.secretKey}")
    public void setSECRETKEY(String SECRETKEY){MinioUtil.SECRETKEY=SECRETKEY;}

    private static MinioClient minioClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        minioClient = new MinioClient(ENDPOINT,ACCESSKEY,SECRETKEY);
    }

    public static Result upload(MultipartFile file){
        try {
            String s=null;
            minioClient = new MinioClient(ENDPOINT, ACCESSKEY, SECRETKEY);
            //存入bucket不存在则创建，并设置为只读
            if (!minioClient.bucketExists(BUCKETNAME)) {
                minioClient.makeBucket(BUCKETNAME);
                minioClient.setBucketPolicy(BUCKETNAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getOriginalFilename();
            String fileType = filename.substring(filename.lastIndexOf("."));
            String uploadfilename = UUID.randomUUID().toString().replaceAll("-","") + fileType;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 文件存储的目录结构
            //String objectName = sdf.format(new Date()) + "/" + uploadfilename;
            String objectName = uploadfilename;
            // 存储文件
            minioClient.putObject(BUCKETNAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功!");
            s= objectName;
            return ResultFactory.buildSuccess(s);
        }catch (Exception e){
            log.info("文件上传失败",e);
            return ResultFactory.buildFail("文件上传失败");
        }
    }
}
