package com.cuidl.pip.sms.service;

import java.util.Map;

/**
 * @author cuidl
 */
public interface ISmsService {

    /**
     * 发送消息
     * @param mobile 手机号
     * @param templateCode 模板code
     * @param param 参数
     */
    void send(String mobile, String templateCode, Map<String,Object> param);
}
