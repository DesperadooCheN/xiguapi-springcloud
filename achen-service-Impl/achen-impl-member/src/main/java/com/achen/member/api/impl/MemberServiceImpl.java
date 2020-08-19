package com.achen.member.api.impl;

import com.achen.member.api.feign.WechatServiceFeign;
import com.achen.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberServiceImpl implements MemberService {

    @Autowired
    private WechatServiceFeign wechatServiceFeign;

    @Override
    public Object getTest() {
        int a = 1;
        return "获取wechat： "+wechatServiceFeign.Test(a);
    }
}
