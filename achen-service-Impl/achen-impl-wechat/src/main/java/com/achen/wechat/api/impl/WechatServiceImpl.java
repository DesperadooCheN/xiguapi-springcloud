package com.achen.wechat.api.impl;

import com.achen.service.TestService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatServiceImpl implements TestService {
    @Override
    public String Test() {
        return "success+666";
    }
}
