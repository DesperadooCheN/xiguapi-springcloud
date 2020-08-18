package com.achen.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TestService {
    @GetMapping("/test")
    String Test(int id);

//    String Test(@RequestParam("userId")String userId);
}
