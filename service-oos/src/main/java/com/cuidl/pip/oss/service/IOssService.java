package com.cuidl.pip.oss.service;

import java.io.InputStream;

/**
 * @author cuidl
 */
public interface IOssService {
    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    void removeFile(String url);
}
