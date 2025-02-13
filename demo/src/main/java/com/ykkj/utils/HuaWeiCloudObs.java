/*
package com.ykkj.utils;


import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.ObsObject;
import com.obs.services.model.PutObjectResult;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Data
@Component
public class HuaWeiCloudObs {


    FileStorageService fileStorageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HuaWeiCloudObs.class);

    @Value("${cloud.huawei.obs.accessKeyId}")
    private String AK;

    @Value("${cloud.huawei.obs.secretAccessKeyId}")
    private String SK;

    @Value("${cloud.huawei.obs.endPoint}")
    private String ENDPOINT;

    @Value("${cloud.huawei.obs.bucketName}")
    private String BUCKET_NAME;

    */
/**
     * OBS-上传文件
     *
     * @param objectKey   具体的文件名（含存储路径）
     * @param inputStream 输入流
     * @return PutObjectResult
     *//*

    public PutObjectResult uploadFile(String objectKey, InputStream inputStream) {
        ObsClient obsClient = null;
        PutObjectResult putObjectResult = null;
        try {
            obsClient = new ObsClient(AK, SK, ENDPOINT);
            putObjectResult = obsClient.putObject(BUCKET_NAME, objectKey, inputStream);
            LOGGER.info("上传文件PutObjectResult============" + putObjectResult);
        } catch (ObsException e) {
            printObsException(objectKey, e);
        } catch (Exception e) {
            LOGGER.error("OBS上传文件报错Exception===============" + e.getMessage());
        } finally {
            if (obsClient != null) {
                try {
                    // 关闭OBS连接
                    obsClient.close();
                } catch (IOException e) {
                    LOGGER.error("OBS关闭连接报错！===============" + e.getMessage());
                }
            }

        }
        return putObjectResult;
    }

    */
/**
     * OBS-上传文件
     *
     * @param objectKey 具体的文件名（含存储路径）
     * @param fileName  文件路径
     * @return PutObjectResult
     *//*

    public PutObjectResult uploadFile(String objectKey, String fileName) {
        ObsClient obsClient = null;
        PutObjectResult putObjectResult = null;
        try {
            obsClient = new ObsClient(AK, SK, ENDPOINT);
            putObjectResult = obsClient.putObject(BUCKET_NAME, objectKey, new File(fileName));
            LOGGER.info("上传文件PutObjectResult============" + putObjectResult);

        } catch (ObsException e) {
            printObsException(objectKey, e);
        } catch (Exception e) {
            LOGGER.error("OBS上传文件报错Exception===============" + e.getMessage());
        } finally {
            if (obsClient != null) {
                try {
                    // 关闭OBS连接
                    obsClient.close();
                } catch (IOException e) {
                    LOGGER.error("OBS关闭连接报错！===============" + e.getMessage());
                }
            }

        }
        return putObjectResult;
    }

    */
/**
     * OBS-下载文件（获取对象）
     *
     * @param objectKey 具体的文件名（含存储路径）
     * @return ObsObject
     *//*

    public ObsObject downloadFile(String objectKey) {

        ObsClient obsClient = null;
        ObsObject obsObject = null;

        try {

            obsClient = new ObsClient(AK, SK, ENDPOINT);
            obsObject = obsClient.getObject(BUCKET_NAME, objectKey);
            LOGGER.info("下载文件ObsObject============" + obsObject);

        } catch (ObsException e) {
            printObsException(objectKey, e);
        } catch (Exception e) {
            LOGGER.error("OBS下载文件报错Exception===============" + e.getMessage());
        } finally {

            if (obsClient != null) {
                try {
                    // 关闭OBS连接
                    obsClient.close();
                } catch (IOException e) {
                    LOGGER.error("OBS关闭连接报错！===============" + e.getMessage());
                }
            }

        }
        return obsObject;
    }

    */
/**
     * OBS-下载文件（流式下载）
     *
     * @param obsObject 下载文件信息
     * @return ByteArrayOutputStream
     *//*

    public static ByteArrayOutputStream convertOutputStream(ObsObject obsObject) {
        InputStream input = null;
        ByteArrayOutputStream bos = null;
        try {
            // 流式下载
            input = obsObject.getObjectContent();
            byte[] b = new byte[1024];
            bos = new ByteArrayOutputStream();
            int len;
            while ((len = input.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            LOGGER.info(new String(bos.toByteArray()));

            bos.close();
            input.close();

        } catch (Exception e) {
            LOGGER.error("OBS上传文件报错Exception===============" + e.getMessage());
        } finally {

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("bos关闭连接失败===============" + e.getMessage());
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("input关闭连接失败===============" + e.getMessage());
                }
            }

        }
        return bos;
    }

    */
/**
     * OBS-打印错误信息
     *
     * @param objectKey 路径KEY
     * @param e         错误信息
     * @return ByteArrayOutputStream
     *//*

    private static void printObsException(String objectKey, ObsException e) {
        LOGGER.info("OBS文件报错ObsException===============" + objectKey);
        LOGGER.info("ObsException e: ====" + e);
        LOGGER.info("Response Code: ====" + e.getResponseCode());
        LOGGER.info("Error Message: ====" + e.getErrorMessage());
        LOGGER.info("Error Code: =======" + e.getErrorCode());
        LOGGER.info("Request ID: =======" + e.getErrorRequestId());
        LOGGER.info("Host ID: ==========" + e.getErrorHostId());
    }

}*/
