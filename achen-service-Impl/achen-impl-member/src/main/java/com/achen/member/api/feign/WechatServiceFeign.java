package com.achen.member.api.feign;

import com.achen.service.TestService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("achen-wechat")
public interface WechatServiceFeign extends TestService {

//    @GetMapping("test")
//    String Test();

}
