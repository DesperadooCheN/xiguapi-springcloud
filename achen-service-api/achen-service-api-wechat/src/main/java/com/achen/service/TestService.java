package com.achen.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface TestService {
    @GetMapping("/test")
    String Test();

//    String Test(@RequestParam("userId")String userId);
}
