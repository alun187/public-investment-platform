package com.cuidl.pip.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.cuidl.pip.oss.service.IOssService;
import com.cuidl.pip.oss.utils.OssProperty;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author cuidl
 */
@Service
@Slf4j
public class OssServiceImpl implements IOssService {
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {
        log.info(OssProperty.BUCKET_NAME + ", " + OssProperty.ENDPOINT + ", " + OssProperty.KEY_ID);
        OSS client = new OSSClientBuilder().build(
                OssProperty.ENDPOINT,
                OssProperty.KEY_ID,
                OssProperty.KEY_SECRET
        );
        if (!client.doesBucketExist(OssProperty.BUCKET_NAME)) {
            client.createBucket(OssProperty.BUCKET_NAME);
            client.setBucketAcl(OssProperty.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        String fileFolder = new DateTime().toString("/yyyy/MM/dd/");
        String key = module + fileFolder + fileName;
        //文件上传至阿里云
        client.putObject(OssProperty.BUCKET_NAME, key, inputStream);
        client.shutdown();
        return "https://" + OssProperty.BUCKET_NAME + "." + OssProperty.ENDPOINT + "/" + key;
    }

    @Override
    public void removeFile(String url) {
        OSS client = new OSSClientBuilder().build(
                OssProperty.ENDPOINT,
                OssProperty.KEY_ID,
                OssProperty.KEY_SECRET
        );
        String host = "https://" + OssProperty.BUCKET_NAME + "." + OssProperty.ENDPOINT + "/";
        url = url.substring(host.length());
        client.deleteObject(OssProperty.BUCKET_NAME, url);
        client.shutdown();
    }
}
