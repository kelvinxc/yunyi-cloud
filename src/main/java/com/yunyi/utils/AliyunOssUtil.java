package com.yunyi.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: AliyunOssUtil
 * @Description: TODO
 * @author:
 * @Version: 1.0
 **/
@Component
public class AliyunOssUtil {

    private static String File_URL;
    private static String bucketName = AliyunOssConfigConstant.BUCKE_NAME;
    private static String endPoint = AliyunOssConfigConstant.END_POINT;
    private static String accessKeyId = AliyunOssConfigConstant.AccessKey_ID;
    private static String accessKeySecret = AliyunOssConfigConstant.AccessKey_Secret;
    private static String fileHost = AliyunOssConfigConstant.FILE_HOST;

    public static String upLoad(MultipartFile file, String path) throws IOException {
        if (file == null || path == null) {
            return null;
        }
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        //设置文件路径
        String fileUrl = fileHost + path + "/" + file.getOriginalFilename();
        File_URL = "https://" + bucketName + "." + endPoint + "/" + fileUrl;
        PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file.getInputStream()));
        //上传文件
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return File_URL;
    }

    public static boolean download(String path,OutputStream outputStream) throws IOException {

        String objectName = path.split("https://yiyi-cloud.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        IOUtils.copy(ossObject.getObjectContent(),outputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    public static boolean delete(String path) throws IOException {
        // 如果是默认头像禁止删除
        if ("https://yiyi-cloud.oss-cn-beijing.aliyuncs.com/yi-cloud/avatar.jpg".equals(path)){
            return false;
        }
        String objectName = path.split("https://yiyi-cloud.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

    public static boolean reName(String path) throws IOException {
        String objectName = path.split("https://yiyi-cloud.oss-cn-beijing.aliyuncs.com/")[1];
        System.err.println(objectName);
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }
        // 关闭OSSClient。
        ossClient.shutdown();
        return true;
    }

}
