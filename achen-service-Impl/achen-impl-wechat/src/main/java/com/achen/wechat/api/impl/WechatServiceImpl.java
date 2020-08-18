package com.achen.wechat.api.impl;

import com.achen.base.BaseApiService;
import com.achen.service.TestService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WechatServiceImpl extends BaseApiService implements TestService  {

    @Override
    public String Test(int id) {
        int a = 1/id;
        return setResultSuccess("success").toString();
    }
}
